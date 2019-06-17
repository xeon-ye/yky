package com.deloitte.services.srpmp.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.services.srpmp.common.entity.SerialNoCenter;
import com.deloitte.services.srpmp.common.mapper.SerialNoCenterMapper;
import com.deloitte.services.srpmp.common.service.ISerialNoCenterService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-04
 * @Description :  SerialNoCenter服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SerialNoCenterServiceImpl extends ServiceImpl<SerialNoCenterMapper, SerialNoCenter> implements ISerialNoCenterService {

    @Autowired
    private SerialNoCenterMapper serialNoCenterMapper;

    private final String TYPE_APL = "APL";

    private final String TYPE_BUD = "BUD";

    private final String TYPE_TAS = "TAS";

    private final String TYPE_PUD = "PUD";

    private final String TYPE_APD = "APD";

    private final String TYPE_BJ = "BJ";

    private final String TYPE_ACCEPT = "ACC";

    private final String TYPE_RES = "RES";

    private final String TYPE_TRA = "TRA";

    private int getNextNo(String type, String header) {

        SerialNoCenter serialNo = new SerialNoCenter();
        serialNo.setSerialType(type);
        serialNo.setSerialHeader(header);
        serialNo = serialNoCenterMapper.selectByTypeAndHeaderWithLock(serialNo);
        long serNo = 0l;
        if (serialNo != null) {
            serNo = serialNo.getSerialNo() + 1;
        } else {
            serialNo = new SerialNoCenter();
            serialNo.setSerialType(type);
            serialNo.setSerialHeader(header);
            serNo ++;
        }
        serialNo.setSerialNo((long) serNo );
        this.saveOrUpdate(serialNo);
        return (int) serNo;
    }

    @Override
    public JSONObject getNextAPLNo(String header,  DeptVo deptVo) {

        JSONObject json = new JSONObject();
        int serNo  = getNextNo(TYPE_APL, header);
        StringBuilder sb = new StringBuilder();
        sb.append(header);
        sb.append(StringUtils.leftPad("" + serNo, 3,"0"));
        String no = sb.toString();
        json.put("aplNum", TYPE_APL + no);
        json.put("budNum", TYPE_BUD + no);

        String year = DateFormatUtils.format(new Date(), "yyyy");
        String tasheader = year.substring(2) + deptVo.getDeptCode() + header.substring(2);

        serNo  = getNextNo(TYPE_TAS, tasheader);
        sb = new StringBuilder();
        sb.append(tasheader);
        sb.append(StringUtils.leftPad("" + serNo, 3,"0"));
        json.put("tasNum", TYPE_TAS + sb.toString());
        return json;
    }

    @Override
    public String getNextPUDNo(String header) {

        int serNo  = getNextNo(TYPE_PUD, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_PUD);
        sb.append(header);
        sb.append(StringUtils.leftPad("" + serNo, 4,"0"));
        return sb.toString();
    }

    @Override
    public String getNextAPDNo(String header) {

        int serNo  = getNextNo(TYPE_APD, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_APD);
        sb.append(header);
        sb.append(StringUtils.leftPad("" + serNo, 4,"0"));
        return sb.toString();
    }

    @Override
    public String getNextBJNo(String header, String projectCode) {

        int serNo  = getNextNo(TYPE_BJ, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_BJ);
        sb.append(projectCode);
        sb.append(serNo);
        return sb.toString();
    }

    @Override
    public String getNextAcceptNo(String header, String projectCode) {

        int serNo  = getNextNo(TYPE_ACCEPT, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_ACCEPT);
        sb.append(header);
        if(StringUtils.isNotBlank(projectCode)) {
            sb.append(projectCode);
        }
        sb.append(serNo);
        return sb.toString();
    }

    @Override
    public String getNextResultNo(String header) {

        int serNo  = getNextNo(TYPE_RES, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_RES);
        sb.append(header);
        sb.append(StringUtils.leftPad("" + serNo, 4,"0"));
        return sb.toString();
    }

    @Override
    public String getNextResultTransNo(String header) {

        int serNo  = getNextNo(TYPE_TRA, header);
        StringBuilder sb = new StringBuilder();
        sb.append(TYPE_TRA);
        sb.append(header);
        sb.append(StringUtils.leftPad("" + serNo, 4,"0"));
        return sb.toString();
    }
}


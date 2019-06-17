package com.deloitte.services.dss.scientific.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.dss.scientific.mapper.DeptFundMapper;
import com.deloitte.services.dss.scientific.mapper.ResearchResultMapper;
import com.deloitte.services.dss.scientific.service.IDeptFundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptFundServiceImpl extends ServiceImpl implements IDeptFundService {

    @Autowired
    private DeptFundMapper deptFundMapper;

    @Autowired
    private ResearchResultMapper researchResultMapper;


    @Override
    public List<DeptFundVo> queryDeptProAmountPay(Map map) {
        return deptFundMapper.queryDeptProAmountPay(map);
    }

    @Override
    public Map queryDeptProFundDetail(Map map) {
        //查询明细
        Map detailMap = deptFundMapper.queryDeptProFundDetail(map);
        if (null == detailMap) {
            throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
        }

        //处理明细json
        Map amountDetail = JSONHandler(detailMap.get("amountDetail".toUpperCase()).toString());
        Map payDetail = JSONHandler(detailMap.get("payDetail".toUpperCase()).toString());
        Map percent = new HashMap();


        //计算明细执行率
        for (Object name : amountDetail.keySet()) {
            Double amount = Double.parseDouble(amountDetail.get(name).toString()) ;
            Double pay = Double.parseDouble(payDetail.get(name).toString());
            percent.put(name, pay / amount);
        }

        //计算执行率平均值
        Double percentSum = 0.0;
        for (Object aDouble : percent.values()) {
            percentSum += Double.parseDouble(aDouble.toString());
        }


        DeptFundDetailVo vo = new DeptFundDetailVo();
        vo.setProjectId(Long.parseLong(detailMap.get("projectId".toUpperCase()).toString()));
        vo.setProjectName(detailMap.get("projectName".toUpperCase()).toString());

        //执行率平均值
        vo.setAverage(percentSum / percent.size());
        //明细执行率json
        vo.setDeptFundDetail(JSON.toJSONString(percent));

        //查询项目成果
        List<ResultVo> paper = researchResultMapper.queryResearch(map.get("projectNum").toString());

        //项目PI
        ProjectLeaderVo leader = deptFundMapper.queryLeader(map.get("projectNum").toString());

        //项目人员学位统计
        ProjectPersonJoinVo joinPerson = deptFundMapper.queryJoinPerson(map.get("projectNum").toString());

        Map resutlMap = new HashMap();
        resutlMap.put("detail",vo);
        resutlMap.put("paper",paper);
        resutlMap.put("leader", leader);
        resutlMap.put("joinPerson", joinPerson);
        return resutlMap;
    }


    /**
     * 处理经费明细JSON
     *
     * @param JSONString
     * @return
     */
    public Map JSONHandler(String JSONString) {
        Map cmap = new HashMap();
        if (JSON.isValid(JSONString)) {//判断是否是Json字符串
            JSONArray objects = JSON.parseArray(JSONString);//转换成jsonarray

            //把jsonarray里的数据整合，按name以及对应的amount合并成一个map
            for (int i = 0; i < objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String name = jsonObject.get("name").toString();
                if (cmap.containsKey(name)) {
                    Object amount = jsonObject.get("amount");
                    if (null != amount && !"".equals(amount)) {
                        if (null != cmap.get(name) && !"".equals(cmap.get(name))) {
                            cmap.put(name, Long.parseLong(amount.toString()) + Long.parseLong(cmap.get(name).toString()));
                        } else {
                            cmap.put(name, amount);
                        }
                    }
                } else {
                    cmap.put(name, jsonObject.get("amount"));
                }
            }
        }else {
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE);
        }
        return cmap;
    }


}

package com.deloitte.services.fssc.business.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.business.bpm.entity.BaseBpmProcessVal;
import com.deloitte.services.fssc.business.bpm.mapper.BaseBpmProcessValMapper;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmProcessValService;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-29
 * @Description :  BaseBpmProcessVal服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class BaseBpmProcessValServiceImpl extends ServiceImpl<BaseBpmProcessValMapper, BaseBpmProcessVal> implements IBaseBpmProcessValService {

    @Autowired
    private UserFeignService userFeignService;

    /**
     * 获取
     * @return
     */
    public Map<String,String> getAndSaveProcessValue(ProcessValueForm form){
        QueryWrapper<BaseBpmProcessVal> valQueryWrapper=new QueryWrapper<>();
        valQueryWrapper.eq(BaseBpmProcessVal.DOCUMENT_ID,form.getDocumentId())
                .eq(BaseBpmProcessVal.DOCUMENT_TYPE, form.getDocumentType());
        this.remove(valQueryWrapper);
        List<BaseBpmProcessVal> vals=new ArrayList<>();
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "request", form.getRequest()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "hasProject",form.getProjectId()!=null?"Y":"N"));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "totalAmount",form.getTotalAmount()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "mainTypeCode",form.getMainTypeCode()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "projectId", StringUtil.objectToString(form.getProjectId())));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "createBy", StringUtil.objectToString(form.getCreateBy())));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "deptCode",form.getDeptCode()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "projectCode",form.getProjectCode()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "unitCode",StringUtil.objectToString(form.getUnitCode())));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "hasContract",form.getHasContract()));
        vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                "documentType",form.getDocumentType()));
        if(form.getCreateBy()!=null){
            try {
                Result<UserVo> userVoResult = userFeignService.get(form.getCreateBy());
                UserVo data = userVoResult.getData();
                vals.add(new BaseBpmProcessVal(form.getDocumentId(),form.getDocumentType(),
                        "level",data.getPositionLevel()));
            }catch (Exception e){
                log.error("流程变量构造用户级别失败!");
            }

        }

        this.saveBatch(vals);

        return convertMap(vals);
    }


    private Map<String,String> convertMap(List<BaseBpmProcessVal> vals){
        Map<String,String> processVariables=new HashMap<>();
        for (BaseBpmProcessVal val:vals){
            processVariables.put(val.getKey(),val.getValue());
        }
        return processVariables;
    }


    public Map<String,String> findValueByDocument(Long documentId,String documentType){
        QueryWrapper<BaseBpmProcessVal> valQueryWrapper=new QueryWrapper<>();
        valQueryWrapper.eq(BaseBpmProcessVal.DOCUMENT_ID,documentId)
                .eq(BaseBpmProcessVal.DOCUMENT_TYPE,documentType);
        List<BaseBpmProcessVal> list = this.list(valQueryWrapper);

        return convertMap(list);
    }

}


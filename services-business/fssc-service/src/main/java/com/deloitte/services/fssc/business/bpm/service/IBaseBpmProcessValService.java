package com.deloitte.services.fssc.business.bpm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.business.bpm.entity.BaseBpmProcessVal;
import com.deloitte.services.fssc.business.bpm.vo.ProcessValueForm;

import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-29
 * @Description : BaseBpmProcessVal服务类接口
 * @Modified :
 */
public interface IBaseBpmProcessValService extends IService<BaseBpmProcessVal> {



    Map<String,String> getAndSaveProcessValue(ProcessValueForm form);


    Map<String,String> findValueByDocument(Long documentId,String documentType);
}

package com.deloitte.services.fssc.business.bpm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bpm.param.ProcessQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : Process服务类接口
 * @Modified :
 */
public interface IProcessService extends IService<Process> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Process>
     */
    IPage<Process> selectPage(ProcessQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Process>
     */
    List<Process> selectList(ProcessQueryForm queryForm);

    String findStatus(Long documentId,String documentType);
}

package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.ProcessQueryForm;
import com.deloitte.services.contract.entity.Process;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-30
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

    /**
     * 根据审批选择的key获取作废时需要用的key
     * @param processDefineKey
     * @return
     */
    Process getEndKeyByStartKey(String processDefineKey);
}

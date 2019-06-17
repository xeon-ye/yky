package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExeChangeQueryForm;
import com.deloitte.services.project.entity.ExeChange;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : ExeChange服务类接口
 * @Modified :
 */
public interface IExeChangeService extends IService<ExeChange> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ExeChange>
     */
    IPage<ExeChange> selectPage(ExeChangeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ExeChange>
     */
    List<ExeChange> selectList(ExeChangeQueryForm queryForm);

    /**
     * 获取
     * @param executionId
     * @return
     */
    List<ExeChange> getExeChangeList(long executionId);
}

package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ExeRepHisQueryForm;
import com.deloitte.services.project.entity.ExeRepHis;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description : ExeRepHis服务类接口
 * @Modified :
 */
public interface IExeRepHisService extends IService<ExeRepHis> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ExeRepHis>
     */
    IPage<ExeRepHis> selectPage(ExeRepHisQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ExeRepHis>
     */
    List<ExeRepHis> selectList(ExeRepHisQueryForm queryForm);

    /**
     *
     * @param applicationId
     * @return
     */
    List<ExeRepHis> getAllList(String exeRepId);

}

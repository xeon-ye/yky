package com.deloitte.services.fssc.system.attchdef.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.attchdef.param.BaseFileDefLineQueryForm;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDefLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDefLine服务类接口
 * @Modified :
 */
public interface IBaseFileDefLineService extends IService<BaseFileDefLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseFileDefLine>
     */
    IPage<BaseFileDefLine> selectPage(BaseFileDefLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseFileDefLine>
     */
    List<BaseFileDefLine> selectList(BaseFileDefLineQueryForm queryForm);
}

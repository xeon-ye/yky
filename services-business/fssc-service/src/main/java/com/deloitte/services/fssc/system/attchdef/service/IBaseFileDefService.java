package com.deloitte.services.fssc.system.attchdef.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.attchdef.param.BaseFileDefQueryForm;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDef;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDef服务类接口
 * @Modified :
 */
public interface IBaseFileDefService extends IService<BaseFileDef> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseFileDef>
     */
    IPage<BaseFileDef> selectPage(BaseFileDefQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseFileDef>
     */
    List<BaseFileDef> selectList(BaseFileDefQueryForm queryForm);
}

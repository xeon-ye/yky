package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineBaseQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description : SrpmsOutlineBase服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineBaseService extends IService<SrpmsOutlineBase> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsOutlineBase>
     */
    IPage<SrpmsOutlineBase> selectPage(BaseQueryForm<SrpmsOutlineBaseQueryParam> queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsOutlineBase>
     */
    List<SrpmsOutlineBase> selectList(BaseQueryForm<SrpmsOutlineBaseQueryParam> queryForm);

    /**
     * 根据条件查询题录基础数据service接口
     *
     * @param type
     * @param orgId
     * @param year
     * @param month
     * @return
     */
    SrpmsOutlineBase getSrpmsOutlineBase(String type, Long orgId, String year, String month);
}

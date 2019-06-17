package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicSubjectMapQueryForm;
import com.deloitte.services.contract.entity.BasicSubjectMap;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicSubjectMap服务类接口
 * @Modified :
 */
public interface IBasicSubjectMapService extends IService<BasicSubjectMap> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicSubjectMap>
     */
    IPage<BasicSubjectMap> selectPage(BasicSubjectMapQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicSubjectMap>
     */
    List<BasicSubjectMap> selectList(BasicSubjectMapQueryForm queryForm);
}

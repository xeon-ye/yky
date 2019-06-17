package com.deloitte.services.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.portal.param.CategoryQueryForm;
import com.deloitte.services.portal.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description : Category服务类接口
 * @Modified :
 */
public interface ICategoryService extends IService<Category> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Category>
     */
    IPage<Category> selectPage(CategoryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Category>
     */
    List<Category> selectList(CategoryQueryForm queryForm);

    /**
     * 查询首页栏目列表
     * @return
     */
    List<Category> selectHomeCategories();
}

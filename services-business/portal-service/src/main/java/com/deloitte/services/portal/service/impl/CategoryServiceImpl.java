package com.deloitte.services.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.portal.param.CategoryQueryForm;
import com.deloitte.services.portal.entity.Category;
import com.deloitte.services.portal.mapper.CategoryMapper;
import com.deloitte.services.portal.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :  Category服务实现类
 * @Modified :
 */
@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public IPage<Category> selectPage(CategoryQueryForm queryForm ) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper <Category>();
        //getQueryWrapper(queryWrapper,queryForm);
        return categoryMapper.selectPage(new Page<Category>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Category> selectList(CategoryQueryForm queryForm) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper <Category>();
        //getQueryWrapper(queryWrapper,queryForm);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> selectHomeCategories() {
        return categoryMapper.selectHomeCategories();
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Category> getQueryWrapper(QueryWrapper<Category> queryWrapper,CategoryQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCategoryId())){
            queryWrapper.eq(Category.CATEGORY_ID,queryForm.getCategoryId());
        }
        if(StringUtils.isNotBlank(queryForm.getCategoryTitle())){
            queryWrapper.eq(Category.CATEGORY_TITLE,queryForm.getCategoryTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getCategoryKey())){
            queryWrapper.eq(Category.CATEGORY_KEY,queryForm.getCategoryKey());
        }
        if(StringUtils.isNotBlank(queryForm.getCategorySort())){
            queryWrapper.eq(Category.CATEGORY_SORT,queryForm.getCategorySort());
        }
        if(StringUtils.isNotBlank(queryForm.getCategoryDatetime())){
            queryWrapper.eq(Category.CATEGORY_DATETIME,queryForm.getCategoryDatetime());
        }
        if(StringUtils.isNotBlank(queryForm.getCategoryDescription())){
            queryWrapper.eq(Category.CATEGORY_DESCRIPTION,queryForm.getCategoryDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getDelFlg())){
            queryWrapper.eq(Category.DEL_FLG,queryForm.getDelFlg());
        }
        return queryWrapper;
    }
     */
}


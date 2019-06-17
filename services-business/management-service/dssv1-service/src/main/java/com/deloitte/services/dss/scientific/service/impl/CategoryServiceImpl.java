package com.deloitte.services.dss.scientific.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.dss.scientific.service.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl implements ICategoryService {
    @Override
    public String getCategory(String category) {
        if("100101".equals(category)){
            return category;
        }else {
            String[] categoryList = category.split("_");
            String categoryString = "";
            for (int i = 0; i < categoryList.length; i++) {
                if (i == 0) {
                    categoryString = "('" + categoryList[i] + "'";
                } else {
                    categoryString = categoryString + ",'" + categoryList[i] + "'";
                }
            }
            categoryString = categoryString + ")";
            return categoryString;
        }
    }

    @Override
    public String getCategoryData(String category) {
        if("1001".equals(category)){
            return category;
        }else {
            String[] categoryList = category.split("_");
            String categoryString = "";
            for (int i = 0; i < categoryList.length; i++) {
                if (i == 0) {
                    categoryString = "('" + categoryList[i] + "'";
                } else {
                    categoryString = categoryString + ",'" + categoryList[i] + "'";
                }
            }
            categoryString = categoryString + ")";
            return categoryString;
        }
    }
}

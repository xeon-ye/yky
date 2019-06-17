package com.deloitte.services.dss.scientific.service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface ICategoryService extends IService {
    //转换category
    String getCategory(String category);

    String getCategoryData(String category);

}

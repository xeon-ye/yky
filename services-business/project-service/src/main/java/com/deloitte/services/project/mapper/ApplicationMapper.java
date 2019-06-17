package com.deloitte.services.project.mapper;

import com.deloitte.services.project.entity.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 申报书 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-22
 */
public interface ApplicationMapper extends BaseMapper<Application> {

    Application getOneApp(String projectId);
}

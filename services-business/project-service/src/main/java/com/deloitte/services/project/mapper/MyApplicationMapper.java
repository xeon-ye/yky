package com.deloitte.services.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.services.project.entity.MyApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/9 10:26
 * @Description :
 * @Modified:
 */
@Mapper
public interface MyApplicationMapper extends BaseMapper<MyApplication> {


    /**
     * 分页查询-项目申报-我的申请
     * @param page
     * @return
     */
    List<MyApplication> searchMyApplication(Page page);

}

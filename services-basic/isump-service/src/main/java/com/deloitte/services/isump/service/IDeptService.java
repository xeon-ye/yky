package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.isump.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description : Dept服务类接口
 * @Modified :
 */
public interface IDeptService extends IService<Dept> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Dept>
     */
    IPage<Dept> selectPage(DeptQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Dept>
     */
    List<Dept> selectList(DeptQueryForm queryForm);

    Result save2(DeptForm deptForm);
    /**
     * 根据code查询单位信息
     * @param code
     * @return
     */
    DeptVo getByCode(String code);

    /**
     * 根据名称查询单位信息
     * @param name
     * @return
     */
    DeptVo getByName(String name);

    boolean deptEnabled(long id);

    /**
     * 更新状态
     * @param state
     * @param id
     * @return
     */
    int updateState(String state, String id);
}

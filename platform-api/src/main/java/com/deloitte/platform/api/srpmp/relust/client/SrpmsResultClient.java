package com.deloitte.platform.api.srpmp.relust.client;

import javax.validation.Valid;

import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.relust.param.SrpmsResultQueryForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultForm;
import com.deloitte.platform.api.srpmp.relust.vo.SrpmsResultVo;
import com.deloitte.platform.common.core.entity.vo.Result;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description :  SrpmsResult控制器接口
 * @Modified :
 */
public interface SrpmsResultClient {

    public static final String path="/srpmp/result";

    /**
     *  POST---新增
     * @param srpmsResultForm
     * @return
     */
    @PostMapping(value = path)	
    Result save(@Valid @ModelAttribute SrpmsResultForm srpmsResultForm, UserVo userVo, DeptVo deptVo);

    /**
     *  POST---新增
     * @param srpmsResultForm
     * @return
     */
    @PostMapping(value = path+"/submit")	
    Result submit(@Valid @ModelAttribute SrpmsResultForm srpmsResultForm, UserVo userVo, DeptVo deptVo);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SrpmsResultVo> get(@PathVariable(value="id") long id);

    /**
     * 根据id删除成果管理数据
     *
     * @param id
     * @return
     */
    @GetMapping(value = path+"/delete/{id}")
    Result<SrpmsResultVo> delete(@PathVariable(value="id") long id);

    /**
     *  POST----复杂查询
     * @param  srpmsResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsResultVo>> search(@Valid @RequestBody SrpmsResultQueryForm srpmsResultQueryForm, UserVo userVo, DeptVo deptVo);

    /**
     * 成果管理审批通过操作
     *
     * @param actionVO
     * @param deptVo
     * @return
     */
    @PostMapping(value = path+"/audit/approve")
    Result auditApprove(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo);

    /**
     * 成果管理审批拒绝操作
     *
     * @param actionVO
     * @return
     */
    @PostMapping(value = path+"/refuse")
    Result refuse(@Valid @RequestBody TaskNodeActionVO actionVO);

}

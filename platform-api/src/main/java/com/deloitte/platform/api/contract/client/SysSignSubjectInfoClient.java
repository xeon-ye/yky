package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysSignSubjectInfo控制器接口
 * @Modified :
 */
public interface SysSignSubjectInfoClient {

    public static final String path="/contract/sys-sign-subject-info";


    /**
     *  POST---新增
     * @param sysSignSubjectInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SysSignSubjectInfoForm sysSignSubjectInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, sysSignSubjectInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SysSignSubjectInfoForm sysSignSubjectInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SysSignSubjectInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   sysSignSubjectInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SysSignSubjectInfoVo>> list(@Valid @RequestBody SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  sysSignSubjectInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SysSignSubjectInfoVo>> search(@Valid @RequestBody SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm);
}

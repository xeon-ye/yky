package com.deloitte.platform.api.hr.ebs.client;

import com.deloitte.platform.api.hr.ebs.param.EmployeeAbroadQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeAbroadForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeAbroadVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeAbroad控制器接口
 * @Modified :
 */
public interface EmployeeAbroadClient {

    public static final String path="/hr/employee-abroad";


    /**
     *  POST---新增
     * @param employeeAbroadForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeAbroadForm employeeAbroadForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeAbroadForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EmployeeAbroadForm employeeAbroadForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeAbroadVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   employeeAbroadQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeAbroadVo>> list(@Valid @RequestBody EmployeeAbroadQueryForm employeeAbroadQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeAbroadQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeAbroadVo>> search(@Valid @RequestBody EmployeeAbroadQueryForm employeeAbroadQueryForm);

    /**
     *  根据职工编号code获取人员出国出境信息数据
     *
     * @param userCode 人员信息CODE（非必填，当前登录用户）
     * @return
     */
    @ApiOperation(value = "根据职工编号code获取人员出国出境信息数据", notes = "根据指定职工编号code获取出国出境数据信息")
    @GetMapping(value = path+"/getEmployeeAbroadByEmpCode")
    Result<EmployeeAbroadVo> getEmployeeAbroadByEmpCode(@RequestParam(required = false) String userCode);
}

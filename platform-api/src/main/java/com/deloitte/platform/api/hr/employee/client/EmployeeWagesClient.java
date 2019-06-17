package com.deloitte.platform.api.hr.employee.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee.param.EmployeeWagesQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeWagesForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeWagesInfoVo;
import com.deloitte.platform.api.hr.employee.vo.EmployeeWagesVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :  EmployeeWages控制器接口
 * @Modified :
 */
public interface EmployeeWagesClient {

    public static final String path="/hr/employee-wages";


    /**
     *  POST---新增
     * @param employeeWagesForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeWagesForm employeeWagesForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeWagesForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EmployeeWagesForm employeeWagesForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeWagesVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   employeeWagesQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeWagesVo>> list(@Valid @RequestBody EmployeeWagesQueryForm employeeWagesQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeWagesQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeWagesVo>> search(@Valid @RequestBody EmployeeWagesQueryForm employeeWagesQueryForm);

    /**
     *  POST----复杂查询
     * @param  employeeWagesQueryForm
     * @return
     */
    @PostMapping(value = path+"/getUserWages")
    Result<EmployeeWagesInfoVo>  getUserWages(@Valid @RequestBody EmployeeWagesQueryForm employeeWagesQueryForm);
}

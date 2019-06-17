package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesScienceQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesScienceForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesScienceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :  EmployeeMesScience控制器接口
 * @Modified :
 */
public interface EmployeeMesScienceClient {

    public static final String path="/hr/employee-mes-science";


    /**
     *  POST---新增
     * @param employeeMesScienceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesScienceForm employeeMesScienceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesScienceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesScienceForm employeeMesScienceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesScienceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesScienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesScienceVo>> list(@Valid @RequestBody EmployeeMesScienceQueryForm employeeMesScienceQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesScienceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesScienceVo>> search(@Valid @RequestBody EmployeeMesScienceQueryForm employeeMesScienceQueryForm);
}

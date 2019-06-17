package com.deloitte.platform.api.hr.employee_mes.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesBankQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBankForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBankVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :  EmployeeMesBank控制器接口
 * @Modified :
 */
public interface EmployeeMesBankClient {

    public static final String path="/hr/employee-mes-bank";


    /**
     *  POST---新增
     * @param employeeMesBankForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesBankForm employeeMesBankForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesBankForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesBankForm employeeMesBankForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesBankVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesBankQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesBankVo>> list(@Valid @RequestBody EmployeeMesBankQueryForm employeeMesBankQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesBankQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesBankVo>> search(@Valid @RequestBody EmployeeMesBankQueryForm employeeMesBankQueryForm);
}

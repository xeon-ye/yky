package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesQuitQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesQuitForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesQuitVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesQuit控制器接口
 * @Modified :
 */
public interface EmployeeMesQuitClient {

    public static final String path="/hr/employee-mes-quit";


    /**
     *  POST---新增
     * @param employeeMesQuitForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesQuitForm employeeMesQuitForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesQuitForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesQuitForm employeeMesQuitForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesQuitVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesQuitQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesQuitVo>> list(@Valid @RequestBody EmployeeMesQuitQueryForm employeeMesQuitQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesQuitQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesQuitVo>> search(@Valid @RequestBody EmployeeMesQuitQueryForm employeeMesQuitQueryForm);
}

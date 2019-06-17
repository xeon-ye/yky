package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesDisQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesDisForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesDisVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesDis控制器接口
 * @Modified :
 */
public interface EmployeeMesDisClient {

    public static final String path="/hr/employee-mes-dis";


    /**
     *  POST---新增
     * @param employeeMesDisForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesDisForm employeeMesDisForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesDisForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesDisForm employeeMesDisForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesDisVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesDisQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesDisVo>> list(@Valid @RequestBody EmployeeMesDisQueryForm employeeMesDisQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesDisQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesDisVo>> search(@Valid @RequestBody EmployeeMesDisQueryForm employeeMesDisQueryForm);
}

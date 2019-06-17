package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.EmployeePoliticalQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeePoliticalForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeePoliticalVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :  EmployeePolitical控制器接口
 * @Modified :
 */
public interface EmployeePoliticalClient {

    public static final String path="/hr/employee-political";


    /**
     *  POST---新增
     * @param employeePoliticalForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeePoliticalForm employeePoliticalForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeePoliticalForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeePoliticalForm employeePoliticalForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeePoliticalVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeePoliticalQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeePoliticalVo>> list(@Valid @RequestBody EmployeePoliticalQueryForm employeePoliticalQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeePoliticalQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeePoliticalVo>> search(@Valid @RequestBody EmployeePoliticalQueryForm employeePoliticalQueryForm);
}

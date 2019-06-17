package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.EmployeeLastworkQueryForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeLastworkForm;
import com.deloitte.platform.api.hr.employee.vo.EmployeeLastworkVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :  EmployeeLastwork控制器接口
 * @Modified :
 */
public interface EmployeeLastworkClient {

    public static final String path="/hr/employee-lastwork";


    /**
     *  POST---新增
     * @param employeeLastworkForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeLastworkForm employeeLastworkForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeLastworkForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeLastworkForm employeeLastworkForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeLastworkVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeLastworkQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeLastworkVo>> list(@Valid @RequestBody EmployeeLastworkQueryForm employeeLastworkQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeLastworkQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeLastworkVo>> search(@Valid @RequestBody EmployeeLastworkQueryForm employeeLastworkQueryForm);
}

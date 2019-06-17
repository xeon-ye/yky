package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesBaseQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBaseForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesBase控制器接口
 * @Modified :
 */
public interface EmployeeMesBaseClient {

    public static final String path="/hr/employee-mes-base";


    /**
     *  POST---新增
     * @param employeeMesBaseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesBaseForm employeeMesBaseForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesBaseForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesBaseForm employeeMesBaseForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesBaseVo> get(@PathVariable(value = "id") String id);


    /**
     *  POST----列表查询
     * @param   employeeMesBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesBaseVo>> list(@Valid @RequestBody EmployeeMesBaseQueryForm employeeMesBaseQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesBaseVo>> search(@Valid @RequestBody EmployeeMesBaseQueryForm employeeMesBaseQueryForm);
}

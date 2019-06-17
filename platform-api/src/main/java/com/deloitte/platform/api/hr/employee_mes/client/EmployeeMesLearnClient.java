package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesLearnQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLearnForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLearnVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :  EmployeeMesLearn控制器接口
 * @Modified :
 */
public interface EmployeeMesLearnClient {

    public static final String path="/hr/employee-mes-learn";


    /**
     *  POST---新增
     * @param employeeMesLearnForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesLearnForm employeeMesLearnForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesLearnForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesLearnForm employeeMesLearnForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesLearnVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesLearnQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesLearnVo>> list(@Valid @RequestBody EmployeeMesLearnQueryForm employeeMesLearnQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesLearnQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesLearnVo>> search(@Valid @RequestBody EmployeeMesLearnQueryForm employeeMesLearnQueryForm);
}

package com.deloitte.platform.api.hr.employee_mes.client;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesStrationQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesStrationForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesStrationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-14
 * @Description :  EmployeeMesStration控制器接口
 * @Modified :
 */
public interface EmployeeMesStrationClient {

    public static final String path="/hr/employee-mes-stration";


    /**
     *  POST---新增
     * @param employeeMesStrationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesStrationForm employeeMesStrationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesStrationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesStrationForm employeeMesStrationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesStrationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesStrationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesStrationVo>> list(@Valid @RequestBody EmployeeMesStrationQueryForm employeeMesStrationQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesStrationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesStrationVo>> search(@Valid @RequestBody EmployeeMesStrationQueryForm employeeMesStrationQueryForm);
}

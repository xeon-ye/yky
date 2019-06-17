package com.deloitte.platform.api.hr.employee_mes.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesLogyQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLogyForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLogyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description :  EmployeeMesLogy控制器接口
 * @Modified :
 */
public interface EmployeeMesLogyClient {

    public static final String path="/hr/employee-mes-logy";


    /**
     *  POST---新增
     * @param employeeMesLogyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesLogyForm employeeMesLogyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesLogyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesLogyForm employeeMesLogyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesLogyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesLogyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesLogyVo>> list(@Valid @RequestBody EmployeeMesLogyQueryForm employeeMesLogyQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesLogyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesLogyVo>> search(@Valid @RequestBody EmployeeMesLogyQueryForm employeeMesLogyQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesLogyForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesLogyForm employeeMesLogyForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesLogyForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesLogyForm employeeMesLogyForm);
}

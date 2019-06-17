package com.deloitte.platform.api.hr.employee_mes.client;

import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesSkillQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesSkillForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesSkillVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-14
 * @Description :  EmployeeMesSkill控制器接口
 * @Modified :
 */
public interface EmployeeMesSkillClient {

    public static final String path="/hr/employee-mes-skill";


    /**
     *  POST---新增
     * @param employeeMesSkillForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesSkillForm employeeMesSkillForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesSkillForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesSkillForm employeeMesSkillForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesSkillVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesSkillQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesSkillVo>> list(@Valid @RequestBody EmployeeMesSkillQueryForm employeeMesSkillQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesSkillQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesSkillVo>> search(@Valid @RequestBody EmployeeMesSkillQueryForm employeeMesSkillQueryForm);
}

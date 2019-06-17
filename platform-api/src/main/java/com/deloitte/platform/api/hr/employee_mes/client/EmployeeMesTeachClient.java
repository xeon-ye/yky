package com.deloitte.platform.api.hr.employee_mes.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee_mes.param.EmployeeMesTeachQueryForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesTeachForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesTeachResultForm;
import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesTeachVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :  EmployeeMesTeach控制器接口
 * @Modified :
 */
public interface EmployeeMesTeachClient {

    public static final String path="/hr/employee-mes-teach";


    /**
     *  POST---新增
     * @param employeeMesTeachForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesTeachForm employeeMesTeachForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesTeachForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") String id, @Valid @RequestBody EmployeeMesTeachResultForm teachResultForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesTeachVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesTeachQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesTeachVo>> list(@Valid @RequestBody EmployeeMesTeachQueryForm employeeMesTeachQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesTeachQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesTeachVo>> search(@Valid @RequestBody EmployeeMesTeachQueryForm employeeMesTeachQueryForm);


    /**
     *  POST---新增  修改  无效
     * @param employeeMesTeachForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesTeachForm employeeMesTeachForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesTeachForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesTeachForm employeeMesTeachForm);
}

package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesHighQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesHighForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesHighVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesHigh控制器接口
 * @Modified :
 */
public interface EmployeeMesHighClient {

    public static final String path="/hr/employee-mes-high";


    /**
     *  POST---新增
     * @param employeeMesHighForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesHighForm employeeMesHighForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesHighForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesHighForm employeeMesHighForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesHighVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesHighQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesHighVo>> list(@Valid @RequestBody EmployeeMesHighQueryForm employeeMesHighQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesHighQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesHighVo>> search(@Valid @RequestBody EmployeeMesHighQueryForm employeeMesHighQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesHighForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesHighForm employeeMesHighForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesHighForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesHighForm employeeMesHighForm);
}

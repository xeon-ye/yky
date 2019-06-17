package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesWorkQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesWorkForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesWorkVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesWork控制器接口
 * @Modified :
 */
public interface EmployeeMesWorkClient {

    public static final String path="/hr/employee-mes-work";


    /**
     *  POST---新增
     * @param employeeMesWorkForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesWorkForm employeeMesWorkForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesWorkForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesWorkForm employeeMesWorkForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesWorkVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesWorkQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesWorkVo>> list(@Valid @RequestBody EmployeeMesWorkQueryForm employeeMesWorkQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesWorkQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesWorkVo>> search(@Valid @RequestBody EmployeeMesWorkQueryForm employeeMesWorkQueryForm);


    /**
     *  POST---新增  修改  无效
     * @param employeeMesWorkForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesWorkForm employeeMesWorkForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesWorkForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesWorkForm employeeMesWorkForm);
}

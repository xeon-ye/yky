package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesAwardQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesAwardForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesAwardVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesAward控制器接口
 * @Modified :
 */
public interface EmployeeMesAwardClient {

    public static final String path="/hr/employee-mes-award";


    /**
     *  POST---新增
     * @param employeeMesAwardForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesAwardForm employeeMesAwardForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesAwardForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesAwardForm employeeMesAwardForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesAwardVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesAwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesAwardVo>> list(@Valid @RequestBody EmployeeMesAwardQueryForm employeeMesAwardQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesAwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesAwardVo>> search(@Valid @RequestBody EmployeeMesAwardQueryForm employeeMesAwardQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesAwardForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesAwardForm employeeMesAwardForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesAwardForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesAwardForm employeeMesAwardForm);
}

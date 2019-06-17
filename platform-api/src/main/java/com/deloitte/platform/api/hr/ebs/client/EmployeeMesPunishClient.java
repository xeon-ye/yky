package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesPunishQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesPunishForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesPunishVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesPunish控制器接口
 * @Modified :
 */
public interface EmployeeMesPunishClient {

    public static final String path="/hr/employee-mes-punish";


    /**
     *  POST---新增
     * @param employeeMesPunishForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesPunishForm employeeMesPunishForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesPunishForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesPunishForm employeeMesPunishForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesPunishVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesPunishQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesPunishVo>> list(@Valid @RequestBody EmployeeMesPunishQueryForm employeeMesPunishQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesPunishQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesPunishVo>> search(@Valid @RequestBody EmployeeMesPunishQueryForm employeeMesPunishQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesPunishForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesPunishForm employeeMesPunishForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesPunishForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesPunishForm employeeMesPunishForm);
}

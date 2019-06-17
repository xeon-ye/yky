package com.deloitte.platform.api.hr.ebs.client;


import com.deloitte.platform.api.hr.ebs.vo.EMployeeFamilyCheckForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeFamilyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeFamily控制器接口
 * @Modified :
 */
public interface EmployeeFamilyClient {

    public static final String path="/hr/employee-family";


    /**
     *  POST---新增
     * @param formList
     * @return
     */
    @PostMapping(value = path+"/add")
    Result add(@RequestParam String formList,@RequestParam Integer type);


    /**
     *  根据职工编号code获取人员家庭成员及社会关系数据
     *
     * @param userCode  人员信息CODE（非必填，当前登录用户）
     * @return
     */
    @ApiOperation(value = "", notes = "根据职工编号code获取人员家庭成员及社会关系数据信息")
    @GetMapping(value = path+"/getEmployeeFamilyByEmpCode")
    Result<List<EmployeeFamilyVo>> getEmployeeFamilyByEmpCode(@RequestParam(required = false) String userCode);

    /**
     *  成员关系申请信息审核
     *
     * @param eMployeeFamilyCheckForm 审批信息
     * @return
     */
    @PostMapping(value = path+"/checkApplyById")
    Result checkApplyById(@Valid @RequestBody EMployeeFamilyCheckForm eMployeeFamilyCheckForm);
}

package com.deloitte.platform.api.hr.ebs.client;



import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesContractForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesContractVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesContract控制器接口
 * @Modified :
 */
public interface EmployeeMesContractClient {

    public static final String path="/hr/employee-mes-contract";


    /**
     *  POST---新增
     * @param employeeMesContractForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesContractForm employeeMesContractForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesContractForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody EmployeeMesContractForm employeeMesContractForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesContractVo> get(@PathVariable(value="id") long id);


    /**
     *  根据职工编号code获取人员聘用合同
     *
     * @param userCode  人员信息CODE（非必填，当前登录用户）
     * @return
     */
    @ApiOperation(value = "根据职工编号code获取人员聘用合同", notes = "根据指定职工编号code获取人员聘用合同EmployeeMesContract信息")
    @GetMapping(value = path+"/getEmployeeMesContractByEmpCode")
    Result<EmployeeMesContractVo> getEmployeeMesContractByEmpCode(@RequestParam(required = false) String userCode);
}

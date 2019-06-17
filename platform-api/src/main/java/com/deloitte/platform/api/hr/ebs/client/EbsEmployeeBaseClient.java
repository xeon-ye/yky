package com.deloitte.platform.api.hr.ebs.client;

import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsEmployeeBaseClient
 * @ProjectName platform
 * @Description: TODO
 * @date 9:18  2019/6/4
 */
public interface EbsEmployeeBaseClient {

    public static final String path="/hr/ebs-employee-base";

    /**
     * 更新人员基本信息
     * @param employeeBaseForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeBaseForm employeeBaseForm);


    /**
     * 推送到人员基本信息EBS
     * @param employeeBaseForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeBaseForm employeeBaseForm);
}

package com.deloitte.platform.api.hr.ebs.client;

import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesPilitForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsEmployeeMesPilitClient
 * @ProjectName platform
 * @Description: TODO
 * @date 9:18  2019/6/4
 */
public interface EbsEmployeeMesPilitClient {

    public static final String path="/hr/ebs-employee-mes-pilit";

    /**
     * 更新人员基本信息
     * @param employeeMesPilitForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesPilitForm employeeMesPilitForm);

}

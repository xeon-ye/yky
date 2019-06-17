package com.deloitte.platform.api.hr.ebs.client;

import com.deloitte.platform.api.hr.employee_mes.vo.EmployeeMesLearnForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsEmployeeMesLearnClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
public interface EbsEmployeeMesLearnClient {

    public static final String path="/hr/ebs-employee-mes-learn";

    /**
     * 更新单位
     * @param employeeMesLearnForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesLearnForm employeeMesLearnForm);
}

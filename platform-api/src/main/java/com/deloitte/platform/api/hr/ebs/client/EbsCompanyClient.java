package com.deloitte.platform.api.hr.ebs.client;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;
import com.deloitte.platform.api.hr.common.vo.HrDeptForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsCompanyClient
 * @ProjectName platform
 * @Description: TODO
 * @date 9:18  2019/6/4
 */
public interface EbsCompanyClient {

    public static final String path="/hr/ebs-company";

    /**
     * 更新单位
     * @param hrDeptForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody HrDeptForm hrDeptForm);

}

package com.deloitte.platform.api.hr.ebs.client;

import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author woo
 * @Title: EbsOrganizationClient
 * @ProjectName platform
 * @Description: TODO
 * @date 14:27  2019/6/4
 */
public interface EbsOrganizationClient {

    public static final String path="/hr/ebs-organization";

    /**
     * 更新单位
     * @param organizationForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody OrganizationForm organizationForm);
}

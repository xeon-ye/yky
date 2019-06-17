package com.deloitte.services.isump.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取
 * @author admin
 */
@Component
@PropertySource("classpath:config.properties")
public class ConfigPropertiesUtil {

    @Value("${system.name.fssc}")
    public String systemNameFssc;

    @Value("${system.name.srpmp}")
    public String systemNameSrpmp;

    @Value("${system.name.oaservice}")
    public String systemNameOaService;

    @Value("${system.name.hr}")
    public String systemNameHR;

    @Value("${system.name.contract}")
    public String systemNameContract;

    @Value("${system.name.dssv1}")
    public String systemNameDssv1;

    @Value("${fssc.role}")
    public String fsscRole;

    @Value("${srpmp.role}")
    public String srpmpRole;

    @Value("${oaservice.role}")
    public String oaserviceRole;

    @Value("${hr.role}")
    public String hrRole;

    @Value("${contract.role}")
    public String contractRole;

    @Value("${dssv1.role}")
    public String dssv1Role;

    @Value("${user.register.processDefineKey}")
    public String userRegisterProcessDefineKey;

    @Value("${user.register.sourceSystem}")
    public String userRegisterSourceSystem;

    @Value("${user.register.objectType}")
    public String userRegisterObjectType;

    @Value("${org.register.taskId}")
    public String orgRegisterTaskId;

    @Value("${org.register.chargeOrg}")
    public String orgRegisterChargeOrg;

    @Value("${org.register.processDefineKey}")
    public String orgRegisterProcessDefineKey;

    @Value("${org.register.sourceSystem}")
    public String orgRegisterSourceSystem;

    @Value("${org.register.objectType}")
    public String orgrRegisterObjectType;

    @Value("${fssc.amdin.id}")
    public String fsscAdminId;

    @Value("${fssc.amdin.name}")
    public String fsscAdminName;

    @Value("${srpmp.amdin.id}")
    public String srpmpAdminId;

    @Value("${srpmp.amdin.name}")
    public String srpmpAdminName;

    @Value("${oaservice.amdin.id}")
    public String oaserviceAdminId;

    @Value("${oaservice.amdin.name}")
    public String oaserviceAdminName;

    @Value("${hr.amdin.id}")
    public String hrAdminId;

    @Value("${hr.amdin.name}")
    public String hrAdminName;

    @Value("${contract.amdin.id}")
    public String contractAdminId;

    @Value("${contract.amdin.name}")
    public String contractAdminName;

    @Value("${dssv1.amdin.id}")
    public String dssv1AdminId;

    @Value("${dssv1.amdin.name}")
    public String dssv1AdminName;

}

package com.deloitte.services.fssc.business.bpm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 审批人配置类，拥有默认值，需要另外配置的话 在yml里面配置 即可覆盖
 */
@ConfigurationProperties(prefix = "bpm.fssc")
@Component
@Data
public class ApproveProperties {

    //行政基建处orgCode
    private String xzjj_code="1001019";

    //院校办公室code
    private String yxbgs_code="1001002";

    //科技管理处code
    private String kjglc_code="1001046";

    //财务处code
    private String cwc_code="1001011";

    //后勤服务中心
    private String hqfwqzx_code="1001020";

    //资产管理部门员工编号
    private String zcglsprzh="CW000010";

    //全过程审计员工编号
    private String qgcsjzh="CW000018";

    //核算主管工号
    private String hxzgzh="CW000019";

    //财务会计工号
    private String cwzh="CW000017";

    //出纳工号
    private String cnzh="CW000018";
}

package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-26
 * @Description :   ZpcpAuditRecord查询from对象
 * @Modified :
 */
@ApiModel("ZpcpAuditRecord查询表单")
@Data
public class ZpcpAuditRecordQueryForm extends BaseQueryForm<ZpcpAuditRecordQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申报表id")
    private Long declareId;

    @ApiModelProperty(value = "2.聘任工作组审核未通过,3.聘任工作组审核通过,4学术委员会审核未通过,5.学术委员会审核通过,6.教授委员会审核未通过,7.教授委员会审核通过,8.教职聘任委员会审核未通过,9.教职聘任委员会审核通过")
    private String checkStatus;

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "更新人id")
    private String updateBy;

    @ApiModelProperty(value = "组织机构")
    private String organizationCode;

    @ApiModelProperty(value = "备注")
    private String remarks;
}

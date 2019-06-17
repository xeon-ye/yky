package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : AuditingHistory新增修改form对象
 * @Modified :
 */
@ApiModel("新增AuditingHistory表单")
@Data
public class AuditingHistoryForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请人ID（关联用户表）")
    private Long applyUserId;

    @ApiModelProperty(value = "审核人ID（关联用户表）")
    private Long auditingUserId;

    @ApiModelProperty(value = "申请审核的业务ID")
    private Long businessId;

    @ApiModelProperty(value = "审核事项")
    private String auditingBusinessItem;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditingTime;

    @ApiModelProperty(value = "审核结果")
    private String auditingResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人ID")
    private Long cretaeBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

}

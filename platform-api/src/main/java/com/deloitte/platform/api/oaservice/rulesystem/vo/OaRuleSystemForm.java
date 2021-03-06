package com.deloitte.platform.api.oaservice.rulesystem.vo;

import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentForm;
import com.deloitte.platform.api.oaservice.noticeper.vo.OaNoticePermissionForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description : OaRuleSystem新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaRuleSystem表单")
@Data
public class OaRuleSystemForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "${field.comment}")
    private String title;

    @ApiModelProperty(value = "紧急程度")
    private String urgentLevel;

    @ApiModelProperty(value = "规则字段类别Code，取自字典")
    private String ruleSortCode;

    @ApiModelProperty(value = "规则字段类别，取自字典，例：办公制度、财务制度、人事制度、科技管理制度、其他制度")
    private String ruleSortName;

    @ApiModelProperty(value = "申请部门Code")
    private String applyOrgCode;

    @ApiModelProperty(value = "申请部门")
    private String applyOrgName;

    @ApiModelProperty(value = "申请用户ID")
    private String applyUserId;

    @ApiModelProperty(value = "申请用户")
    private String applyUserName;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "是否删除，默认0， 0，否，1，是")
    private Integer delFlag;

    @ApiModelProperty(value = "公告正文")
    private String ruleContent;

    @ApiModelProperty(value = "附件列表")
    private List<OaAttachmentForm> attachments;

    @ApiModelProperty(value = "点击次数")
    private Integer ruleHit;

    @ApiModelProperty(value = "审批状态，默认0，0，审核中，1，审批完成")
    private String approvalStatus;

    @ApiModelProperty(value = "是否需要业务负责人审批，默认0否，1，是，0，否")
    private Integer isneedBussiness;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDatetime;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "更新人id")
    private String updateUserId;

    @ApiModelProperty(value = "流水号")
    private String orderNum;

    @ApiModelProperty(value = "申请单位code")
    private String applyDeptCode;

    @ApiModelProperty(value = "部门权限列表")
    private List<OaNoticePermissionForm> permissionDepts;

    @ApiModelProperty(value = "单位权限: inter 内部单位, outer 外部单位")
    private String deptPer;
}

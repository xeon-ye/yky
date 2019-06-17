package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpDrugDevice新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpDrugDevice表单")
@Data
public class ZpcpDrugDeviceForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "原创新药和医疗器械")
    private String drugDevice;

    @ApiModelProperty(value = "类别")
    private String type;

    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    @ApiModelProperty(value = "审批阶段")
    private String approvalStage;

    @ApiModelProperty(value = "完成情况排名")
    private String completionRanking;

    @ApiModelProperty(value = "所获分数")
    private String score;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "文件对象")
    private HrAttachmentForm attachmentVo;

}

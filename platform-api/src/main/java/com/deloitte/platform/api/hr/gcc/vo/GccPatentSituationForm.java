package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccPatentSituation新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccPatentSituation表单")
@Data
public class GccPatentSituationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "授予国家")
    private String awardedNation;

    @ApiModelProperty(value = "专利所有者")
    private String patentOwenr;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "专利号")
    private String patentNumber;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;
}

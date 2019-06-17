package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccTextbookCompilation新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccTextbookCompilation表单")
@Data
public class GccTextbookCompilationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员编号")
    private Long Id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "教材名称")
    private String bookName;

    @ApiModelProperty(value = "出版社")
    private String publishing;

    @ApiModelProperty(value = "出版年份")
    private String publishYear;

    @ApiModelProperty(value = "编著情况")
    private String compile;

    @ApiModelProperty(value = "排名")
    private String ranking;

    @ApiModelProperty(value = "成效情况")
    private String results;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;
}

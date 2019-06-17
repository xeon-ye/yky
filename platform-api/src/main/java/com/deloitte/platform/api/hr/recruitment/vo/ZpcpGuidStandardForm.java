package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpGuidStandard新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpGuidStandard表单")
@Data
public class ZpcpGuidStandardForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "规则指南名称")
    private String guidStandardName;

    @ApiModelProperty(value = "发布时间")
    private LocalDate publishTime;

    @ApiModelProperty(value = "发布单位")
    private String publishUnit;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "级别")
    private String grade;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "文件保存对象")
    private HrAttachmentForm attachmentVo;

}

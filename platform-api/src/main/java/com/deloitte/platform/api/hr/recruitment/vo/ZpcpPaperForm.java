package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpPaper新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpPaper表单")
@Data
public class ZpcpPaperForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "发布时间")
    private LocalDate publishTime;

    @ApiModelProperty(value = "论文题目")
    private String paperTitle;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否为通讯作者（0.不是，1.是）")
    private String isCorrespondingAuthor;

    @ApiModelProperty(value = "期刊影响因子")
    private String impactFactor;

    @ApiModelProperty(value = "发表刊物")
    private String publication;

    @ApiModelProperty(value = "是否为代表性论文（0.不是，1.是）")
    private String isRepresentative;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

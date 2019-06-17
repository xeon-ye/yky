package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpPatent新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpPatent表单")
@Data
public class ZpcpPatentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "授权专利名称")
    private String patentName;

    @ApiModelProperty(value = "发明人")
    private String inventor;

    @ApiModelProperty(value = "专利所有者")
    private String patentOwner;

    @ApiModelProperty(value = "专利号")
    private String patentNo;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "国别")
    private String country;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

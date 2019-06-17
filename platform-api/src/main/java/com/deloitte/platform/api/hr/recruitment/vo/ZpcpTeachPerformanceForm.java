package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpTeachPerformance新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpTeachPerformance表单")
@Data
public class ZpcpTeachPerformanceForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "教学业绩名称")
    private String teachName;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "级别")
    private String grade;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

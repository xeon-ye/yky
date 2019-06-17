package com.deloitte.platform.api.hr.employee_mes.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description : EmployeeMesTeach新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesTeach表单")
@Data
public class EmployeeMesTeachForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "入学日期")
    private LocalDate segment1;

    @ApiModelProperty(value = "毕/肄业日期")
    private LocalDate segment2;

    @ApiModelProperty(value = "毕/肄业学校名称")
    private String segment3;

    @ApiModelProperty(value = "毕业专业名称")
    private String segment5;

    @ApiModelProperty(value = "学历")
    private String segment6;

    @ApiModelProperty(value = "学位")
    private String segment8;

    @ApiModelProperty(value = "学制")
    private String segment12;

    @ApiModelProperty(value = "教育形式")
    private String segment11;

    @ApiModelProperty(value = "是否是最高学历")
    private String segment13;

    @ApiModelProperty(value = "是否是第一学历")
    private String segment14;

    @ApiModelProperty(value = "毕业类型")
    private String segment15;

    @ApiModelProperty(value = "是否留学回国人士")
    private String segment16;

    @ApiModelProperty(value = "留学国家")
    private String segment17;

    @ApiModelProperty(value = "留学类型")
    private String overseasStudy;

    @ApiModelProperty(value = "留学出国时间")
    private LocalDate segment18;

    @ApiModelProperty(value = "留学回国时间")
    private LocalDate segment19;

    @ApiModelProperty(value = "备注")
    private String segment20;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    private String empMesId;
    @ApiModelProperty(value = "教职工编码")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "是否本校毕业")
    private String segment21;

    @ApiModelProperty(value = "学历（统计）")
    private String segment22;

    @ApiModelProperty(value = "学位（统计）")
    private String segment23;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "特殊信息名称")
    private String specialInformation;
}

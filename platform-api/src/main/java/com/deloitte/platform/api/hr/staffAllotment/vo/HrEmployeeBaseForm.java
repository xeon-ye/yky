package com.deloitte.platform.api.hr.staffAllotment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description : HrEmployeeBase新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrEmployeeBase表单")
@Data
public class HrEmployeeBaseForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "头像存放位置")
    private String photourl;

    @ApiModelProperty(value = "教职工ID")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别 1 男 2女 3不详")
    private String gender;

    @ApiModelProperty(value = "部门编码")
    private String depCode;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "EBS系统中字段")
    private String ebsCode;

    @ApiModelProperty(value = "身份证")
    private String idNumber;

    @ApiModelProperty(value = "个人身份")
    private String identity;

    @ApiModelProperty(value = "在岗状态 1 在岗 2 离岗 3 其他")
    private String isonduty;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birth;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "籍贯")
    private String natives;

    @ApiModelProperty(value = "出生地")
    private String birthAddress;

    @ApiModelProperty(value = "国籍")
    private String nationality;

    @ApiModelProperty(value = "名族")
    private String nation;

    @ApiModelProperty(value = "婚姻状况")
    private String ismarriage;

    @ApiModelProperty(value = "单位邮箱")
    private String companyEmail;

    @ApiModelProperty(value = "个人邮箱")
    private String personalEmail;

    @ApiModelProperty(value = "单位电话")
    private String companyPhone;

    @ApiModelProperty(value = "个人电话")
    private String mobilePhone;

    @ApiModelProperty(value = "参加工作时间")
    private LocalDateTime workingTime;

    @ApiModelProperty(value = "进入系统时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "岗位级别")
    private String positionLevel;

    @ApiModelProperty(value = "单位")
    private String company;

}

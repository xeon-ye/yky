package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-08
 * @Description :   EmployeeMesBase查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesBase查询表单")
@Data
public class EmployeeMesBaseQueryForm extends BaseQueryForm<EmployeeMesBaseQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Double id;

    @ApiModelProperty(value = "教职工编号")
    private String employeeNumber;

    @ApiModelProperty(value = "姓名")
    private String fullName;

    @ApiModelProperty(value = "证件类型")
    private String attribute1;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "人员类型ID")
    private Long personTypeId;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "工作岗位类别")
    private String attribute2;

    @ApiModelProperty(value = "个人身份")
    private String attribute3;

    @ApiModelProperty(value = "籍贯")
    private String townOfBirth;

    @ApiModelProperty(value = "出生日期")
    private String dateOfBirth;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "出生地")
    private String regionOfBirth;

    @ApiModelProperty(value = "国籍")
    private String nationality;

    @ApiModelProperty(value = "民族")
    private String perInformation17;

    @ApiModelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value = "电子邮件类型")
    private String emailType;

    @ApiModelProperty(value = "电子邮件")
    private String emailAddress;

    @ApiModelProperty(value = "电话号码类型")
    private String phoneType;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "户口类型")
    private String perInformation4;

    @ApiModelProperty(value = "户口所在地")
    private String perInformation5;

    @ApiModelProperty(value = "参加工作时间")
    private LocalDateTime attribute8;

    @ApiModelProperty(value = "进入本系统时间")
    private LocalDateTime originalDateOfHire;

    @ApiModelProperty(value = "参工后工作年限间断开始时间")
    private String segment1;

    @ApiModelProperty(value = "参工后工作年限间断结束时间")
    private String segment2;

    @ApiModelProperty(value = "参工后工作年限间断原因")
    private String segment3;

    @ApiModelProperty(value = "参工后工作年限间断时长（年）")
    private String segment4;

    @ApiModelProperty(value = "参工后工作年限间断时长（月）")
    private String segment5;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}

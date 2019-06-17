package com.deloitte.platform.api.hr.employee.vo;

import com.deloitte.platform.api.hr.ebs.vo.EmployeeFamilyForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description : EmployeeBase新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeBase表单")
@Data
public class EmployeeBaseForm extends BaseForm {
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

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "岗位级别")
    private String positionLevel;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "员工家庭信息")
    private List<EmployeeFamilyForm> familyForms;

    @ApiModelProperty(value = "员工以前工作信息")
    private List<EmployeeLastworkForm> employeeLastworkForms;

    @ApiModelProperty(value = "员工教育信息")
    private List<EmployeePoliticalForm> employeePoliticalForms;

    @ApiModelProperty(value = "员工政治面貌信息")
    private List<EmployeeTeachForm> employeeTeachForms;

    @ApiModelProperty(value = "员工表ID")
    private String Id;

    @ApiModelProperty(value = "出生年份")
    private String birthYear;

    @ApiModelProperty(value = "用户id")
    private Long accountId;

    @ApiModelProperty(value = "专家职称")
    private String expertsTitles;

    @ApiModelProperty(value = "个人银行账号")
    private String bankNumber;

    @ApiModelProperty(value = "开户行")
    private String openBank;

    @ApiModelProperty(value = "银联号")
    private String unionpay;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "处级级别")
    private String chiefRank;

    @ApiModelProperty(value = "拟退休日期")
    private LocalDate retireDate;

    @ApiModelProperty(value = "拟返聘截止日期")
    private LocalDate rebateData;

    @ApiModelProperty(value = "健康状况")
    private String healthCondition;
    
}

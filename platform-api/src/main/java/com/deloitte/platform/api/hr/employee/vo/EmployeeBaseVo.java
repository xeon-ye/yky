package com.deloitte.platform.api.hr.employee.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-05-10
 * @Description : EmployeeBase返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBaseVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

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

    @ApiModelProperty(value = "身份证(证件编号)")
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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "岗位级别")
    private String positionLevel;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "用户id")
    private String accountId;

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

    @ApiModelProperty(value = "出生年份")
    private String birthYear;

    @ApiModelProperty(value = "院校编码")
    private String institution;

    @ApiModelProperty(value = "职称编码")
    private String titelCode;

    @ApiModelProperty(value = "专家级别")
    private String expertLevle;

    @ApiModelProperty(value = "组织编码")
    private String organizationCode;

    @ApiModelProperty(value = "1 处级以下 2处级及以上")
    private String chiefRank;

    @ApiModelProperty(value = "证件类型")
    private String idType;

    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "最高学位")
    private String degree;

    @ApiModelProperty(value = "毕业院校")
    private String academy;

    @ApiModelProperty(value = "毕业专业")
    private String major;

    @ApiModelProperty(value = "从事专业")
    private String profession;

    @ApiModelProperty(value = "专业技术职务")
    private String specTechJob;

    @ApiModelProperty(value = "专业研究方向")
    private String researchDir;

    @ApiModelProperty(value = "研究生导师资格")
    private String tutorQualification;

    @ApiModelProperty(value = "一级学科")
    private String subject1;

    @ApiModelProperty(value = "二级学科")
    private String subject2;

    @ApiModelProperty(value = "三级学科")
    private String subject3;

    @ApiModelProperty(value = "专家级别")
    private String expertLevel;

    @ApiModelProperty(value = "专业技术职务系列")
    private String specTechSeries;

    @ApiModelProperty(value = "入职时间")
    private LocalDateTime entryTime;

    @ApiModelProperty(value = "是否属于干部 0 机关职工 1 机关职工处级，副高级女干部 2 所院高级干部 ")
    private String iscadre;

    @ApiModelProperty(value = "0 内部编制 1 外部编制 2离职")
    private String isstrength;

    @ApiModelProperty(value = "离职日期")
    private LocalDate quitDate;

    @ApiModelProperty(value = "现具备未聘任的专业技术资格名称")
    private String technologyNow;

    @ApiModelProperty(value = "现具备未聘任的专业技术资格的取得时间")
    private LocalDateTime getdate;

    @ApiModelProperty(value = "审批单位")
    private String approvalCompany;

    @ApiModelProperty(value = "聘任的专业技术资格起始时间")
    private LocalDateTime technologyStart;

    @ApiModelProperty(value = "聘任的专业技术资格结束时间")
    private LocalDateTime technologyEnd;

    @ApiModelProperty(value = "毕业时间")
    private LocalDateTime graduationDate;

    @ApiModelProperty(value = "户口类型")
    private String residenceType;

    @ApiModelProperty(value = "户口所在地")
    private String residenceAddress;

    @ApiModelProperty(value = "0 未残疾 1残疾")
    private String isdeformed;

    @ApiModelProperty(value = "参工后工作年限间断时长（月）")
    private String joinIntervalM;

    @ApiModelProperty(value = "参工后工作年限间断时长（年)")
    private String joinIntervalY;

    @ApiModelProperty(value = "参工后工作年限间断原因")
    private String joinIntervalReason;

    @ApiModelProperty(value = "参工后工作年限间断开始时间")
    private LocalDateTime joinIntervalStart;

    @ApiModelProperty(value = "参工后工作年限间断结束时间")
    private LocalDateTime joinIntervalEnd;

    @ApiModelProperty(value = "人员类型")
    private String personType;

    @ApiModelProperty(value = "退休日期")
    private LocalDate retireDate;

    @ApiModelProperty(value = "拟返聘截止日期")
    private LocalDate rebateData;

    @ApiModelProperty(value = "政治面貌")
    private String pilitName;

    @ApiModelProperty(value = "健康状况")
    private String healthCondition;

    @ApiModelProperty(value = "健康状况(汉字)")
    private String healthConditionCn;

    @ApiModelProperty(value = "专业技术职务(汉字)")
    private String specTechJobCn;
	
	private String postName;

    @ApiModelProperty(value = "所在单位类型 0：内部单位  1：供应商  2：外部注册单位")
    private String deptGroupType;

}

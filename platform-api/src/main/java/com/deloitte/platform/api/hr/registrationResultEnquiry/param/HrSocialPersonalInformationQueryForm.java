package com.deloitte.platform.api.hr.registrationResultEnquiry.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :   HrSocialPersonalInformation查询from对象
 * @Modified :
 */
@ApiModel("HrSocialPersonalInformation查询表单")
@Data
public class HrSocialPersonalInformationQueryForm extends BaseQueryForm<HrSocialPersonalInformationQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别：男，女")
    private String gender;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "出生年月")
    private LocalDateTime dateOfBirth;

    @ApiModelProperty(value = "身高")
    private Double height;

    @ApiModelProperty(value = "血型")
    private String bloodType;

    @ApiModelProperty(value = "政治面貌")
    private String politicsStatus;

    @ApiModelProperty(value = "婚否")
    private String maritalStatus;

    @ApiModelProperty(value = "最高学历")
    private String highestEducation;

    @ApiModelProperty(value = "证件类型")
    private String certificateType;

    @ApiModelProperty(value = "证件号码")
    private String certificateNumber;

    @ApiModelProperty(value = "最高学位")
    private String highestDegree;

    @ApiModelProperty(value = "毕业院校")
    private String graduateSchool;

    @ApiModelProperty(value = "所学专业")
    private String major;

    @ApiModelProperty(value = "照片地址")
    private String photoPath;

    @ApiModelProperty(value = "外语水平")
    private String foreignLanguageLevel;

    @ApiModelProperty(value = "计算机水平")
    private String computerAbility;

    @ApiModelProperty(value = "户口所在地")
    private String householdLocation;

    @ApiModelProperty(value = "户口性质：农村，城市")
    private String householdType;

    @ApiModelProperty(value = "目前工作单位")
    private String currentEmployer;

    @ApiModelProperty(value = "工作部门")
    private String department;

    @ApiModelProperty(value = "职称/技术等级")
    private String technicalLevel;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "期望月薪")
    private Double expectedMonthlySalary;

    @ApiModelProperty(value = "通讯地址及邮编")
    private String mailingAddress;

    @ApiModelProperty(value = "手机号码")
    private String cellPhoneNumber;

    @ApiModelProperty(value = "座机号码")
    private String landlineNumber;

    @ApiModelProperty(value = "家庭住址")
    private String familyAddress;

    @ApiModelProperty(value = "电子邮箱")
    private String emailAddress;

    @ApiModelProperty(value = "应聘岗位及编号")
    private String positionAndNumber;

    @ApiModelProperty(value = "现居住城市")
    private String currentCity;

    @ApiModelProperty(value = "工作单位及主要内容或科研课题及主要成果")
    private String mainAchievements;

    @ApiModelProperty(value = "所获奖励或荣誉名称及授予单位")
    private String rewards;

    @ApiModelProperty(value = "自我评价")
    private String selfAssessment;

    @ApiModelProperty(value = "状态：已提交，未提交")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;
}

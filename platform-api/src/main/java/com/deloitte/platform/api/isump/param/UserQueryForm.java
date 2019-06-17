package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   User查询from对象
 * @Modified :
 */
@ApiModel("User查询表单")
@Data
public class UserQueryForm extends BaseQueryForm<UserQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "员工号")
    private String empNo;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "头衔，职称")
    private String honor;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "擅长领域")
    private String expertise;

    @ApiModelProperty(value = "学科")
    private String subject;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "从事专业")
    private String major;

    @ApiModelProperty(value = "固定电话")
    private String tel;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "证件类型")
    private String idCardType;

    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    private String educationCountry;

    @ApiModelProperty(value = "所在单位")
    private String dept;

    @ApiModelProperty(value = "每年工作时间")
    private Integer workPerYear;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    @ApiModelProperty(value = "依托基地名称")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划")
    private String talentPlan;

    @ApiModelProperty(value = "源人员ID")
    private String sourcePersonId;

    @ApiModelProperty(value = "学位")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "单位")
    private String org;
    @ApiModelProperty(value = "单位ID")
    private String orgId;
    @ApiModelProperty(value = "国别及地区")
    private String country;
    @ApiModelProperty(value = "名族")
    private String nation;
    @ApiModelProperty(value = "授予年份")
    private String educationYear;
    @ApiModelProperty(value = "排除人员ID集合")
    private List<String> excludeIds;
    @ApiModelProperty(value = "民族")
    private String faculty;
    @ApiModelProperty(value = "教职工编号集合，用于条件in")
    private List<String> empNos;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "token")
    private String token;


    @ApiModelProperty(value = "出生地")
    private String birthPlace;

    @ApiModelProperty(value = "政治面貌")
    private String ploSta;

    @ApiModelProperty(value = "管理岗位等级")
    private String managePositionRank;

    @ApiModelProperty(value = "默认副账号ID")
    private Long deputyAccountId;

    @ApiModelProperty(value = "研究成果")
    private String researchResults;

    @ApiModelProperty(value = "职位级别")
    private String positionLevel;

    @ApiModelProperty(value = "id集合：idList")
    private List<Long> idList;

    private String orgCode;

}

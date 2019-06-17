package com.deloitte.services.isump.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jianglong
 * @since 2019-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_USER")
@ApiModel(value="User对象", description="用户表")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "员工号")
    @TableField("EMP_NO")
    private String empNo;

    @ApiModelProperty(value = "电话号码")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "邮件")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "密码盐")
    @TableField("SALT")
    private String salt;

    @ApiModelProperty(value = "头像")
    @TableField("AVATAR")
    private String avatar;

    @ApiModelProperty(value = "头衔")
    @TableField("HONOR")
    private String honor;

    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    @TableField("RESERVE")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    @TableField("VERSION")
    private String version;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "擅长领域")
    @TableField("EXPERTISE")
    private String expertise;

    @ApiModelProperty(value = "学科")
    @TableField("SUBJECT")
    private String subject;

    @ApiModelProperty(value = "性别")
    @TableField("GENDER")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    @TableField("BIRTH_DATE")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称")
    @TableField("POSITION_TITLE")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历")
    @TableField("EDUCATION")
    private String education;

    @ApiModelProperty(value = "从事专业")
    @TableField("MAJOR")
    private String major;

    @ApiModelProperty(value = "固定电话")
    @TableField("TEL")
    private String tel;

    @ApiModelProperty(value = "传真")
    @TableField("FAX")
    private String fax;

    @ApiModelProperty(value = "证件类型")
    @TableField("ID_CARD_TYPE")
    private String idCardType;

    @ApiModelProperty(value = "证件号码")
    @TableField("ID_CARD")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    @TableField("EDUCATION_COUNTRY")
    private String educationCountry;

    @ApiModelProperty(value = "所在单位")
    @TableField("DEPT")
    private String dept;

    @ApiModelProperty(value = "每年工作时间")
    @TableField("WORK_PER_YEAR")
    private Integer workPerYear;

    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "邮政编码")
    @TableField("ZIP_CODE")
    private String zipCode;

    @ApiModelProperty(value = "依托基地名称")
    @TableField("LIVE_BASE_NAME")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划")
    @TableField("TALENT_PLAN")
    private String talentPlan;

    @ApiModelProperty(value = "源人员ID")
    @TableField("SOURCE_PERSON_ID")
    private String sourcePersonId;

    @ApiModelProperty(value = "学位")
    @TableField("DEGREE")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    @TableField("OFFICE_NAME")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    @TableField("PROJECT_COMMITMENT_UNIT")
    private String projectCommitmentUnit;

    @TableField("TOKEN")
    private String token;

    @ApiModelProperty(value = "国别及地区")
    @TableField("COUNTRY")
    private String country;

    @ApiModelProperty(value = "民族")
    @TableField("NATION")
    private String nation;

    @ApiModelProperty(value = "授予年份")
    @TableField("EDUCATION_YEAR")
    private String educationYear;

    @ApiModelProperty(value = "院系")
    @TableField("FACULTY")
    private String faculty;

    @ApiModelProperty(value = "出生地")
    @TableField("BIRTH_PLACE")
    private String birthPlace;

    @ApiModelProperty(value = "政治面貌")
    @TableField("PLO_STA")
    private String ploSta;

    @ApiModelProperty(value = "管理岗位等级")
    @TableField("MANAGE_POSITION_RANK")
    private String managePositionRank;

    @ApiModelProperty(value = "默认副账号ID")
    @TableField("DEPUTY_ACCOUNT_ID")
    private Long deputyAccountId;

    @ApiModelProperty(value = "研究成果")
    @TableField("RESEARCH_RESULTS")
    private String researchResults;

    @ApiModelProperty(value = "是否第一次登陆")
    @TableField("FIRST_LOGIN")
    private String firstLogin;

    @ApiModelProperty(value = "职位级别")
    @TableField("POSITION_LEVEL")
    private String positionLevel;

    public static final String ID = "ID";

    public static final String FACULTY = "FACULTY";

    public static final String NAME = "NAME";

    public static final String EMP_NO = "EMP_NO";

    public static final String PHONE = "PHONE";

    public static final String EMAIL = "EMAIL";

    public static final String PASSWORD = "PASSWORD";

    public static final String SALT = "SALT";

    public static final String AVATAR = "AVATAR";

    public static final String HONOR = "HONOR";

    public static final String TYPE = "TYPE";

    public static final String STATE = "STATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXPERTISE = "EXPERTISE";

    public static final String SUBJECT = "SUBJECT";

    public static final String GENDER = "GENDER";

    public static final String BIRTH_DATE = "BIRTH_DATE";

    public static final String POSITION_TITLE = "POSITION_TITLE";

    public static final String EDUCATION = "EDUCATION";

    public static final String MAJOR = "MAJOR";

    public static final String TEL = "TEL";

    public static final String FAX = "FAX";

    public static final String ID_CARD_TYPE = "ID_CARD_TYPE";

    public static final String ID_CARD = "ID_CARD";

    public static final String EDUCATION_COUNTRY = "EDUCATION_COUNTRY";

    public static final String DEPT = "DEPT";

    public static final String WORK_PER_YEAR = "WORK_PER_YEAR";

    public static final String ADDRESS = "ADDRESS";

    public static final String ZIP_CODE = "ZIP_CODE";

    public static final String LIVE_BASE_NAME = "LIVE_BASE_NAME";

    public static final String TALENT_PLAN = "TALENT_PLAN";

    public static final String SOURCE_PERSON_ID = "SOURCE_PERSON_ID";

    public static final String DEGREE = "DEGREE";

    public static final String OFFICE_NAME = "OFFICE_NAME";

    public static final String PROJECT_COMMITMENT_UNIT = "PROJECT_COMMITMENT_UNIT";

    public static final String BIRTH_PLACE = "BIRTH_PLACE";

    public static final String PLO_STA = "PLO_STA";

    public static final String MANAGE_POSITION_RANK = "MANAGE_POSITION_RANK";

    public static final String DEPUTY_ACCOUNT_ID = "DEPUTY_ACCOUNT_ID";

    public static final String RESEARCH_RESULTS = "RESEARCH_RESULTS";

    public static final String FIRST_LOGIN = "FIRST_LOGIN";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

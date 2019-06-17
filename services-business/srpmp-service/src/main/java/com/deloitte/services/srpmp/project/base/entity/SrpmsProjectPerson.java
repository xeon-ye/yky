package com.deloitte.services.srpmp.project.base.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目人员信息表
 * </p>
 *
 * @author lixin
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_PERSON")
@ApiModel(value="SrpmsProjectPerson对象", description="项目人员信息表")
public class SrpmsProjectPerson extends Model<SrpmsProjectPerson> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "姓名")
    @TableField("PERSON_NAME")
    private String personName;

    @ApiModelProperty(value = "性别CODE")
    @TableField("GENDER")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    @TableField("BIRTH_DATE")
    private LocalDateTime birthDate;

    @ApiModelProperty(value = "职称CODE")
    @TableField("POSITION_TITLE")
    private String positionTitle;

    @ApiModelProperty(value = "最高学历CODE")
    @TableField("EDUCATION")
    private String education;

    @ApiModelProperty(value = "从事专业CODE")
    @TableField("MAJOR")
    private String major;

    @ApiModelProperty(value = "固定电话")
    @TableField("TEL_PHONE")
    private String telPhone;

    @ApiModelProperty(value = "移动电话")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty(value = "传真号码")
    @TableField("FAX_NUMBER")
    private String faxNumber;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "证件类型CODE")
    @TableField("ID_CARD_TYPE")
    private String idCardType;

    @ApiModelProperty(value = "证件号码")
    @TableField("ID_CARD")
    private String idCard;

    @ApiModelProperty(value = "学位授予国别")
    @TableField("EDUCATION_COUNTRY")
    private String educationCountry;

    @ApiModelProperty(value = "所在单位")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "每年工作时间")
    @TableField("WORK_PER_YEAR")
    private Integer workPerYear;

    @ApiModelProperty(value = "通讯地址及邮编")
    @TableField("ADDRESS_AND_ZIP")
    private String addressAndZip;

    @ApiModelProperty(value = "依托基地名称")
    @TableField("LIVE_BASE_NAME")
    private String liveBaseName;

    @ApiModelProperty(value = "入选人才计划CODE数组")
    @TableField("TALENT_PLAN")
    private String talentPlan;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "源人员ID")
    @TableField("SOURCE_PERSON_ID")
    private Long sourcePersonId;

    @ApiModelProperty(value = "学位")
    @TableField("DEGREE")
    private String degree;

    @ApiModelProperty(value = "所在科室")
    @TableField("OFFICE_NAME")
    private String officeName;

    @ApiModelProperty(value = "承担单位")
    @TableField("PROJECT_COMMITMENT_UNIT")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "通讯地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "邮编")
    @TableField("ZIP_CODE")
    private String zipCode;


    public static final String ID = "ID";

    public static final String PERSON_NAME = "PERSON_NAME";

    public static final String GENDER = "GENDER";

    public static final String BIRTH_DATE = "BIRTH_DATE";

    public static final String POSITION_TITLE = "POSITION_TITLE";

    public static final String EDUCATION = "EDUCATION";

    public static final String MAJOR = "MAJOR";

    public static final String TEL_PHONE = "TEL_PHONE";

    public static final String MOBILE = "MOBILE";

    public static final String FAX_NUMBER = "FAX_NUMBER";

    public static final String EMAIL = "EMAIL";

    public static final String ID_CARD_TYPE = "ID_CARD_TYPE";

    public static final String ID_CARD = "ID_CARD";

    public static final String EDUCATION_COUNTRY = "EDUCATION_COUNTRY";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String WORK_PER_YEAR = "WORK_PER_YEAR";

    public static final String ADDRESS_AND_ZIP = "ADDRESS_AND_ZIP";

    public static final String LIVE_BASE_NAME = "LIVE_BASE_NAME";

    public static final String TALENT_PLAN = "TALENT_PLAN";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SOURCE_PERSON_ID = "SOURCE_PERSON_ID";

    public static final String DEGREE = "DEGREE";

    public static final String OFFICE_NAME = "OFFICE_NAME";

    public static final String PROJECT_COMMITMENT_UNIT = "PROJECT_COMMITMENT_UNIT";

    public static final String ADDRESS = "ADDRESS";

    public static final String ZIP_CODE = "ZIP_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

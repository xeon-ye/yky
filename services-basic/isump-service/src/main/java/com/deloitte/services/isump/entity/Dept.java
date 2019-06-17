package com.deloitte.services.isump.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目单位信息表
 * </p>
 *
 * @author jianglong
 * @since 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_DEPT")
@ApiModel(value="Dept对象", description="项目单位信息表")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "邮编")
    @TableField("ZIP_CODE")
    private String zipCode;

    @ApiModelProperty(value = "通讯地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "联系人")
    @TableField("CONTACTS_NAME")
    private String contactsName;

    @ApiModelProperty(value = "电话")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "传真号码")
    @TableField("FAX_NUMBER")
    private String faxNumber;

    @ApiModelProperty(value = "单位性质CODE")
    @TableField("DEPT_QUALITY")
    private String deptQuality;

    @ApiModelProperty(value = "单位主管部门")
    @TableField("DEPT_MAN_DEPARTMENT")
    private String deptManDepartment;

    @ApiModelProperty(value = "单位隶属")
    @TableField("DEPT_SUBJECTION")
    private String deptSubjection;

    @ApiModelProperty(value = "单位法人姓名")
    @TableField("DEPT_LEGAL_PERSON_NAME")
    private String deptLegalPersonName;

    @ApiModelProperty(value = "单位所属地区省")
    @TableField("PROVINCE_CODE")
    private String provinceCode;

    @ApiModelProperty(value = "单位所属地区市")
    @TableField("CITY_CODE")
    private String cityCode;

    @ApiModelProperty(value = "单位所属地区县")
    @TableField("COUNTY_CODE")
    private String countyCode;

    @ApiModelProperty(value = "零余额账户-单位开户名称")
    @TableField("BANK_ACCOUNT_NAME_LOOSE")
    private String bankAccountNameLoose;

    @ApiModelProperty(value = "零余额账户-银行名称")
    @TableField("BANK_NAME_LOOSE")
    private String bankNameLoose;

    @ApiModelProperty(value = "零余额账户-银行账号")
    @TableField("BANK_ACCOUNT_NUMBER_LOOSE")
    private String bankAccountNumberLoose;

    @ApiModelProperty(value = "零余额账户-单位开户名称")
    @TableField("BANK_ACCOUNT_NAME_BASE")
    private String bankAccountNameBase;

    @ApiModelProperty(value = "零余额账户-银行名称")
    @TableField("BANK_NAME_BASE")
    private String bankNameBase;

    @ApiModelProperty(value = "零余额账户-银行账号")
    @TableField("BANK_ACCOUNT_NUMBER_BASE")
    private String bankAccountNumberBase;

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

    @ApiModelProperty(value = "源单位ID")
    @TableField("SOURCE_DEPT_ID")
    private Long sourceDeptId;

    @ApiModelProperty(value = "单位组织机构代码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "单位code")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "成立时间")
    @TableField("ESTABLISHED_TIME")
    private LocalDateTime establishedTime;

    @ApiModelProperty(value = "所有制形式")
    @TableField("OWNERSHIP_TYPE")
    private String ownershipType;

    @ApiModelProperty(value = "主管单位名称")
    @TableField("ADMIN_NAME")
    private String adminName;

    @ApiModelProperty(value = "单位法人性别")
    @TableField("DEPT_LEGAL_PERSON_GENDER")
    private String deptLegalPersonGender;

    @ApiModelProperty(value = "单位法人生日")
    @TableField("DEPT_LEGAL_PERSON_BOTHDAY")
    private LocalDateTime deptLegalPersonBothday;

    @ApiModelProperty(value = "单位法人专业")
    @TableField("DEPT_LEGAL_PERSON_JOB")
    private String deptLegalPersonJob;

    @ApiModelProperty(value = "单位法人最高学历")
    @TableField("DEPT_LEGAL_PERSON_EDUCATION")
    private String deptLegalPersonEducation;

    @ApiModelProperty(value = "单位法人职称")
    @TableField("DEPT_LEGAL_PERSON_JOBTITLE")
    private String deptLegalPersonJobtitle;

    @ApiModelProperty(value = "主要联系人名称")
    @TableField("MAIN_NAME")
    private String mainName;

    @ApiModelProperty(value = "主要联系人性别")
    @TableField("MAIN_GENDER")
    private String mainGender;

    @ApiModelProperty(value = "主要联系人生日")
    @TableField("MAIN_BOTHDAY")
    private LocalDateTime mainBothday;

    @ApiModelProperty(value = "主要联系人职称")
    @TableField("MAIN_JOB")
    private String mainJob;

    @ApiModelProperty(value = "主要联系人电话")
    @TableField("MAIN_TELL")
    private String mainTell;

    @ApiModelProperty(value = "主要联系人邮箱")
    @TableField("MAIN_EMAIL")
    private String mainEmail;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty(value = "单位类型")
    @TableField("DEPT_TYPE")
    private String deptType;

    @ApiModelProperty(value = "统一社会信用代码")
    @TableField("CREDIT_CODE")
    private String creditCode;

    @ApiModelProperty(value = "纳税人识别号")
    @TableField("TAXPAYER_NUMBER")
    private String taxpayerNumber;

    @ApiModelProperty(value = "开户银行")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "账户名称")
    @TableField("BANK_NAME")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    @TableField("BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @ApiModelProperty(value = "类型（0：内部  1：外部）")
    @TableField("GROUP_TYPE")
    private Integer groupType;

    @ApiModelProperty(value = "主要联系人移动电话")
    @TableField("MAIN_PHONE")
    private String mainPhone;

    @ApiModelProperty(value = "国家")
    @TableField("COUNTRY")
    private String country;

    @ApiModelProperty(value = "所属区域")
    @TableField("REGION")
    private String region;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;


    public static final String ID = "ID";

    public static final String STATE = "STATE";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String ZIP_CODE = "ZIP_CODE";

    public static final String ADDRESS = "ADDRESS";

    public static final String CONTACTS_NAME = "CONTACTS_NAME";

    public static final String PHONE = "PHONE";

    public static final String EMAIL = "EMAIL";

    public static final String FAX_NUMBER = "FAX_NUMBER";

    public static final String DEPT_QUALITY = "DEPT_QUALITY";

    public static final String DEPT_MAN_DEPARTMENT = "DEPT_MAN_DEPARTMENT";

    public static final String DEPT_SUBJECTION = "DEPT_SUBJECTION";

    public static final String DEPT_LEGAL_PERSON_NAME = "DEPT_LEGAL_PERSON_NAME";

    public static final String PROVINCE_CODE = "PROVINCE_CODE";

    public static final String CITY_CODE = "CITY_CODE";

    public static final String COUNTY_CODE = "COUNTY_CODE";

    public static final String BANK_ACCOUNT_NAME_LOOSE = "BANK_ACCOUNT_NAME_LOOSE";

    public static final String BANK_NAME_LOOSE = "BANK_NAME_LOOSE";

    public static final String BANK_ACCOUNT_NUMBER_LOOSE = "BANK_ACCOUNT_NUMBER_LOOSE";

    public static final String BANK_ACCOUNT_NAME_BASE = "BANK_ACCOUNT_NAME_BASE";

    public static final String BANK_NAME_BASE = "BANK_NAME_BASE";

    public static final String BANK_ACCOUNT_NUMBER_BASE = "BANK_ACCOUNT_NUMBER_BASE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SOURCE_DEPT_ID = "SOURCE_DEPT_ID";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String ESTABLISHED_TIME = "ESTABLISHED_TIME";

    public static final String OWNERSHIP_TYPE = "OWNERSHIP_TYPE";

    public static final String ADMIN_NAME = "ADMIN_NAME";

    public static final String DEPT_LEGAL_PERSON_GENDER = "DEPT_LEGAL_PERSON_GENDER";

    public static final String DEPT_LEGAL_PERSON_BOTHDAY = "DEPT_LEGAL_PERSON_BOTHDAY";

    public static final String DEPT_LEGAL_PERSON_JOB = "DEPT_LEGAL_PERSON_JOB";

    public static final String DEPT_LEGAL_PERSON_EDUCATION = "DEPT_LEGAL_PERSON_EDUCATION";

    public static final String DEPT_LEGAL_PERSON_JOBTITLE = "DEPT_LEGAL_PERSON_JOBTITLE";

    public static final String MAIN_NAME = "MAIN_NAME";

    public static final String MAIN_GENDER = "MAIN_GENDER";

    public static final String MAIN_BOTHDAY = "MAIN_BOTHDAY";

    public static final String MAIN_JOB = "MAIN_JOB";

    public static final String MAIN_TELL = "MAIN_TELL";

    public static final String MAIN_EMAIL = "MAIN_EMAIL";

    public static final String DEPT_TYPE = "DEPT_TYPE";

    public static final String CREDIT_CODE = "CREDIT_CODE";

    public static final String TAXPAYER_NUMBER = "TAXPAYER_NUMBER";

    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";

    public static final String BANK_NAME = "BANK_NAME";

    public static final String BANK_ACCOUNT_NUMBER = "BANK_ACCOUNT_NUMBER";

    public static final String GROUP_TYPE = "GROUP_TYPE";

    public static final String MAIN_PHONE = "MAIN_PHONE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

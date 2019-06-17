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
 * 项目单位信息表
 * </p>
 *
 * @author lixin
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_DEPT")
@ApiModel(value="SrpmsProjectDept对象", description="项目单位信息表")
public class SrpmsProjectDept extends Model<SrpmsProjectDept> {

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


    public static final String ID = "ID";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

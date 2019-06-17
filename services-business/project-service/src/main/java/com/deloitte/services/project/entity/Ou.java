package com.deloitte.services.project.entity;

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
 * 单位基本信息
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_OU")
@ApiModel(value="Ou对象", description="单位基本信息")
public class Ou extends Model<Ou> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位情况ID")
    @TableField("OU_ID")
    private String ouId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "单位编码")
    @TableField("OU_CODE")
    private String ouCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("OU_NAME")
    private String ouName;

    @ApiModelProperty(value = "所属部门CODE")
    @TableField("DEPARTMENT_CODE")
    private String departmentCode;

    @ApiModelProperty(value = "所属部门")
    @TableField("DEPARTMENT")
    private String department;

    @ApiModelProperty(value = "法人代表ID")
    @TableField("CORPORATE_REPRE_ID")
    private String corporateRepreId;

    @ApiModelProperty(value = "联系人ID")
    @TableField("CONTACTS_ID")
    private String contactsId;

    @ApiModelProperty(value = "编制人数")
    @TableField("OU_ORGANIZATION")
    private String ouOrganization;

    @ApiModelProperty(value = "实有人数")
    @TableField("ACTUAL_STAFF_NUMBER")
    private String actualStaffNumber;

    @ApiModelProperty(value = "专职科研人员数")
    @TableField("NUMBER__RESEARCHERS")
    private String numberResearchers;

    @ApiModelProperty(value = "离退休人员数")
    @TableField("NUMBER_OF_RETIREES")
    private String numberOfRetirees;

    @ApiModelProperty(value = "35-50岁中青年科研人员")
    @TableField("RESEARCHERS_AGED_35_50")
    private String researchersAged3550;

    @ApiModelProperty(value = "院士（人）")
    @TableField("ACADEMICIAN_QUANTITY")
    private String academicianQuantity;

    @ApiModelProperty(value = "在读博士生（人）")
    @TableField("PHD_QUANTITY")
    private String phdQuantity;

    @ApiModelProperty(value = "在读硕士生（人）")
    @TableField("MASTER_QUANTITY")
    private String masterQuantity;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "法人代表名称")
    @TableField("CORPORATE_REPRE_NAME")
    private String corporateRepreName;

    @ApiModelProperty(value = "联系人名称")
    @TableField("CONTACTS_NAME")
    private String contactsName;


    public static final String ID = "ID";

    public static final String OU_ID = "OU_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String OU_CODE = "OU_CODE";

    public static final String OU_NAME = "OU_NAME";

    public static final String DEPARTMENT_CODE = "DEPARTMENT_CODE";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String CORPORATE_REPRE_ID = "CORPORATE_REPRE_ID";

    public static final String CONTACTS_ID = "CONTACTS_ID";

    public static final String OU_ORGANIZATION = "OU_ORGANIZATION";

    public static final String ACTUAL_STAFF_NUMBER = "ACTUAL_STAFF_NUMBER";

    public static final String NUMBER__RESEARCHERS = "NUMBER__RESEARCHERS";

    public static final String NUMBER_OF_RETIREES = "NUMBER_OF_RETIREES";

    public static final String RESEARCHERS_AGED_35_50 = "RESEARCHERS_AGED_35_50";

    public static final String ACADEMICIAN_QUANTITY = "ACADEMICIAN_QUANTITY";

    public static final String PHD_QUANTITY = "PHD_QUANTITY";

    public static final String MASTER_QUANTITY = "MASTER_QUANTITY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String CORPORATE_REPRE_NAME = "CORPORATE_REPRE_NAME";

    public static final String CONTACTS_NAME = "CONTACTS_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

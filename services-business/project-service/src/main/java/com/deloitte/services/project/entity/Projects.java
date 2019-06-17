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
 * 项目表
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECTS")
@ApiModel(value="Projects对象", description="项目表")
public class Projects extends Model<Projects> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目属性")
    @TableField("PROJECT_ATTRIBUTE")
    private String projectAttribute;

    @ApiModelProperty(value = "项目类型code")
    @TableField("PROJECT_TYPE_CODE")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型名称")
    @TableField("PROJECT_TYPE_NAME")
    private String projectTypeName;

    @ApiModelProperty(value = "项目执行年度")
    @TableField("PLAN_YEAR")
    private String planYear;

    @ApiModelProperty(value = "项目周期")
    @TableField("CYCLE")
    private String cycle;

    @ApiModelProperty(value = "项目负责人ID")
    @TableField("PROJECT_HEADER_ID")
    private String projectHeaderId;

    @ApiModelProperty(value = "项目负责人名称")
    @TableField("PROJECT_HEADER_NAME")
    private String projectHeaderName;

    @ApiModelProperty(value = "财务负责人ID")
    @TableField("FIN_HEADER_ID")
    private String finHeaderId;

    @ApiModelProperty(value = "项目代管人ID")
    @TableField("PROJECT_CONNECT_STAFF_ID")
    private String projectConnectStaffId;

    @ApiModelProperty(value = "项目单位ID")
    @TableField("ORGANIZATION_ID")
    private String organizationId;

    @ApiModelProperty(value = "项目单位名称")
    @TableField("ORGANIZATION_NAME")
    private String organizationName;

    @ApiModelProperty(value = "一级项目名称")
    @TableField("PROJECT_CATGORY")
    private String projectCatgory;

    @ApiModelProperty(value = "项目取消原因描述")
    @TableField("PRO_CANCEL_DES")
    private String proCancelDes;

    @ApiModelProperty(value = "项目执行中项目变更原因")
    @TableField("PRO_CHANGE")
    private String proChange;

    @ApiModelProperty(value = "优先级排序")
    @TableField("PRIORITY")
    private String priority;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "拓展")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "项目状态code")
    @TableField("PROJECT_STATUS_CODE")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态名称")
    @TableField("PROJECT_STATUS_NAME")
    private String projectStatusName;

    @ApiModelProperty(value = "委托方id")
    @TableField("ENTRUST_ID")
    private String entrustId;

    @ApiModelProperty(value = "承担方id")
    @TableField("ASSUME_ID")
    private String assumeId;

    @ApiModelProperty(value = "是否未维护项目 1是 2 否")
    @TableField("PROJECT_MARK")
    private String projectMark;

    @ApiModelProperty(value = "财务负责人名称")
    @TableField("FIN_HEADER_NAME")
    private String finHeaderName;

    @ApiModelProperty(value = "项目代管人名称")
    @TableField("PROJECT_CONNECT_STAFF_NAME")
    private String projectConnectStaffName;

    @ApiModelProperty(value = "一级项目code")
    @TableField("PROJECT_CATGORY_CODE")
    private String projectCatgoryCode;

    @ApiModelProperty(value = "委托方")
    @TableField("ENTRUST_NAME")
    private String entrustName;

    @ApiModelProperty(value = "承担方")
    @TableField("ASSUME_NAME")
    private String assumeName;

    @ApiModelProperty(value = "true关联 false未关联")
    @TableField("APPLICATION_MARK")
    private String applicationMark;

    @ApiModelProperty(value = "单位邮政编码")
    @TableField("ZIP_CODE")
    private String zipCode;

    @ApiModelProperty(value = "单位负责人ID")
    @TableField("OU_CHARGE_STAFF_ID")
    private String ouChargeStaffId;

    @ApiModelProperty(value = "单位负责人名称")
    @TableField("OU_CHARGE_STAFF_NAME")
    private String ouChargeStaffName;

    @ApiModelProperty(value = "项目负责人职务")
    @TableField("PROJECT_HEADER_POST")
    private String projectHeaderPost;

    @ApiModelProperty(value = "项目负责人电话")
    @TableField("PROJECT_HEADER_TEL")
    private String projectHeaderTel;

    @ApiModelProperty(value = "项目代管人职务")
    @TableField("PRO_CONNECT_STAFF_POST")
    private String proConnectStaffPost;

    @ApiModelProperty(value = "项目代管人电话")
    @TableField("PRO_CONNECT_STAFF_TEL")
    private String proConnectStaffTel;

    @ApiModelProperty(value = "单位地址")
    @TableField("ADRESS")
    private String adress;

    @ApiModelProperty(value = "主管部门")
    @TableField("DEPARTMENT")
    private String department;

    @ApiModelProperty(value = "主管部门代码")
    @TableField("DEPARTMENT_CODE")
    private String departmentCode;

    @ApiModelProperty(value = "实施单位")
    @TableField("APP_OPARTION_OU")
    private String appOpartionOu;

    @ApiModelProperty(value = "是否为项目新增 1是 2 否")
    @TableField("REPLY_NEW_MARK")
    private String replyNewMark;

    @ApiModelProperty(value = "子活动类别Code")
    @TableField("SUBACT_CATAGORY_CODE")
    private String subactCatagoryCode;

    @ApiModelProperty(value = "子活动类别名称")
    @TableField("SUBACT_CATAGORY_NAME")
    private String subactCatagoryName;

    @ApiModelProperty(value = "项目单位CODE")
    @TableField("ORGANIZATION_CODE")
    private String organizationCode;

    @ApiModelProperty(value = "项目类别CODE")
    @TableField("PROJECT_CATAGORY_CODE")
    private String projectCatagoryCode;

    @ApiModelProperty(value = "项目类别名称")
    @TableField("PROJECT_CATAGORY_NAME")
    private String projectCatagoryName;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "创建人id")
    @TableField("CREATE_USER_ID")
    private String createUserId;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_ATTRIBUTE = "PROJECT_ATTRIBUTE";

    public static final String PROJECT_TYPE_CODE = "PROJECT_TYPE_CODE";

    public static final String PROJECT_TYPE_NAME = "PROJECT_TYPE_NAME";

    public static final String PLAN_YEAR = "PLAN_YEAR";

    public static final String CYCLE = "CYCLE";

    public static final String PROJECT_HEADER_ID = "PROJECT_HEADER_ID";

    public static final String PROJECT_HEADER_NAME = "PROJECT_HEADER_NAME";

    public static final String FIN_HEADER_ID = "FIN_HEADER_ID";

    public static final String PROJECT_CONNECT_STAFF_ID = "PROJECT_CONNECT_STAFF_ID";

    public static final String ORGANIZATION_ID = "ORGANIZATION_ID";

    public static final String ORGANIZATION_NAME = "ORGANIZATION_NAME";

    public static final String PROJECT_CATGORY = "PROJECT_CATGORY";

    public static final String PRO_CANCEL_DES = "PRO_CANCEL_DES";

    public static final String PRO_CHANGE = "PRO_CHANGE";

    public static final String PRIORITY = "PRIORITY";

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

    public static final String PROJECT_STATUS_CODE = "PROJECT_STATUS_CODE";

    public static final String PROJECT_STATUS_NAME = "PROJECT_STATUS_NAME";

    public static final String ENTRUST_ID = "ENTRUST_ID";

    public static final String ASSUME_ID = "ASSUME_ID";

    public static final String PROJECT_MARK = "PROJECT_MARK";

    public static final String FIN_HEADER_NAME = "FIN_HEADER_NAME";

    public static final String PROJECT_CONNECT_STAFF_NAME = "PROJECT_CONNECT_STAFF_NAME";

    public static final String PROJECT_CATGORY_CODE = "PROJECT_CATGORY_CODE";

    public static final String ENTRUST_NAME = "ENTRUST_NAME";

    public static final String ASSUME_NAME = "ASSUME_NAME";

    public static final String APPLICATION_MARK = "APPLICATION_MARK";

    public static final String ZIP_CODE = "ZIP_CODE";

    public static final String OU_CHARGE_STAFF_ID = "OU_CHARGE_STAFF_ID";

    public static final String OU_CHARGE_STAFF_NAME = "OU_CHARGE_STAFF_NAME";

    public static final String PROJECT_HEADER_POST = "PROJECT_HEADER_POST";

    public static final String PROJECT_HEADER_TEL = "PROJECT_HEADER_TEL";

    public static final String PRO_CONNECT_STAFF_POST = "PRO_CONNECT_STAFF_POST";

    public static final String PRO_CONNECT_STAFF_TEL = "PRO_CONNECT_STAFF_TEL";

    public static final String ADRESS = "ADRESS";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String DEPARTMENT_CODE = "DEPARTMENT_CODE";

    public static final String APP_OPARTION_OU = "APP_OPARTION_OU";

    public static final String REPLY_NEW_MARK = "REPLY_NEW_MARK";

    public static final String SUBACT_CATAGORY_CODE = "SUBACT_CATAGORY_CODE";

    public static final String SUBACT_CATAGORY_NAME = "SUBACT_CATAGORY_NAME";

    public static final String ORGANIZATION_CODE = "ORGANIZATION_CODE";

    public static final String PROJECT_CATAGORY_CODE = "PROJECT_CATAGORY_CODE";

    public static final String PROJECT_CATAGORY_NAME = "PROJECT_CATAGORY_NAME";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String CREATE_USER_ID = "CREATE_USER_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

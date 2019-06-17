package com.deloitte.services.contract.entity;

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
 * 合同项目信息 数据来源：科研或者通用项目
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_PROJECT_INFO")
@ApiModel(value="SysProjectInfo对象", description="合同项目信息 数据来源：科研或者通用项目")
public class SysProjectInfo extends Model<SysProjectInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目金额")
    @TableField("PROJECT_AMOUNT")
    private Long projectAmount;

    @ApiModelProperty(value = "所属组织ID")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "所属组织")
    @TableField("ORG")
    private String org;

    @ApiModelProperty(value = "项目负责人ID")
    @TableField("PROJECT_MANAGER_CODE")
    private String projectManagerCode;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PROJECT_MANAGER")
    private String projectManager;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;


    public static final String ID = "ID";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_AMOUNT = "PROJECT_AMOUNT";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORG = "ORG";

    public static final String PROJECT_MANAGER_CODE = "PROJECT_MANAGER_CODE";

    public static final String PROJECT_MANAGER = "PROJECT_MANAGER";

    public static final String IS_USED = "IS_USED";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

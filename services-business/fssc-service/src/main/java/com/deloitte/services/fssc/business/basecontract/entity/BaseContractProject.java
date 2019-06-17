package com.deloitte.services.fssc.business.basecontract.entity;

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
 * 合同项目关联表
 * </p>
 *
 * @author qiliao
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTRACT_PROJECT")
@ApiModel(value="BaseContractProject对象", description="合同项目关联表")
public class BaseContractProject extends Model<BaseContractProject> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty(value = "项目ID")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "合同ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EX1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EX2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EX3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EX4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EX5")
    private String ex5;


    public static final String ID = "ID";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

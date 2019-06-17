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
 * 合同履行计划关联表
 * </p>
 *
 * @author qiliao
 * @since 2019-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTRACT_PLAN_MAP")
@ApiModel(value="BaseContractPlanMap对象", description="合同履行计划关联表")
public class BaseContractPlanMap extends Model<BaseContractPlanMap> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同ID")
    @TableField("CONTRACT_ID")
    private Long contractId;

    @ApiModelProperty(value = "履行计划ID")
    @TableField("PLAN_ID")
    private Long planId;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    public static final String ID = "ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String PLAN_ID = "PLAN_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

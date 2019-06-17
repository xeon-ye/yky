package com.deloitte.platform.api.fssc.edu.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description :   FundsApplyLine查询from对象
 * @Modified :
 */
@ApiModel("FundsApplyLine查询表单")
@Data
public class FundsApplyLineQueryForm extends BaseQueryForm {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "分配学院ID")
    private Long schoolId;

    @ApiModelProperty(value = "申请金额")
    private Double applyAmount;

    @ApiModelProperty(value = "用途类型")
    private String useType;

    @ApiModelProperty(value = "受款负责人ID")
    private Long recieveUserId;

    @ApiModelProperty(value = "受款负责人姓名")
    private String recieveUserName;

    @ApiModelProperty(value = "受款负责人工号")
    private String recieveEmpNo;

    @ApiModelProperty(value = "申请单位名称")
    private String applyUnitName;

    @ApiModelProperty(value = "申请人名称")
    private String applyUserName;

    @ApiModelProperty(value = "是否被调整申请关联")
    private String isConnected;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "预留字段6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    private String ex15;
}

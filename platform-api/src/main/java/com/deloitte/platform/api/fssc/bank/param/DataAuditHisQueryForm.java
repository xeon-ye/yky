package com.deloitte.platform.api.fssc.bank.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description :   DataAuditHis查询from对象
 * @Modified :
 */
@ApiModel("DataAuditHis查询表单")
@Data
public class DataAuditHisQueryForm extends BaseQueryForm<DataAuditHisQueryParam>  {


    @ApiModelProperty(value = "历史记录ID")
    private Long id;

    @ApiModelProperty(value = "对象ID")
    private Long documentId;

    @ApiModelProperty(value = "对象类型即表名")
    private String documentType;

    @ApiModelProperty(value = "审核意见")
    private String auditOpin;
    @ApiModelProperty(value = "拒绝原因")
    private String refusedReson;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

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

    @ApiModelProperty(value = "版本")
    private Long version;
}

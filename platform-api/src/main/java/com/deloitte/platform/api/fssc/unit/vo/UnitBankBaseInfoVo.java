package com.deloitte.platform.api.fssc.unit.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : UnitInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitBankBaseInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    private Long unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位类型")
    private String unitType;

    @ApiModelProperty(value = "所属区域")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long areaId;

    @ApiModelProperty(value = "联系人")
    private String concatUserName;
    @ApiModelProperty(value = "联系方式")
    private String concatWay;

    @ApiModelProperty(value = "创建人")
    private String createUserName;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "状态")
    private String auditStatus;

    @ApiModelProperty(value = "纳税识别号")
    private String taxRegistNum;


    @ApiModelProperty(value = "收款方式")
    private String recieveMoneyType;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "创建人")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long updateBy;

    @ApiModelProperty(value = "版本")
    private Long version;

    private String address;

    @ApiModelProperty(value = "统一信用代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long bankId;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;


    @ApiModelProperty(value = "单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long unitId;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;



}

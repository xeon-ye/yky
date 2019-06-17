package com.deloitte.platform.api.fssc.travle.vo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasTravleApplyInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasTravelApplyInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目预算会计科目")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "支持附件数量")
    private Long supportFileNum;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

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

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "支出大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否离京")
    private String whetherLeaveBj;

    @ApiModelProperty(value = "是否借款")
    private String whetherBorrow;

    @ApiModelProperty(value = "是否有招待")
    private String whetherReception;

    @ApiModelProperty(value = "${field.comment}")
    private Long orgId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgPath;

    @ApiModelProperty(value="出差人数")
    private Long travelPeople;

    @ApiModelProperty("是否被借款单关联")
    private String isBorrowConnect;

    @ApiModelProperty("是否被差旅报账单关联")
    private String isReimburConnect;

    @ApiModelProperty(value="费用信息")
    public List<TasCostInformationLineVo> tasCostInformationLineVos;

    @ApiModelProperty(value="离京信息")
    public List<TasLeaveaBjInformationVo> tasLeaveaBjInformationVos;

    @ApiModelProperty(value="审批历史")
    private List<BpmProcessTaskVo>  bpmProcessTaskVos;
}

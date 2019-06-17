package com.deloitte.platform.api.fssc.poi.vo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
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
 * @Date : Create in 2019-05-13
 * @Description : PepaymentOrderInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PepaymentOrderInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "ID")
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

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

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;


    @ApiModelProperty(value = "${field.comment}")
    private String ext1;

    @ApiModelProperty(value = "${field.comment}")
    private String ext2;

    @ApiModelProperty(value = "${field.comment}")
    private String ext3;

    @ApiModelProperty(value = "${field.comment}")
    private String ext4;

    @ApiModelProperty(value = "${field.comment}")
    private String ext5;

    @ApiModelProperty(value = "${field.comment}")
    private String ext6;

    @ApiModelProperty(value = "${field.comment}")
    private String ext7;

    @ApiModelProperty(value = "${field.comment}")
    private String ext8;

    @ApiModelProperty(value = "${field.comment}")
    private String ext9;

    @ApiModelProperty(value = "${field.comment}")
    private String ext10;

    @ApiModelProperty(value = "${field.comment}")
    private String ext11;

    @ApiModelProperty(value = "${field.comment}")
    private String ext12;

    @ApiModelProperty(value = "${field.comment}")
    private String ext13;

    @ApiModelProperty(value = "${field.comment}")
    private String ext14;

    @ApiModelProperty(value = "${field.comment}")
    private String ext15;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "还款方式")
    private String repaymentType;

    @ApiModelProperty(value = "还款金额合计")
    private BigDecimal reAmountTatol;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "${field.comment}")
    private Long orgId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgPath;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;

    @ApiModelProperty(value = "核销明细")
    private List<GeExpenseBorrowPrepayVo> expenseBorrowPrepayVos;

}

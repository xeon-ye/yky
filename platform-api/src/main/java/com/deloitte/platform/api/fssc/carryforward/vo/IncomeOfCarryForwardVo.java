package com.deloitte.platform.api.fssc.carryforward.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description : IncomeOfCarryForward返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeOfCarryForwardVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "支出大类")
    private String mainTypeId;

    @ApiModelProperty(value = "单据编码")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "归属单位ID")
    private String unitId;

    @ApiModelProperty(value = "归属部门ID")
    private String deptId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "入账日期")
    private LocalDateTime enterDate;

    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    @ApiModelProperty(value = "结转状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "单据编号")
    private String documentId;

    @ApiModelProperty(value = "扩展字段1")
    private String etx1;

    @ApiModelProperty(value = "扩展字段2")
    private String etx2;

    @ApiModelProperty(value = "扩展字段3")
    private String etx3;

    @ApiModelProperty(value = "扩展字段4")
    private String etx4;

    @ApiModelProperty(value = "扩展字段5")
    private String etx5;

    @ApiModelProperty(value = "凭证ID")
    private String jeHeaderId;
    @ApiModelProperty(value = "支出大类name")
    private String mainTypeName;

}

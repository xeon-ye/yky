package com.deloitte.platform.api.fssc.budget.vo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-30
 * @Description : 公费调整 返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetPublicAdjustVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")
    private String  unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门id")
    private String deptId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "年度预算金额")
    private BigDecimal annualBudgetAmount;

    @ApiModelProperty(value = "当期预算金额")
    private BigDecimal periodBudgetAmount;

    @ApiModelProperty(value = "当期可用金额")
    private BigDecimal periodUsableAmount;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "组织ID")
    private String orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "申请人")
    private String applyForPerson;

    @ApiModelProperty(value = "申请人名称")
    private String applyForPersonName;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "行信息")
    List<BudgetPublicAdjustLineVo> lineVoList;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;
}

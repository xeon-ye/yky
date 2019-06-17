package com.deloitte.platform.api.fssc.carryforward.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :   DssScientificRecive查询from对象
 * @Modified :
 */
@ApiModel("DssScientificRecive查询表单")
@Data
public class DssScientificReciveQueryForm extends BaseQueryForm<DssScientificReciveQueryParam>  {


    @ApiModelProperty(value = "唯一标识 唯一标识")
    private Long id;

    @ApiModelProperty(value = "项目编码")
    private String proCode;

    @ApiModelProperty(value = "任务编码")
    private String taskCode;

    @ApiModelProperty(value = "到位时间 到位时间 存储yyyymm")
    private LocalDateTime reciveDate;

    @ApiModelProperty(value = "到位金额 到位金额")
    private Double funds;

    @ApiModelProperty(value = "到位单位ID 到位单位ID")
    private Long reciveDeptId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "来源系统")
    private String fromSystem;

    @ApiModelProperty(value = "预算年度")
    private String budgetYear;

    @ApiModelProperty(value = "财务单据编码")
    private String documentNum  ;

    @ApiModelProperty(value = "Y  在用  N 已冲销")
    private String status;

    @ApiModelProperty(value = "财务系统总唯一标识数据的ID")
    private Long cwLineId;
}

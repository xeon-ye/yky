package com.deloitte.platform.api.dss.finance.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-10
 * @Description : FinanceExpenditure返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceExpenditureVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "期间")
    @TableField("PERIOD_CODE")
    private String periodCode;

    @ApiModelProperty(value = "机构编码")
    @TableField("COM_CODE")
    private String comCode;

    @ApiModelProperty(value = "机构名称")
    @TableField("COM_DES")
    private String comDes;

    @ApiModelProperty(value = "指标编码")
    @TableField("INDEX_CODE")
    private String indexCode;

    @ApiModelProperty(value = "指标名称")
    @TableField("INDEX_DES")
    private String indexDes;

    @ApiModelProperty(value = "年初至今金额")
    @TableField("YTD")
    private BigDecimal ytd;

    @ApiModelProperty(value = "年度预算")
    @TableField("BUD_YTD")
    private BigDecimal budYtd;

}

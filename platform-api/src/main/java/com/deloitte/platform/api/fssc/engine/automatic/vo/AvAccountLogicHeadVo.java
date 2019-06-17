package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvAccountLogicHead返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvAccountLogicHeadVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "逻辑凭证ID")
    private String logicId;

    @ApiModelProperty(value = "判断条件")
    private String judgeCondition;

    @ApiModelProperty(value = "业务类别来源")
    private String typeFrom;

    @ApiModelProperty(value = "附件数量来源")
    private String numFrom;

    @ApiModelProperty(value = "币种来源")
    private String voucherFrom;

    @ApiModelProperty(value = "汇率来源")
    private String rateFrom;

    @ApiModelProperty(value = "头说明来源")
    private String headFrom;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long crreteBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @ApiModelProperty(value = "预留属性1")
    private String etx1;

    @ApiModelProperty(value = "预留属性2")
    private String etx2;

    @ApiModelProperty(value = "预留属性3")
    private String etx3;

    @ApiModelProperty(value = "预留属性4")
    private String etx4;

    @ApiModelProperty(value = "预留属性5")
    private String etx5;

}

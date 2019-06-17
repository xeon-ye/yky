package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvBaseElement返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvBaseElementVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String  id;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String dataType;

    @ApiModelProperty(value = "数据编码")
    private String dataCode;

    @ApiModelProperty(value = "数据名称")
    private String dataDesc;

    @ApiModelProperty(value = "数据状态（N/Y）")
    private String dataStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "预留字段1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    private String etx3;

    @ApiModelProperty(value = "预留字段4")
    private String etx4;

    @ApiModelProperty(value = "预留字段5")
    private String etx5;

    @ApiModelProperty(value = "COA的序号")
    private Long segmentNum;

    @ApiModelProperty(value = "是否是父类")
    private String summaryFlag;


    List<AvBaseElementVo> segment1;
    List<AvBaseElementVo> segment2;
    List<AvBaseElementVo> segment3;
    List<AvBaseElementVo> segment4;
    List<AvBaseElementVo> segment5;
    List<AvBaseElementVo> segment6;
    List<AvBaseElementVo> segment7;
    List<AvBaseElementVo> segment8;
    List<AvBaseElementVo> segment9;
    List<AvBaseElementVo> segment10;




}

package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :   AvAccountElement查询from对象
 * @Modified :
 */
@ApiModel("AvAccountElement查询表单")
@Data
public class AvAccountElementQueryForm extends BaseQueryForm<AvAccountElementQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "科目表ID（跟COA关联）")
    private Long chartOfAccountsId;

    @ApiModelProperty(value = "核算要素编码")
    private String segmentCode;

    @ApiModelProperty(value = "核算要素名称")
    private String segmentDesc;

    @ApiModelProperty(value = "COA的序号")
    private Long segmentNum;

    @ApiModelProperty(value = "账套名称")
    private String name;

    @ApiModelProperty(value = "简称")
    private String shortName;

    private String description;

    @ApiModelProperty(value = "核算要素值(N/Y)")
    private String hasValue;

    @ApiModelProperty(value = "是否有效(N/Y)")
    private String status;

    @ApiModelProperty(value = "要素类型(COA/HEAD/LINE)")
    private String type;

    @ApiModelProperty(value = "数据来源")
    private String dataFrom;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "更新人")
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
    @ApiModelProperty(value = "值级")
    private String segmentType;

    private int start;
    private int end;
    private String balanceCode;
    private Long ledgerId;
}

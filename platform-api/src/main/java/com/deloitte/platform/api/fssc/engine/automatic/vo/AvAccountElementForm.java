package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvAccountElement新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvAccountElement表单")
@Data
public class AvAccountElementForm extends BaseForm {
    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

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

    private  Long ledgerId;
    @ApiModelProperty(value = "值级")
    private String segmentType;

}

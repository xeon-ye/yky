package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvAccountLogicLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvAccountLogicLine表单")
@Data
public class AvAccountLogicLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    private Long id;
    @ApiModelProperty(value = "判断条件")
    private String judgeCondition;

    @ApiModelProperty(value = "凭证逻辑ID")
    private Long logicId;

    @ApiModelProperty(value = "借贷")
    private String debitCreditFrom;

    @ApiModelProperty(value = "分录类型")
    private String entryTypeFrom;

    @ApiModelProperty(value = "行说明")
    private String rowCommentFrom;

    @ApiModelProperty(value = "金额")
    private String moneyFrom;

    @ApiModelProperty(value = "COA段1")
    private String segment1;

    @ApiModelProperty(value = "COA段2")
    private String segment2;

    @ApiModelProperty(value = "COA段3")
    private String segment3;

    @ApiModelProperty(value = "COA段4")
    private String segment4;

    @ApiModelProperty(value = "COA段5")
    private String segment5;

    @ApiModelProperty(value = "COA段6")
    private String segment6;

    @ApiModelProperty(value = "COA段7")
    private String segment7;

    @ApiModelProperty(value = "COA段8")
    private String segment8;

    @ApiModelProperty(value = "COA段9")
    private String segment9;

    @ApiModelProperty(value = "COA段10")
    private String segment10;

    @ApiModelProperty(value = "COA段11")
    private String segment11;

    @ApiModelProperty(value = "COA段12")
    private String segment12;

    @ApiModelProperty(value = "COA段13")
    private String segment13;

    @ApiModelProperty(value = "COA段14")
    private String segment14;

    @ApiModelProperty(value = "COA段15")
    private String segment15;

    @ApiModelProperty(value = "COA段16")
    private String segment16;

    @ApiModelProperty(value = "COA段17")
    private String segment17;

    @ApiModelProperty(value = "COA段18")
    private String segment18;

    @ApiModelProperty(value = "COA段19")
    private String segment19;

    @ApiModelProperty(value = "COA段20")
    private String segment20;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "预留属性1")
    private String ext1;

    @ApiModelProperty(value = "预留属性2")
    private String ext2;

    @ApiModelProperty(value = "预留属性3")
    private String ext3;

    @ApiModelProperty(value = "预留属性4")
    private String ext4;

    @ApiModelProperty(value = "预留属性5")
    private String ext5;
    @ApiModelProperty(value = "和主表映射关系Id")
    private String avDicId;


}

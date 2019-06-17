package com.deloitte.platform.api.fssc.engine.manual.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description : AvManualVoucherLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvManualVoucherLine表单")
@Data
public class AvManualVoucherLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private  Long id;

    @ApiModelProperty(value = "凭证行号")
    private Long jeLineNum;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "会计科目结构")
    private String accountStructure;

    @ApiModelProperty(value = "会计科目结构编码")
    private String accountStructureCode;

    @ApiModelProperty(value = "会计科目结构描述")
    private String accountStructureDesc;

    @ApiModelProperty(value = "凭证类型")
    private String voucherType;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "凭证头ID")
    private Long jeHeaderId;

    @ApiModelProperty(value = "账薄ID")
    private Long ledgerId;

    @ApiModelProperty(value = "会计期间")
    private String periodName;

    @ApiModelProperty(value = "会计日期")
    private LocalDateTime effectiveDate;

    @ApiModelProperty(value = "原币借方金额")
    private BigDecimal enteredDr;

    @ApiModelProperty(value = "原币贷方金额")
    private BigDecimal enteredCr;

    @ApiModelProperty(value = "本币借方金额")
    private BigDecimal accountedDr;

    @ApiModelProperty(value = "本币贷方金额")
    private BigDecimal accountedCr;

    @ApiModelProperty(value = "凭证行说明")
    private String description;

    @ApiModelProperty(value = "凭证类别")
    private String lineTypeCode;

    @ApiModelProperty(value = "审批状态")
    private String status;

    @ApiModelProperty(value = "过账状态")
    private String postStatus;

    @ApiModelProperty(value = "科目结构1")
    private String segment1;

    @ApiModelProperty(value = "科目结构2")
    private String segment2;

    @ApiModelProperty(value = "科目结构3")
    private String segment3;

    @ApiModelProperty(value = "科目结构4")
    private String segment4;

    @ApiModelProperty(value = "科目结构5")
    private String segment5;

    @ApiModelProperty(value = "科目结构6")
    private String segment6;

    @ApiModelProperty(value = "科目结构7")
    private String segment7;

    @ApiModelProperty(value = "科目结构8")
    private String segment8;

    @ApiModelProperty(value = "科目结构9")
    private String segment9;

    @ApiModelProperty(value = "科目结构10")
    private String segment10;

    @ApiModelProperty(value = "科目结构11")
    private String segment11;

    @ApiModelProperty(value = "科目结构12")
    private String segment12;

    @ApiModelProperty(value = "科目结构13")
    private String segment13;

    @ApiModelProperty(value = "科目结构14")
    private String segment14;

    @ApiModelProperty(value = "科目结构15")
    private String segment15;

    @ApiModelProperty(value = "科目结构16")
    private String segment16;

    @ApiModelProperty(value = "科目结构17")
    private String segment17;

    @ApiModelProperty(value = "科目结构18")
    private String segment18;

    @ApiModelProperty(value = "科目结构19")
    private String segment19;

    @ApiModelProperty(value = "科目结构20")
    private String segment20;

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

    @ApiModelProperty(value = "扩展字段6")
    private String ext6;

    @ApiModelProperty(value = "扩展字段7")
    private String ext7;

    @ApiModelProperty(value = "扩展字段8")
    private String ext8;

    @ApiModelProperty(value = "扩展字段9")
    private String ext9;

    @ApiModelProperty(value = "扩展字段10")
    private String ext10;

    @ApiModelProperty(value = "扩展字段11")
    private String ext11;

    @ApiModelProperty(value = "扩展字段12")
    private String ext12;

    @ApiModelProperty(value = "扩展字段13")
    private String ext13;

    @ApiModelProperty(value = "扩展字段14")
    private String ext14;

    @ApiModelProperty(value = "扩展字段15")
    private String ext15;

    @ApiModelProperty(value = "EBS返回错误信息")
    private String errorMessage;

}

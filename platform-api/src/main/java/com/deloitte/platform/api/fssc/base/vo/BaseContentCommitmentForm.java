package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description : BaseContentCommitment新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseContentCommitment表单")
@Data
public class BaseContentCommitmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单据名称")
    private String billName;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "承若书内容")
    private String contentCommitment;

    @ApiModelProperty(value = "有效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "无效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "单据类型ID",required = true)
    @NotEmpty(message = "单据类型ID不能为空")
    private Long documentTypeId;

    @ApiModelProperty(value = "组织ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "单位编码",required = true)
    @NotBlank(message = "单位编码不能为空")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "ext1")
    private String ext1;

    @ApiModelProperty(value = "ext2")
    private String ext2;

    @ApiModelProperty(value = "ext3")
    private String ext3;

    @ApiModelProperty(value = "ext4")
    private String ext4;

    @ApiModelProperty(value = "ext5")
    private String ext5;

}

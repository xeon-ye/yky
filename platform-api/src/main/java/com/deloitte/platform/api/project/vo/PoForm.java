package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-25
 * @Description : Po新增修改form对象
 * @Modified :
 */
@ApiModel("新增Po表单")
@Data
public class PoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "采购清单ID")
    private String poId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "物料名称")
    private String mtlName;

    @ApiModelProperty(value = "数量")
    private String mtlQuantity;

    @ApiModelProperty(value = "物料单价")
    private String mtlUnitPrice;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

}

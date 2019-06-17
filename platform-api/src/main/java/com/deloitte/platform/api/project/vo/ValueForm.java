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
 * @Date : Create in 2019-05-23
 * @Description : Value新增修改form对象
 * @Modified :
 */
@ApiModel("新增Value表单")
@Data
public class ValueForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "集值ID")
    private Long valueId;

    @ApiModelProperty(value = "集值编码")
    private String valueCode;

    @ApiModelProperty(value = "集值名称")
    private String valueName;

    @ApiModelProperty(value = "集值状态,0生效,1失效")
    private Long valueStatus;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime successTime;

    @ApiModelProperty(value = "父ID")
    private Long parId;

    @ApiModelProperty(value = "父名称")
    private String parName;

    @ApiModelProperty(value = "失效时间")
    private LocalDateTime failureTime;

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
    private String orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "项目分类id")
    private Long proId;

    @ApiModelProperty(value = "值集描述")
    private String valueDes;

}

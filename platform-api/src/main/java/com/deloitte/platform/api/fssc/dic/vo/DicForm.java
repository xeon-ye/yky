package com.deloitte.platform.api.fssc.dic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : Dic新增修改form对象
 * @Modified :
 */
@ApiModel("新增Dic表单")
@Data
public class DicForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字段id")
    private Long id;

    @ApiModelProperty(value = "字段代码")
    private String eumCode;

    @ApiModelProperty(value = "字段名称")
    private String eumName;

    @ApiModelProperty(value = "字段描述")
    private String eumDesciption;

    @ApiModelProperty(value = "排序编号")
    private Double eumOrder;

    @ApiModelProperty(value = "关联表名(应用到哪个表)")
    private String eumConcatTab;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

}

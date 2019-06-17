package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Dict新增修改form对象
 * @Modified :
 */
@ApiModel("新增Dict表单")
@Data
public class DictForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "备注说明")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "上级CODE")
    private String parentCode;

}

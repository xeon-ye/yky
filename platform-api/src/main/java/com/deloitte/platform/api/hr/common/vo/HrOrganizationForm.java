package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-18
 * @Description : Organization新增修改form对象
 * @Modified :
 */
@ApiModel("新增Organization表单")
@Data
public class HrOrganizationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "上级编码")
    private String parentCode;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预留字段1")
    private String reserve;

    @ApiModelProperty(value = "预留字段2")
    private String version;

    @ApiModelProperty(value = "统一信用代码")
    private String commonCreditCode;

}

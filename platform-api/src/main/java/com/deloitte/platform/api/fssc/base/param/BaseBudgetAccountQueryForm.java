package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description :   BaseBudgetAccount查询from对象
 * @Modified :
 */
@ApiModel("BaseBudgetAccount查询表单")
@Data
public class BaseBudgetAccountQueryForm extends BaseQueryForm<BaseBudgetAccountQueryParam>  {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "启用标志")
    private String validFlag;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "预算类型")
    private String budgetType;

    private String sort;

    private String sortOrder;
}

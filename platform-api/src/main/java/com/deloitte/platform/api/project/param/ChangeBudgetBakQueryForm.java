package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-05
 * @Description :   ChangeBudgetBak查询from对象
 * @Modified :
 */
@ApiModel("ChangeBudgetBak查询表单")
@Data
public class ChangeBudgetBakQueryForm extends BaseQueryForm<ChangeBudgetBakQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "变更预算id")
    private String maintBudgetId;

    @ApiModelProperty(value = "项目维护id")
    private String maintId;

    @ApiModelProperty(value = "批复id")
    private String actCode;

    @ApiModelProperty(value = "经济类型code")
    private String actName;

    @ApiModelProperty(value = "经济类型name")
    private String actAmount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;
}

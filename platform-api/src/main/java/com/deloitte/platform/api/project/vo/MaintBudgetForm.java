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
 * @Description : MaintBudget新增修改form对象
 * @Modified :
 */
@ApiModel("新增MaintBudget表单")
@Data
public class MaintBudgetForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "预算id")
    private String maintBudgetId;

    @ApiModelProperty(value = "项目维护id")
    private String maintId;

    @ApiModelProperty(value = "支出code")
    private String actCode;

    @ApiModelProperty(value = "支出name")
    private String actName;

    @ApiModelProperty(value = "支出金额")
    private String actAmount;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

}

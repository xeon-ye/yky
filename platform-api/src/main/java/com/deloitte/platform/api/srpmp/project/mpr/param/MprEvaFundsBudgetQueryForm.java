package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-12
 * @Description :   MprEvaFundsBudget查询from对象
 * @Modified :
 */
@ApiModel("MprEvaFundsBudget查询表单")
@Data
public class MprEvaFundsBudgetQueryForm extends BaseQueryForm<MprEvaFundsBudgetQueryParam>  {


    @ApiModelProperty(value = "项目ID")
    private Long id;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "任务")
    private String taskName;

    @ApiModelProperty(value = "预算")
    private String budget;

    @ApiModelProperty(value = "支出")
    private String expenses;

    @ApiModelProperty(value = "执行率")
    private String exacutiveRate;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}

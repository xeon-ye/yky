package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccTalentProjectCategory查询from对象
 * @Modified :
 */
@ApiModel("GccTalentProjectCategory查询表单")
@Data
public class GccTalentProjectCategoryQueryForm extends BaseQueryForm<GccTalentProjectCategoryQueryParam>  {
    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "项目类别名称")
    private String projectCategoryName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;


    @ApiModelProperty(value = "所属项目")
    private Long projectCode;

   /* @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "编号")
    private Long code;





    @ApiModelProperty(value = "备注")
    private String remarks;



    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;*/
}

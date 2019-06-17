package com.deloitte.platform.api.portal.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-03
 * @Description :   Category查询from对象
 * @Modified :
 */
@ApiModel("Category查询表单")
@Data
public class CategoryQueryForm extends BaseQueryForm<CategoryQueryParam>  {


    @ApiModelProperty(value = "栏目ID")
    private Long categoryId;

    @ApiModelProperty(value = "栏目标题")
    private String categoryTitle;

    @ApiModelProperty(value = "栏目关键字")
    private String categoryKey;

    @ApiModelProperty(value = "栏目排序")
    private Long categorySort;

    @ApiModelProperty(value = "栏目发布时间")
    private LocalDateTime categoryDatetime;

    @ApiModelProperty(value = "栏目描述")
    private String categoryDescription;

    @ApiModelProperty(value = "删除状态")
    private String delFlg;
}

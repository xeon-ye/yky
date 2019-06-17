package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaPromotionTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaPromotionTable表单")
@Data
public class MprEvaPromotionTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "示范培训/推广地点")
    private String promotionArea;

    @ApiModelProperty(value = "示范培训/推广内容")
    private String promotionContent;

    @ApiModelProperty(value = "示范培训/推广人次")
    private String promotionTimes;

    @ApiModelProperty(value = "扩展")
    private String promotionExt;

}

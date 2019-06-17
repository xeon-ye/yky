package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :   CheckAchEvaluateUser查询from对象
 * @Modified :
 */
@ApiModel("CheckResultRelationQueryForm查询表单")
@Data
public class CheckResultRelationQueryForm extends BaseQueryForm<CheckAchEvaluateUserQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核关系id(冗余便于查找被评价人)")
    private String checkRelationId;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "考核关系名称")
    private String relationName;

    @ApiModelProperty(value = "考核表类型id")
    private String checkTableTypeId;

    @ApiModelProperty(value = "考核模板id")
    private String checkTemplateId;

}

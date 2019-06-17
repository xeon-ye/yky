package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckRelation查询from对象
 * @Modified :
 */
@ApiModel("CheckRelation查询表单")
@Data
public class CheckRelationComputeQueryForm extends BaseQueryForm<CheckRelationQueryParam>  {

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "测评通知di")
    private String checkEvaluateNotifyId;

    @ApiModelProperty(value = "考核表类型id")
    private String checkTableTypeId;

    @ApiModelProperty(value = "考核关系名称")
    private String relationName;

}

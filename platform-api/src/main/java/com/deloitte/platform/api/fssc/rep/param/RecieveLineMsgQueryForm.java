package com.deloitte.platform.api.fssc.rep.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :   RecieveLineMsg查询from对象
 * @Modified :
 */
@ApiModel("RecieveLineMsg查询表单")
@Data
public class RecieveLineMsgQueryForm extends BaseQueryForm<RecieveLineMsgQueryParam>  {

     @ApiModelProperty(value = "单据类型")
     private String documentType;

     @ApiModelProperty(value = "单据编号")
     private String documentNum;

     @ApiModelProperty(value = "付款单位id")
     private Long payUnitId;

     @ApiModelProperty(value = "收入小类ID")
     private Long inComeSubTypeId;

     @ApiModelProperty(value = "收入大类ID")
     private Long inComeMainTypeId;
}

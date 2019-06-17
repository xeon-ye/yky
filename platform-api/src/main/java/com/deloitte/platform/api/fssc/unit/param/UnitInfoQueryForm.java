package com.deloitte.platform.api.fssc.unit.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :   UnitInfo查询from对象
 * @Modified :
 */
@ApiModel("UnitInfo查询表单")
@Data
public class UnitInfoQueryForm extends BaseQueryForm<UnitInfoQueryParam>  {


    @ApiModelProperty(value = "单位编码")
    private Long unitCode;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位类型")
    private String unitType;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "状态")
    private String auditStatus;

    @ApiModelProperty(value = "创建人")
    private String createUserName;



//    @ApiModelProperty(value = "开户行")
//    private String openBank;
//
//    @ApiModelProperty(value = "开户分行")
//    private String openBranchBank;
//
//    @ApiModelProperty(value = "银行账号")
//    private String bankAccount;

}

package com.deloitte.platform.api.fssc.basecontract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :   BaseContractInfo查询from对象
 * @Modified :
 */
@ApiModel("BaseContractInfo查询表单")
@Data
public class BaseContractInfoQueryForm extends BaseQueryForm<BaseContractInfoQueryParam>  {


    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "对方签约主体名称")
    private String sideSubjectName;

    @ApiModelProperty(value = "我方签约主体名称")
    private String ourSubjectName;

    @ApiModelProperty(value = "履行期限开始时间")
    private LocalDateTime executeStartTime;

    @ApiModelProperty(value = "履行期限结束时间")
    private LocalDateTime executeEndTime;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同状态/审批状态")
    private String statue;

    @ApiModelProperty(value = "主办部门")
    private String org;

    @ApiModelProperty(value = "主办部门")
    private String orgCode;

    @ApiModelProperty(value = "经办人")
    private String operator;

    @ApiModelProperty(value = "是否缴纳印花税")
    private String isPayStampDuty;

    @ApiModelProperty(value = "是否涉外合同")
    private String isForeignContract;




}

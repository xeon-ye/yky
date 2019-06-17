package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireRehiringApply查询from对象
 * @Modified :
 */
@ApiModel("RetireRehiringApply查询表单")
@Data
public class RehiringApplyQueryForm extends BaseQueryForm<RehiringApplyQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "返聘原因")
    private String rehiringReason;

    @ApiModelProperty(value = "备用字段(审批转态)")
    private String approval;

    @ApiModelProperty(value = "0保存,1.提交")
    private String status;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "1.部门年度申请,2.部门月度申请,3人力资源处申请")
    private String applyType;
}

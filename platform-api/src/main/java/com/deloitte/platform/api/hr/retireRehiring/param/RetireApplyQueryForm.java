package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireApply查询from对象
 * @Modified :
 */
@ApiModel("RetireApply查询表单")
@Data
public class RetireApplyQueryForm extends BaseQueryForm<RetireApplyQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "备注/退休原由")
    private String remark;

    @ApiModelProperty(value = "0保存,1提交")
    private String status;

    @ApiModelProperty(value = "审批转态(保留字段)")
    private String approval;

    @ApiModelProperty(value = "1.人力资源处,2.机关职工-处级,副高级女干部,3.所院高级干部")
    private String retireType;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Double updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;
}

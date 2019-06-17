package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireDelayApply查询from对象
 * @Modified :
 */
@ApiModel("RetireDelayApply查询表单")
@Data
public class DelayApplyQueryForm extends BaseQueryForm<DelayApplyQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "职工编号")
    private String empCode;

    @ApiModelProperty(value = "0.所院,1机关")
    private String institute;

    @ApiModelProperty(value = "近几年工作情况")
    private String recentWorking;

    @ApiModelProperty(value = "拟定担任工作")
    private String intendedWork;

    @ApiModelProperty(value = "个人意见(机关)")
    private String personalOpinion;

    @ApiModelProperty(value = "延缓退休时间")
    private LocalDateTime delayTime;

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
}

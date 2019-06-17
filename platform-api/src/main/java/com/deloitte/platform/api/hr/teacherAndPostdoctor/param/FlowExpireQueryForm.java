package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-05-14
 * @Description :   FlowExpire查询from对象
 * @Modified :
 */
@ApiModel("FlowExpire查询表单")
@Data
public class FlowExpireQueryForm extends BaseQueryForm<FlowExpireQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "流动站ID")
    private Long stationId;

    @ApiModelProperty(value = "到期提醒人用户CODE")
    private String userCode;

    @ApiModelProperty(value = "是否有效（1是，0否）")
    private Integer isValid;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime effectiveDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}

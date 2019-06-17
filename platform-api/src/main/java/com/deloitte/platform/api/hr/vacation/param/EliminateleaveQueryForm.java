package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description :   Eliminateleave查询from对象
 * @Modified :
 */
@ApiModel("Eliminateleave查询表单")
@Data
public class EliminateleaveQueryForm extends BaseQueryForm<EliminateleaveQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "请假申请表ID")
    private String vacationTrainId;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "销假说明")
    private String eliminateleaveIllu;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;


}

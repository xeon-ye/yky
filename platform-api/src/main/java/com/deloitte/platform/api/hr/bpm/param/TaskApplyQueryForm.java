package com.deloitte.platform.api.hr.bpm.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/4/19 15:57
 */
@ApiModel("我的申请查询参数")
@Data
public class TaskApplyQueryForm extends BaseQueryForm<TaskApplyQueryParam> {

    @ApiModelProperty(value = "查询申请人accoutId")
    private String applyId;

    @ApiModelProperty(value = "系统归属, 固定值 HR")
    private String sourceSystem = "HR";

    @ApiModelProperty(value = "当前状态, 取固定值已提交")
    private String taskStauts = "已提交";

}

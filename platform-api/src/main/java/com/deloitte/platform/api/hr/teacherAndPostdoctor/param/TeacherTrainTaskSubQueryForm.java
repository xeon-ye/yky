package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :   TeacherTrainTask查询from对象
 * @Modified :
 */
@ApiModel("作业提交管理查询表单")
@Data
public class TeacherTrainTaskSubQueryForm extends BaseQueryForm<TeacherTrainTaskQueryParam>  {

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "类型（1 作业提交列表，2 审核作业提交列表）")
    @NotNull(message = "页面类型不能为空")
    @Range(min = 1,max = 2)
    private Integer type;
}

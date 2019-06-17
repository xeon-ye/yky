package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainTask新增修改form对象
 * @Modified :
 */
@ApiModel("作业评分表单")
@Data
public class TeacherTrainTaskMarkForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "人员信息不能为空")
    @ApiModelProperty(value = "人员信息id")
    private Long teacherTrainId;

    @ApiModelProperty(value = "作业一评分")
    private BigDecimal taskOneMark;

    @ApiModelProperty(value = "作业二评分")
    private BigDecimal taskTwoMark;

    @ApiModelProperty(value = "提交类型（1 保存，2提交）")
    @NotNull(message = "提交类型不能为空")
    @Range(min = 1,max = 2)
    private Integer type;

    @ApiModelProperty(value = "流程id")
    private Long id;

    @ApiModelProperty(value = "是否代办进入（1是，2否）")
    private Integer backLog;

}

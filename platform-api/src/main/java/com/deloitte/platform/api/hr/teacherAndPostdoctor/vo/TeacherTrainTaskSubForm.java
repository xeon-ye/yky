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
@ApiModel("提交作业表单")
@Data
public class TeacherTrainTaskSubForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员信息id")
    private Long teacherTrainId;

    @ApiModelProperty(value = "作业一")
    private String taskOne;

    @ApiModelProperty(value = "作业二")
    private String taskTwo;

    @ApiModelProperty(value = "作业一附件url")
    private String taskOneAttachmentUrl;

    @ApiModelProperty(value = "作业二附件url")
    private String taskTwoAttachmentUrl;

    @ApiModelProperty(value = "提交类型（1 保存，2提交）")
    @NotNull(message = "提交类型不能为空")
    @Range(min = 1,max = 2)
    private Integer type;
}

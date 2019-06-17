package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainGain新增修改form对象
 * @Modified :
 */
@ApiModel("新增TeacherTrainGain表单")
@Data
public class TeacherTrainGainForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教师基本信息表id")
    private Long teacherTrainId;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "获得日期")
    private LocalDate getDate;

    @ApiModelProperty(value = "办证机构")
    private String certOrg;

    @ApiModelProperty(value = "备注")
    private String remark;

}

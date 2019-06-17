package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description : TeacherTrainAtt新增修改form对象
 * @Modified :
 */
@ApiModel("录入单个培训人员考勤信息表单")
@Data
public class TeacherTrainAttForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗前人员基本信息表id")
    private Long tid;

    @NotNull(message = "考勤是否合格不能为空")
    @ApiModelProperty(value = "考勤是否合格（1合格，0不合格）")
    private Integer isQualified;

    @NotNull(message = "考勤得分不能为空")
    @ApiModelProperty(value = "考勤得分")
    private String attendanceScore;

    @ApiModelProperty(value = "考勤信息")
    private List<TeacherTrainAttInfoForm> formList;
}

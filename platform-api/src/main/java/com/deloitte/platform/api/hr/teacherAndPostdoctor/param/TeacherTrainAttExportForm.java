package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description :   TeacherTrainAtt查询from对象
 * @Modified :
 */
@ApiModel("考勤管理TeacherTrainAtt导出查询表单")
@Data
public class TeacherTrainAttExportForm {

    @ApiModelProperty(value = "申请年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String attachUnit;

    @ApiModelProperty(value = "考勤是否合格（1合格，0不合格）")
    private Integer isQualified;

}

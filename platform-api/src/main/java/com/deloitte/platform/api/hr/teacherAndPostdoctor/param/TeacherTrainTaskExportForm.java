package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :   TeacherTrainTask查询from对象
 * @Modified :
 */
@ApiModel("作业管理导出表单")
@Data
public class TeacherTrainTaskExportForm {

    @ApiModelProperty(value = "申请年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String attachUnit;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过）")
    private Integer status;

}

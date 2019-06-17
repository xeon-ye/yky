package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description :   TeacherTrain查询from对象
 * @Modified :
 */
@ApiModel("岗前培训列表条件查询表单")
@Data
public class TeacherTrainQueryForm extends BaseQueryForm<TeacherTrainQueryParam>  {

    @ApiModelProperty(value = "申请年份")
    private String year;

    @ApiModelProperty(value = "期数")
    private String period;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位(可多选，数组[111,2222]传参)")
    private String attachUnit;

    @ApiModelProperty(value = "状态（1所院审核通过，2院校审核中，3院校审核通过，4院校审核不通过）")
    private Integer status;

}

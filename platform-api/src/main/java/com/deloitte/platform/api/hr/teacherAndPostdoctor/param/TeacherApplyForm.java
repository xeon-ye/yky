package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherAbilityApplyQueryParam;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.TeacherQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : TeacherApply查询form对象
 * @Modified :
 */
@ApiModel("TeacherApply表单")
@Data
public class TeacherApplyForm extends BaseQueryForm<TeacherAbilityApplyQueryParam> {

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请任教学科")
    private String applyTeachSubject;

    @ApiModelProperty(value = "是否上传证书（1是，2否）")
    private Integer isUpload;

}

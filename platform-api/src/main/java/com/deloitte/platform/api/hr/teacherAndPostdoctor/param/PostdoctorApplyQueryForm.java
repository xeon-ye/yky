package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorApply查询from对象
 * @Modified :
 */
@ApiModel("PostdoctorApply查询表单")
@Data
public class PostdoctorApplyQueryForm extends BaseQueryForm<PostdoctorApplyQueryParam>  {

    @NotNull(message = "自助类型不能为空")
    @Range(min = 1,max = 2)
    @ApiModelProperty(value = "自助类型（1在站成果，2申请退站/延期/更换导师）")
    private Integer type;

}

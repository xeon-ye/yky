package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorFundsRecord查询from对象
 * @Modified :
 */
@ApiModel("博士后经费使用记录查询表单")
@Data
public class PostdoctorFundsRecordQueryForm extends BaseQueryForm<PostdoctorFundsRecordQueryParam>  {

    @ApiModelProperty(value = "经费信息ID")
    private Long postdoctorFundsId;

}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : 搜索Postdoctor表单对象
 * @Modified :
 */
@ApiModel("搜索Postdoctor表单")
@Data
public class PostdoctorSearchForm extends BaseQueryForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String pullUnit;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = "发送内容")
    private String msg;
}

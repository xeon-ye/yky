package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description : ProcessNumber新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProcessNumber表单")
@Data
public class ProcessNumberForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "流程单号代表项目")
    private String name;

    @ApiModelProperty(value = "流程单号前缀")
    private String processNumberCode;

    @ApiModelProperty(value = "流程单号中间日期")
    private String processNumberDate;

    @ApiModelProperty(value = "${field.comment}")
    private String processNumberLast;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

}

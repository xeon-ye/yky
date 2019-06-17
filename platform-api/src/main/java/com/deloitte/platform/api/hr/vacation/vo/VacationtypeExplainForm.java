package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-17
 * @Description : VacationtypeExplain新增修改form对象
 * @Modified :
 */
@ApiModel("新增VacationtypeExplain表单")
@Data
public class VacationtypeExplainForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "假期类型")
    private String vacationType;

    @ApiModelProperty(value = "类型说明提示")
    private String vacationExplain;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

}

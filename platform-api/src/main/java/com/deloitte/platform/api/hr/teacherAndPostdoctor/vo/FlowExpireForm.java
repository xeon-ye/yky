package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-05-14
 * @Description : FlowExpire新增修改form对象
 * @Modified :
 */
@ApiModel("更新FlowExpire表单")
@Data
public class FlowExpireForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "到期提醒信息ID不能为空")
    private Long id;

    @ApiModelProperty(value = "流动站code")
    private Long stationCode;

    @ApiModelProperty(value = "到期提醒人用户CODE")
    @NotNull(message = "到期提醒人用户不能为空")
    private String userCode;

    @ApiModelProperty(value = "是否有效（1是，0否）")
    private Integer isValid;

    @ApiModelProperty(value = "生效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;



}

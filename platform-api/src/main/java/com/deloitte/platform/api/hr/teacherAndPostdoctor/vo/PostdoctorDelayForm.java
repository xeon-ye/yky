package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : 修改Postdoctor延期表单对象
 * @Modified :
 */
@ApiModel("修改Postdoctor延期表单")
@Data
public class PostdoctorDelayForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "延期时长（月）")
    private Integer delayDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "延期时间至（格式:2019-04-01）")
    private LocalDate delayTime;

    @ApiModelProperty(value = "延期档案存处")
    private String delayArchivesDeposit;

    @ApiModelProperty(value = "延期备注")
    private String delayRemark;

    @ApiModelProperty(value = "延期附件名称")
    private String attachmentDelayUrl;

    @ApiModelProperty(value = "延期附件名称")
    private String delayFileName;
}

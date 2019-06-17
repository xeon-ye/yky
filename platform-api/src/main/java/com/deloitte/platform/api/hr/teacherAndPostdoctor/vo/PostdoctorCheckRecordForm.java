package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorCheckRecord新增form对象
 * @Modified :
 */
@ApiModel("新增考核记录表单")
@Data
public class PostdoctorCheckRecordForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博士后信息ID")
    private Long postdoctorId;

    @ApiModelProperty(value = "考核类型（1入站考核，2中期考核，3出战考核）")
    private Integer checkType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "拟出站时间（格式:2019-04-01）")
    private LocalDate expectPushTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "考核时间（格式:2019-04-01）")
    private LocalDate checkTime;

    @ApiModelProperty(value = "考核结果")
    private String checkResult;

    @ApiModelProperty(value = "备注")
    private String remark;

}

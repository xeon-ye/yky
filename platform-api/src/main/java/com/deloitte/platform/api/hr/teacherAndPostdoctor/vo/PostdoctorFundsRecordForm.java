package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorFundsRecord新增修改form对象
 * @Modified :
 */
@ApiModel("新增经费使用记录表单")
@Data
public class PostdoctorFundsRecordForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "博士后经费信息ID")
    private Long postdoctorFundsId;

    @ApiModelProperty(value = "经费使用类型（1费用报销，2劳务专家咨询费支出，3固定资产新增，4其他）")
    @NotNull(message = "经费使用类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "使用原因")
    private String remark;

    @NotNull(message = "使用金额不能为空")
    @ApiModelProperty(value = "使用金额（元）")
    private BigDecimal useFunds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "使用时间")
    private LocalDate useTime;

    @ApiModelProperty(value = "结余金额（元）")
    private BigDecimal surplusFunds;


}

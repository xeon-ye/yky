package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
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
 * @Description : FlowStation新增修改form对象
 * @Modified :
 */
@ApiModel("新增FlowStation表单")
@Data
public class FlowStationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父级流动站ID")
    private Long pid;

    @ApiModelProperty(value = "流动站编码")
    private String stationCode;

    @ApiModelProperty(value = "流动站名称")
    private String stationName;

    @ApiModelProperty(value = "类型（1 一级学科，2 二级学科）")
    private Integer type;

    @ApiModelProperty(value = "流动站负责人")
    private String stationPreside;

    @ApiModelProperty(value = "状态（1有效，0无效）")
    private Integer status;

    @ApiModelProperty(value = "内部标识（1是，0否）")
    private Integer inside;

    @ApiModelProperty(value = "流动站描述")
    private String describe;

    @ApiModelProperty(value = "研究专业（一级学科为null，新增二级学科手动输入值）")
    private String discussMajor;

    @NotNull(message = "生效日期不能为空")
    @ApiModelProperty(value = "生效日期（格式:2019-04-01）")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

}

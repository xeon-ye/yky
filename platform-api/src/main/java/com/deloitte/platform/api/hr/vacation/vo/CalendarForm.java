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
 * @Date : Create in 2019-04-12
 * @Description : Calendar新增修改form对象
 * @Modified :
 */
@ApiModel("新增日历记录表单")
@Data
public class CalendarForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "日期")
    private String day;

    @ApiModelProperty(value = "时间")
    private LocalDateTime dates;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "日期类型 0 工作日 1休息日 2 节假日")
    private String daystype;

    @ApiModelProperty(value = "前端使用")
    private String overlap;

    @ApiModelProperty(value = "前端使用")
    private String color;

    @ApiModelProperty(value = "前端使用")
    private String rendering;
}

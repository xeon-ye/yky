package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description :   Calendar查询from对象
 * @Modified :
 */
@ApiModel("Calendar查询表单")
@Data
public class CalendarQueryForm extends BaseQueryForm<CalendarQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "日期")
    private String day;

    @ApiModelProperty(value = "时间")
    private LocalDateTime dates;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "日期类型 0 工作日 1休息日 2 节假日")
    private String daystype;

    private LocalDateTime startDate;

    private LocalDateTime enddDate;
}

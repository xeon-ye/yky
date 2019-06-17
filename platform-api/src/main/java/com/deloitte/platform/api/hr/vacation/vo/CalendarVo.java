package com.deloitte.platform.api.hr.vacation.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description : Calendar返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

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

    @ApiModelProperty(value = "前端使用")
    private String overlap;

    @ApiModelProperty(value = "前端使用")
    private String color;

    @ApiModelProperty(value = "前端使用")
    private String rendering;

}

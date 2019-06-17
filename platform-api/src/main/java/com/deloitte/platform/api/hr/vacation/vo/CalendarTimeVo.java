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
 * @Author : LJH
 * @Date : Create in 2019-05-29
 * @Description : CalendarTime返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarTimeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "日期类型")
    private String dataType;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "上午开始时间")
    private String morningStartTime;

    @ApiModelProperty(value = "上午结束时间")
    private String morningEndTime;

    @ApiModelProperty(value = "下午开始时间")
    private String eveningStartTime;

    @ApiModelProperty(value = "下午结束时间")
    private String eveningEndTime;

    @ApiModelProperty(value = "日历ID")
    private String calId;

}

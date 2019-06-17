package com.deloitte.platform.api.hr.vacation.vo;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-29
 * @Description : CalendarTime新增修改form对象
 * @Modified :
 */
@ApiModel("新增CalendarTime表单")
@Data
public class CalendarTimeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日期类型")
    private String dataType;

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

    private String careteBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String id;

}

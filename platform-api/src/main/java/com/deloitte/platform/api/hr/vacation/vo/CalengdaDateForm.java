package com.deloitte.platform.api.hr.vacation.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/4/19 14:44
 */
@ApiModel("新增假期日历维护表单")
@Data
public class CalengdaDateForm extends BaseForm {

    @ApiModelProperty(value = "日历维护数组")
    private List<CalendarForm> calendarForms;

}

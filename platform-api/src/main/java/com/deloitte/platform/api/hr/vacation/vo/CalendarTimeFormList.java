package com.deloitte.platform.api.hr.vacation.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/29 14:28
 */
@ApiModel("新增CalendarTime表单")
@Data
public class CalendarTimeFormList {

    private List<CalendarTimeForm> calendarTimeForms;

}

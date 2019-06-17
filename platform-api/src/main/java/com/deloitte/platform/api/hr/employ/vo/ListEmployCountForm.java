package com.deloitte.platform.api.hr.employ.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/4/11 9:29
 */
@ApiModel("新增ListEmployCount表单")
@Data
public class ListEmployCountForm {

    @ApiModelProperty(value = "应聘人员列表")
    private List<EmployCountForm> emplist;



}

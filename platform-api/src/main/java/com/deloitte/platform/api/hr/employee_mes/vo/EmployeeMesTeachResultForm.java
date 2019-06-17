package com.deloitte.platform.api.hr.employee_mes.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/10 10:11
 */
@Data
@ApiModel("员工自助教育信息表单")
public class EmployeeMesTeachResultForm {

    private List<EmployeeMesTeachForm> teachForms;

    private String empMesId;

    private String id;

    private String applyState;
}

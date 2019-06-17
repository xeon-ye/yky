package com.deloitte.platform.api.hr.employee.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/8 15:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitEmployeeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String numbersInside;

    private String numbersOutside;

    private String numberallNow;

    private String yearIncrease;

    private String yearReduction;

    private String dep;

    private String organization;

    private Integer threeYearsRetire;

}

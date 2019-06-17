package com.deloitte.platform.api.hr.retire.param;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/7 16:03
 */
@Data
public class RetirePeopleQueryFrom {

    private String manYearStart;

    private String manYearEnd;

    private String womanCadreYearStart;

    private String womanCadreYearEnd;

    private String womanYearStart;

    private String womanYearEnd;

    private LocalDateTime sameMonthStart;

    private LocalDateTime sameMonthEnd;
}

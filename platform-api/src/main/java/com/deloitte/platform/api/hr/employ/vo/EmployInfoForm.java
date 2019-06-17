package com.deloitte.platform.api.hr.employ.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/9 19:06
 */
@Data
public class EmployInfoForm {

    private List<String> ids;

    private LocalDateTime proposedEntryDate;
}

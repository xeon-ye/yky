package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description :  GccHighLevelPenson查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccHighLevelPensonQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String userName;
    private String choiceYear;
    private String projectName;
    private Long projectId;
    private String projectCategory;
    private Long projectCategoryId;
    private String batch;
    private String firstLevelSubject;
    private String secondLevelSubject;
    private String researchDirection;
    private String state;
    private String prolTechnicians;
    private String executiveFunction;
    private String professial;
    private LocalDateTime comeTime;
    private LocalDateTime transferTime;
    private Long enclosure;
    private String remarks;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String unit;

}

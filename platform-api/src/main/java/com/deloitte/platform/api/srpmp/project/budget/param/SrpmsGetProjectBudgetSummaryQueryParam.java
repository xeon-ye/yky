package com.deloitte.platform.api.srpmp.project.budget.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:LIJUN
 * Date:05/06/2019
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsGetProjectBudgetSummaryQueryParam extends BaseParam {

    private static final long serialVersionUID = 1L;

    private Long projectId;
    private String year;

}

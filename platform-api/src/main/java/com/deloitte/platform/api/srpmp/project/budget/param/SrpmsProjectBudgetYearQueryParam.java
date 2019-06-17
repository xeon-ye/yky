package com.deloitte.platform.api.srpmp.project.budget.param;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.param.BaseParam;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description :  SrpmsProjectBudgetApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetYearQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
 
    private String tableFlag;
    private String projectName;
    private String projectNum;
    private String projectType;
}

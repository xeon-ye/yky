package com.deloitte.platform.api.srpmp.project.budget.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :   SrpmsProjectBudgetSubject查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectBudgetSubject查询表单")
@Data
public class SrpmsProjectBudgetSubjectQueryForm extends BaseQueryForm<SrpmsProjectBudgetSubjectQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目类型CODE")
    private Long projectCategory;

    @ApiModelProperty(value = "科目明细（科目JSON字符串）")
    private String subjectDetail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}

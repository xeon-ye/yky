package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetSubject新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectBudgetSubject表单")
@Data
public class SrpmsProjectBudgetSubjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目类型CODE")
    private Long projectCategory;

    @ApiModelProperty(value = "科目明细（科目JSON字符串）")
    private String subjectDetail;

}

package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description : ZpcpSecondLevelSubject新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpSecondLevelSubject表单")
@Data
public class ZpcpSecondLevelSubjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学科编码")
    private String subjectCode;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

    @ApiModelProperty(value = "一级学科ID")
    private Long firstSubjectId;

}

package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description : ZpcpFirstLevelSubject新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpFirstLevelSubject表单")
@Data
public class ZpcpFirstLevelSubjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "学科编号")
    private String subjecyCode;

    @ApiModelProperty(value = "学科名称")
    private String subjectName;

}

package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicSubjectMap新增修改form对象
 * @Modified :
 */
@ApiModel("新增BasicSubjectMap表单")
@Data
public class BasicSubjectMapForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "签约主体id")
    private Long subjectId;

    @ApiModelProperty(value = "我方：1   对方：2")
    private String type;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "签约主体名称")
    private String subjectName;

    @ApiModelProperty(value = "单位法人姓名")
    private String deptLegalPersonName;
}

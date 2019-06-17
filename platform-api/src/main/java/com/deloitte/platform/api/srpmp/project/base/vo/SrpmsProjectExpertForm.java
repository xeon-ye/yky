package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-09
 * @Description : SrpmsProjectExpert新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectExpert表单")
@Data
public class SrpmsProjectExpertForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long projectId;

    @ApiModelProperty(value = "员工编号")
    private String expertId;

    @ApiModelProperty(value = "员工编号")
    private String expertNum;

    @ApiModelProperty(value = "专家名称")
    private String expertName;

    @ApiModelProperty(value = "性别")
    private String expertSex;

    @ApiModelProperty(value = "职称")
    private String expertTitle;

    @ApiModelProperty(value = "单位")
    private String expertOrg;

    @ApiModelProperty(value = "学科")
    private String expertSubject;

    @ApiModelProperty(value = "擅长领域")
    private String expertField;

    @ApiModelProperty(value = "${field.comment}")
    private String expertTel;

    @ApiModelProperty(value = "${field.comment}")
    private String type;

}

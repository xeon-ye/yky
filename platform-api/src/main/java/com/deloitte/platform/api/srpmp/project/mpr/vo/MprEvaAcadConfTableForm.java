package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaAcadConfTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaAcadConfTable表单")
@Data
public class MprEvaAcadConfTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "会议名称")
    private String conferenceName;

    @ApiModelProperty(value = "会议类型")
    private String conferenceType;

    @ApiModelProperty(value = "国外代表人数")
    private String foreReprNum;

    @ApiModelProperty(value = "国内代表人数")
    private String domeReprNum;

    @ApiModelProperty(value = "会议地点")
    private String conferencePlace;

    @ApiModelProperty(value = "举办时间")
    private String holdingTime;

}

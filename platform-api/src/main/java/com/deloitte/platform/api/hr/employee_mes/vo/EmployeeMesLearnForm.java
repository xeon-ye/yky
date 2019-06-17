package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description : EmployeeMesLearn新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesLearn表单")
@Data
public class EmployeeMesLearnForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "成果名称")
    private String outcomeName;

    @ApiModelProperty(value = "成果类别")
    private String outcomeType;

    @ApiModelProperty(value = "成果水平")
    private String outcomeLevel;

    @ApiModelProperty(value = "鉴定单位")
    private String identificationUnit;

    @ApiModelProperty(value = "论文标题")
    private String paperTitle;

    @ApiModelProperty(value = "发表时间")
    private LocalDateTime publicationTime;

    @ApiModelProperty(value = "何处发表")
    private String publicationPlace;

    @ApiModelProperty(value = "发表著作情况")
    private String publicationSituation;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "获奖时间")
    private LocalDateTime awardTime;

    @ApiModelProperty(value = "获奖项目名称")
    private String awardProjectName;

    @ApiModelProperty(value = "获奖名称")
    private String awardName;

    @ApiModelProperty(value = "获奖项目中担任角色")
    private String awardProjectRole;

    @ApiModelProperty(value = "授奖单位")
    private String awardUnit;

    @ApiModelProperty(value = "获专利时间")
    private LocalDateTime patentTime;

    @ApiModelProperty(value = "获专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利号")
    private String patentNumber;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

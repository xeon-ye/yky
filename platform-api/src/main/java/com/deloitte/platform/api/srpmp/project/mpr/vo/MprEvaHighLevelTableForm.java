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
 * @Description : MprEvaHighLevelTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaHighLevelTable表单")
@Data
public class MprEvaHighLevelTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "人才类型")
    private String talentType;

    @ApiModelProperty(value = "批准编号")
    private String approvalNumber;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "当选时间")
    private String electedDate;

    @ApiModelProperty(value = "单位")
    private String unit;

}

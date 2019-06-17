package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprEvaCoopResultTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaCoopResultTable表单")
@Data
public class MprEvaCoopResultTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    private String resultType;

    @ApiModelProperty(value = "协作单位")
    private String coopUnit;

    @ApiModelProperty(value = "协作人")
    private JSONArray coopPerson;

    @ApiModelProperty(value = "完成人员所在的任务")
    private String completePersonTask;

}

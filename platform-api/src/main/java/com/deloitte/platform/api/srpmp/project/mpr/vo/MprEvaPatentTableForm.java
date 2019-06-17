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
 * @Description : MprEvaPatentTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaPatentTable表单")
@Data
public class MprEvaPatentTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "申请号/授权号")
    private String applyAuthNum;

    @ApiModelProperty(value = "申请/批准国别")
    private String applyApprovalCountry;

    @ApiModelProperty(value = "专利类别（发明专利、实用新型专利、外观专利）")
    private String patentType;

    @ApiModelProperty(value = "完成人")
    private JSONArray completePerson;

    @ApiModelProperty(value = "完成时间")
    private String completeDate;

    @ApiModelProperty(value = "状态（申请/获批）")
    private String patentStatus;

    @ApiModelProperty(value = "是否应用")
    private String isUse;

}

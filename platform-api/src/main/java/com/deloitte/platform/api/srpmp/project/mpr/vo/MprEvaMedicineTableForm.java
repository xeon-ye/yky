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
 * @Description : MprEvaMedicineTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaMedicineTable表单")
@Data
public class MprEvaMedicineTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "产品类别")
    private String productType;

    @ApiModelProperty(value = "申报/注册分类")
    private String registrationType;

    @ApiModelProperty(value = "证书号")
    private String certNo;

    @ApiModelProperty(value = "参与人员")
    private JSONArray joinPerson;

    @ApiModelProperty(value = "批准时间")
    private String approvalDate;

}

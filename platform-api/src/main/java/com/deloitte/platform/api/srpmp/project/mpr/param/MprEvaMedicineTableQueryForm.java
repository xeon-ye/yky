package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprEvaMedicineTable查询from对象
 * @Modified :
 */
@ApiModel("MprEvaMedicineTable查询表单")
@Data
public class MprEvaMedicineTableQueryForm extends BaseQueryForm<MprEvaMedicineTableQueryParam>  {


    @ApiModelProperty(value = "主键ID")
    private Long id;

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
    private String joinPerson;

    @ApiModelProperty(value = "批准时间")
    private String approvalDate;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}

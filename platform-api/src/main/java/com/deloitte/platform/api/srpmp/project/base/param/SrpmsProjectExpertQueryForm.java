package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-09
 * @Description :   SrpmsProjectExpert查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectExpert查询表单")
@Data
public class SrpmsProjectExpertQueryForm extends BaseQueryForm<SrpmsProjectExpertQueryParam>  {

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "项目ID")
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

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String expertTel;

    @ApiModelProperty(value = "${field.comment}")
    private String type;
}

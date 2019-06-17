package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccTechnocracy查询from对象
 * @Modified :
 */
@ApiModel("GccTechnocracy查询表单")
@Data
public class GccTechnocracyQueryForm extends BaseQueryForm<GccTechnocracyQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "学员编号")
    private Long userId;

    @ApiModelProperty(value = "学员姓名")
    private String userName;

    @ApiModelProperty(value = "所在单位")
    private String unit;

    @ApiModelProperty(value = "申请年度")
    private String year;

    @ApiModelProperty(value = "日期")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "组织部门")
    private String department;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "项目类型 0表示 专家培训项目，1 表示专家休养项目")
    private String projectType;

    @ApiModelProperty(value = "地点")
    private String area;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;
}

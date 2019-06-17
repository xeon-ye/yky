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
 * @Description :   GccSpecialistGroup查询from对象
 * @Modified :
 */
@ApiModel("GccSpecialistGroup查询表单")
@Data
public class GccSpecialistGroupQueryForm extends BaseQueryForm<GccSpecialistGroupQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "评审项目")
    private Long reviewProject;

    @ApiModelProperty(value = "评审年份")
    private String reviewYear;

    @ApiModelProperty(value = "所属评审单位")
    private String reviewUnit;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "类型（所院/院校）")
    private String type;

    @ApiModelProperty(value = "分类（评审/考核）")
    private String classify;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;
}

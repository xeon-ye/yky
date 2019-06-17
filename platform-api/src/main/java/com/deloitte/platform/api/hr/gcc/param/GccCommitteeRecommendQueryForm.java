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
 * @Description :   GccCommitteeRecommend查询from对象
 * @Modified :
 */
@ApiModel("GccCommitteeRecommend查询表单")
@Data
public class GccCommitteeRecommendQueryForm extends BaseQueryForm<GccCommitteeRecommendQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "工作单位")
    private String unit;

    @ApiModelProperty(value = "专业职务")
    private String professionalDuty;

    @ApiModelProperty(value = "学科领域")
    private String areaStudy;

    @ApiModelProperty(value = "专家类别")
    private String expertsCategory;

    @ApiModelProperty(value = "投票（是/否） 0 否 ，1是")
    private String vote;

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

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;
}

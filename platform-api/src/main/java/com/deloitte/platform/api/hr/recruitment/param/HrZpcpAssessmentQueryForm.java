package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description :   HrZpcpAssessment查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpAssessment查询表单")
@Data
public class HrZpcpAssessmentQueryForm extends BaseQueryForm<HrZpcpAssessmentQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;
*/
    @ApiModelProperty(value = "教职工编号")
    private Long empCode;

    @ApiModelProperty(value = "在聘信息表ID")
    private String infoId;

    /*@ApiModelProperty(value = "考核类型")
    private String assessmentType;

    @ApiModelProperty(value = "考核时间")
    private LocalDateTime assessmentTime;

    @ApiModelProperty(value = "考核结果")
    private String assessmentResult;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "当前状态")
    private String status;*/
}

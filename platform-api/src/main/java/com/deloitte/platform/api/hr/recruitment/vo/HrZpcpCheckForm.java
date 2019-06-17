package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description : HrZpcpCheck新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpCheck表单")
@Data
public class HrZpcpCheckForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "申报表编号")
    private Long declareId;

    @ApiModelProperty(value = "审核小组编号")
    private Long checkGroudId;

    @ApiModelProperty(value = "申报岗位1票数")
    private Double postVote1;

    @ApiModelProperty(value = "申报岗位2票数")
    private Double postVote2;

    @ApiModelProperty(value = "推荐一级学科")
    private String recommendFirstClass;

    @ApiModelProperty(value = "推荐岗位")
    private String recommendPost;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "checkType审核类型(2,学术委员会,3.教授聘任委员会,4职聘任委员会)")
    private String checkType;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

}

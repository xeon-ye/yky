package com.deloitte.platform.api.hr.recruitment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description :   HrZpcpCheck查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpCheck查询表单")
@Data
public class HrZpcpCheckQueryForm extends BaseQueryForm<HrZpcpCheckQueryParam> {


    @ApiModelProperty(value = "申报表编号")
    private Long declareId;

    @ApiModelProperty(value = "审核类型(2.学术委员会,3.教授聘任委员会,4.教职聘任委员会)")
    private String checkType;

    /*@ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申报人姓名")
    private String declareName;

    @ApiModelProperty(value = "审核小组编号")
    private String checkGroudId;

    @ApiModelProperty(value = "申报岗位1票数")
    private Double postVote1;

    @ApiModelProperty(value = "申报岗位2票数")
    private Double postVote2;

    @ApiModelProperty(value = "推荐一级学科")
    private String recommendFirstClass;

    @ApiModelProperty(value = "推荐岗位")
    private String recommendPost;

    @ApiModelProperty(value = "审核状态")
    private String checkStatus;

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

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    */


}

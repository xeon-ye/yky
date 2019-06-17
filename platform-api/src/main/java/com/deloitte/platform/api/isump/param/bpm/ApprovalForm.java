package com.deloitte.platform.api.isump.param.bpm;

import com.deloitte.platform.api.oaservice.news.vo.ApprovalMatrixVo;
import com.deloitte.platform.api.oaservice.news.vo.ProcessTaskVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

/**
 * 构建新闻审批流程
 */
@Data
public class ApprovalForm extends BaseForm {

    /**
     * 该id为待办查出的主键id
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "审批意见/驳回意见")
    private String opinion;

    @ApiModelProperty(value = "审批类型")
    private String opinionType;

    @ApiModelProperty(value = "审批对象描述")
    private String objectDescription;

    @ApiModelProperty(value = "审批流程key")
    private String processDefineKey;

    @ApiModelProperty(value = "负责组织")
    private String chargeOrg;

    @ApiModelProperty(value = "流程任务ID")
    private String taskId;

    /**
     * 前端调用历史查询接口，并选择某一条数据进行返回，后端用该字段接收
     */
    @ApiModelProperty(value = "ProcessTaskVo返回的VO对象")
    ProcessTaskVo processTaskVo;

    /**
     * 查询待办，获取该字段，根据该字段获取并修改新闻状态
     */
    @ApiModelProperty(value = "审批对象id")
    private String objectId;

    @ApiModelProperty(value = "下一个审批路径")
    private String objectUrl;

    /**
     * 用于接收前端传入的多个审批人
     */
    @ApiModelProperty(value = "矩阵")
    List<ApprovalMatrixVo> nextMatriList;

    @ApiModelProperty(value = "系统集合")
    List<String> systemList;

    @ApiModelProperty(value = "会签审批类型")
    private String pageType;

}

package com.deloitte.platform.api.srpmp.project.task.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeVO;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-17
 * @Description : SrpmsProjectTaskTranLong返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectTaskTranLongVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "参与程度")
    private String proEnterType;

    @ApiModelProperty(value = "参与程度名称")
    private String proEnterTypeName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;

    @ApiModelProperty(value = "预期成果类型")
    private String projectResultType;

    @ApiModelProperty(value = "所在领域")
    private String belongDomain;

    @ApiModelProperty(value = "学科分类")
    private String subjectCategory;

    @ApiModelProperty(value = "任务分解相关说明")
    private String taskDecompositionInstruction;

    @ApiModelProperty(value = "参加人员相关说明")
    private String joinPersonInstruction;

    @ApiModelProperty(value = "预算相关说明")
    private String budgetInstruction;

    @ApiModelProperty(value = "项目经费来源")
    private JSONArray proFundsSource;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "项目执行开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "承担单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "院内项目参加人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "院外项目参加人员")
    private JSONArray outJoinPerson;

    @ApiModelProperty(value = "预算明细JSON")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "相关附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

    @ApiModelProperty(value = "任务分解")
    private List<SrpmsProjectTaskVo> subTaskList;

    @ApiModelProperty(value = "审批历史")
    private List<TaskNodeVO> approveHistory;

    @ApiModelProperty(value = "项目状态")
    private String status;

}

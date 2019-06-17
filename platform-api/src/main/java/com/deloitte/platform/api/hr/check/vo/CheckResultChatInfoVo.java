package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultChatInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核结果id")
    private String checkResultId;

    @ApiModelProperty(value = "绩效沟通通知名称")
    private String chatName;

    @ApiModelProperty(value = "沟通状态")
    private String chatStatus;

    @ApiModelProperty(value = "绩效结果确认")
    private String checkResultConfirm;

    @ApiModelProperty(value = "是否同意考核结果")
    private String isAgree;

    @ApiModelProperty(value = "绩效申述")
    private String achAppeal;

    @ApiModelProperty(value = "附件id")
    private String fileId;

    @ApiModelProperty(value = "申诉处理意见")
    private String appealHandleOpinion;

    @ApiModelProperty(value = "申诉结果")
    private String appeaResult;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "考核组织")
    private String checkOrgId;

    @ApiModelProperty(value = "所在部门")
    private String department;

    @ApiModelProperty(value = "考核等级")
    private String systemLevel;

    @ApiModelProperty(value = "排名")
    private Long ranking;

    @ApiModelProperty(value = "考核得分")
    private Double systemScore;

}

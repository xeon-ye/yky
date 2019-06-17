package com.deloitte.platform.api.srpmp.project.base.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description : SrpmsProjectDeptJoin返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectDeptJoinVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long projectId;

    @ApiModelProperty(value = "序号")
    private Integer sort;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "合作类型CODE")
    private String joinWay;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "任务负责人")
    private String taskLeaderName;

    @ApiModelProperty(value = "人数")
    private String peopleCount;

    @ApiModelProperty(value = "联系邮箱")
    private String email;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "联系邮箱或电话")
    private String phoneOrEmail;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}

package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description :   SrpmsProjectDeptJoin查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectDeptJoin查询表单")
@Data
public class SrpmsProjectDeptJoinQueryForm extends BaseQueryForm<SrpmsProjectDeptJoinQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
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

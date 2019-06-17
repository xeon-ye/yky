package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description : SrpmsProjectDeptJoin新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectDeptJoin表单")
@Data
public class SrpmsProjectDeptJoinForm extends BaseForm {
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

    @ApiModelProperty(value = "联系邮箱")
    private String email;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "联系邮箱或电话")
    private String phoneOrEmail;

    @ApiModelProperty(value = "国家")
    private String country;

}

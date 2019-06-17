package com.deloitte.platform.api.srpmp.project.update.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-01
 * @Description :   SrpmsProjectUpdate查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectUpdate查询表单")
@Data
public class SrpmsProjectUpdateQueryForm extends BaseQueryForm<SrpmsProjectUpdateQueryParam>  {


    @ApiModelProperty(value = "项目变更ID")
    private Long id;

    @ApiModelProperty(value = "变更单号")
    private String updateNumber;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目负责人")
    private String leadPersonName;

    @ApiModelProperty(value = "批示文件ID")
    private Long fileId;

    @ApiModelProperty(value = "批示文件名")
    private String fileName;

    @ApiModelProperty(value = "批示文件URL")
    private String fileUrl;

    @ApiModelProperty(value = "变更类型")
    private String updateType;

    @ApiModelProperty(value = "变更原因")
    private String updateReason;

    @ApiModelProperty(value = "变更状态")
    private String updateState;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "单位ID")
    private String deptId;

    @ApiModelProperty(value = "变更标识")
    private int editFlag;

    @ApiModelProperty(value = "显示table标识,1-我的申请，2-变更记录")
    private int tableFlag;

    @ApiModelProperty(value = "预算书编号")
    private int budNum;
}
package com.deloitte.platform.api.srpmp.project.accept.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAccept返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectAcceptVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;

    @ApiModelProperty(value = "依托单位")
    private String deptName;

    @ApiModelProperty(value = "项目标识")
    private String projectFlag;

    @ApiModelProperty(value = "项目开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目结束时间")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "验收状态")
    private String status;

    @ApiModelProperty(value = "验收状态名称")
    private String statusName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}

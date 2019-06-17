package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccProDecNotice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccProDecNoticeSelfVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "发布单位")
    private String releaseUnit;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目主键")
    private String  projectId;

    @ApiModelProperty(value = "项目类别")
    private String projectCategroy;

    @ApiModelProperty(value = "发布时间")
    private LocalDate releaseTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态 0保存 1 提交 2 所院审核通过  3 所院评审中 4 院校审核通过 5 院校评审中，6院样审核通过，7上级部门审核通过，8 审核未通过 9未申报")
    private String status;

    @ApiModelProperty(value = "填表时间")
    private LocalDateTime formTime;

    @ApiModelProperty(value = "填表时间")
    private String  batch;

    @ApiModelProperty(value = "填表时间")
    private String  applyYear;

    @ApiModelProperty(value = "单位名称")
    private String  releaseUnitNmae;

}

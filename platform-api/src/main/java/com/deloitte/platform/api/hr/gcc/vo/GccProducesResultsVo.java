package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccProducesResults返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccProducesResultsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "人员编号")
    private String userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "概述")
    private String summarize;

    @ApiModelProperty(value = "本科教学情况（1对多课程）")
    private String undergraduateTeach;

    @ApiModelProperty(value = "科研育人情况（1对多课程）")
    private String scientificTeach;

    @ApiModelProperty(value = "其他情况")
    private String other;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "本科讲授课程")
    private String underCourse;

    @ApiModelProperty(value = "本科学时")
    private String underPeriod;

    @ApiModelProperty(value = "本科选学")
    private String underElementary;

    @ApiModelProperty(value = "研究生讲授课程")
    private String graduateCourse;

    @ApiModelProperty(value = "研究生学时")
    private String graduatePeriod;

    @ApiModelProperty(value = "研究生选学")
    private String graduateElementary;


}

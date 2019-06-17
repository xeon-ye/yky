package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description : CheckDeadline返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckDeadlineInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkName;

    @ApiModelProperty(value = "考核期间id")
    private String checkTimeId;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "提醒业务类型")
    private String warnType;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "截至日期")
    private LocalDate endTime;

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

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

}

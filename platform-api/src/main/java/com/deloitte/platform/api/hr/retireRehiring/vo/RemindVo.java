package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemindVo extends BaseVo {

    @ApiModelProperty(value = "人员列表")
    private List<RemindRecordVo> remindRecordVos;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "注释")
    private String id;

    @ApiModelProperty(value = "通知类型()")
    private String remindType;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "通知时间")
    private LocalDate remindTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "保留字段1")
    private String ex1;

    @ApiModelProperty(value = "保留字段2")
    private String ex2;

    @ApiModelProperty(value = "保留字段3")
    private String ex3;
}

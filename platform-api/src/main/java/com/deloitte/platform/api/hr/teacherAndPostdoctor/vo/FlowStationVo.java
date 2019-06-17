package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : FlowStation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowStationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "父级流动站ID")
    private String pid;

    @ApiModelProperty(value = "流动站编码")
    private String stationCode;

    @ApiModelProperty(value = "流动站名称")
    private String stationName;

    @ApiModelProperty(value = "类型（1 一级学科，2 二级学科）")
    private Integer type;

    @ApiModelProperty(value = "流动站负责人code")
    private String stationPreside;

    @ApiModelProperty(value = "流动站负责人")
    private String stationPresideName;

    @ApiModelProperty(value = "流动站到期提醒人ID")
    private String stationExpirePresideId;

    @ApiModelProperty(value = "流动站到期提醒人")
    private String username;

    @ApiModelProperty(value = "状态（1有效，0无效）")
    private Integer status;

    @ApiModelProperty(value = "是否删除（1是，0否）")
    private Integer isDelete;

    @ApiModelProperty(value = "内部标识（1是，0否）")
    private Integer inside;

    @ApiModelProperty(value = "流动站描述")
    private String describe;

    @ApiModelProperty(value = "研究专业（一级学科为null，新增二级学科手动输入值）")
    private String discussMajor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生效日期")
    private LocalDate effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;
//
//    @ApiModelProperty(value = "最后更新人ID")
//    private Long updateBy;
//
//    @ApiModelProperty(value = "创建人ID")
//    private Long createBy;
//
//    @ApiModelProperty(value = "组织机构代码")
//    private String orgCode;

}

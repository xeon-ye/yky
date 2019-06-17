package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpPaper返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpPaperVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "发布时间")
    private LocalDate publishTime;

    @ApiModelProperty(value = "论文题目")
    private String paperTitle;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否为通讯作者（0.不是，1.是）")
    private String isCorrespondingAuthor;

    @ApiModelProperty(value = "期刊影响因子")
    private String impactFactor;

    @ApiModelProperty(value = "发表刊物")
    private String publication;

    @ApiModelProperty(value = "是否为代表性论文（0.不是，1.是）")
    private String isRepresentative;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

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

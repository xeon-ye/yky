package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  * 项目表
 *  * </p>
 *
 * @author zhengchun
 * @since 2019-04-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsAndApplicationVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目编码Code")
    private Long projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "负责人Id")
    private Long projectHeaderId;

    @ApiModelProperty(value = "负责人名称")
    private String projectHeaderName;

    @ApiModelProperty(value = "项目类型Code")
    private Long projectTypeCode;

    @ApiModelProperty(value = "项目类型名称")
    private String projectTypeName;

    @ApiModelProperty(value = "项目类别Code")
    private Long subProjectTypeCode;

    @ApiModelProperty(value = "项目类别名称")
    private String subProjectTypeName;

    @ApiModelProperty(value = "周期")
    private Long cycle;

    /**
     * application 项目状态
     */
    @ApiModelProperty(value = "projectId")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long projectId;

    @ApiModelProperty(value = "评审状态 code")
    private Long reviewStateCode;

    @ApiModelProperty(value = "评审状态 name")
    private String reviewStateName;

    @ApiModelProperty(value = "申报年度")
    private String theApplicationYear;

    @ApiModelProperty(value = "开始执行年度")
    private Long planYear;
    /**
     * 评审意见
     *
     */
    @ApiModelProperty(value = "applicationId(申报书Id)")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long applicationId;

    @ApiModelProperty(value = "review 评审意见")
    private String reviewAdvice;

}

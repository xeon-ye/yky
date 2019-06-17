package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-01
 * @Description : MprEvaFileInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprEvaFileInfoVoExt extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "附件一")
    private MprEvaFileInfoFieldForm annexOne;

    @ApiModelProperty(value = "附件二")
    private List<MprEvaFileInfoFieldForm> annexTwo = Lists.newArrayList();

    @ApiModelProperty(value = "附件三")
    private MprEvaFileInfoFieldForm annexThree;

    @ApiModelProperty(value = "附件四")
    private MprEvaFileInfoFieldForm annexFour;

    @ApiModelProperty(value = "附件五")
    private MprEvaFileInfoFieldForm annexFive;

    @ApiModelProperty(value = "附件六")
    private MprEvaFileInfoFieldForm annexSix;

    @ApiModelProperty(value = "附件七")
    private MprEvaFileInfoFieldForm annexSeven;

    @ApiModelProperty(value = "附件八")
    private MprEvaFileInfoFieldForm annexEight;

    @ApiModelProperty(value = "附件十")
    private MprEvaFileInfoFieldForm annexTen;

    @ApiModelProperty(value = "附件十一")
    private MprEvaFileInfoFieldForm annexEleven;

    @ApiModelProperty(value = "附件十二")
    private MprEvaFileInfoFieldForm annexTwelve;

    @ApiModelProperty(value = "其他附件")
    private List<MprEvaFileInfoFieldForm> annexOther = Lists.newArrayList();

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}

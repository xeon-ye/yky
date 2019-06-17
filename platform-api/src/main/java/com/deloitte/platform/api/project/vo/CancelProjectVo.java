package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/10 10:34
 * @Description : 取消项目申请Vo
 * @Modified:
 */
@Api(value = "项目取消申请VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelProjectVo extends BaseVo {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("项目from")
    private ProjectsVo projectsVo;

    @ApiModelProperty("申报书from")
    private ApplicationVo applicationVo;

    @ApiModelProperty("业务单号from")
    private ServiceNumVo serviceNumVo;

    @ApiModelProperty("申报书附件（List）")
    private List<FileVo> enclosureVoList = Lists.newArrayList();

    @ApiModelProperty("项目取消附件（List）")
    private List<FileVo> projectCancelenclosureVoList = Lists.newArrayList();

}

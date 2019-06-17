package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:LIJUN
 * Date:27/03/2019
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprSaveOrUpdateVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报告基本信息")
    private MprEvaBaseInfoVo baseInfoVo;

    @ApiModelProperty(value = "附件列表")
    private List<SrpmsProjectAttachmentForm> srpmsProjectAttachmentFormList;
}


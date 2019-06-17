package com.deloitte.platform.api.srpmp.project.update.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdateContent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectUpdateContentSaveVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目变更单号")
    private String updateNumber;

    @ApiModelProperty(value = "项目内容变更明细")
    private JSONArray details;
}

package com.deloitte.platform.api.srpmp.project.update.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-01
 * @Description : SrpmsProjectUpdateMenber返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectUpdateMenberSaveVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目变更单号")
    private String updateNumber;

    @ApiModelProperty(value = "参与人员JSON")
    private JSONArray joinPerson;

    @ApiModelProperty(value = "院外参与人员JSON")
    private JSONArray outJoinPerson;

}

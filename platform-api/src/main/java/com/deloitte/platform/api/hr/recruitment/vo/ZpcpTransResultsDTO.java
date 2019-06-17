package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpTransResultsDTO extends BaseVo {

    @ApiModelProperty(value = "科技成果转化对象")
    private List<ZpcpTransResultsVo> zpcpTransResultsVo;

    @ApiModelProperty(value = "科技成果转化说明")
    private String techResultDesc;

    @ApiModelProperty(value = "科技成果转化说明分数")
    private String techResultScore;

    @ApiModelProperty(value = "通知id")
    private long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;
}

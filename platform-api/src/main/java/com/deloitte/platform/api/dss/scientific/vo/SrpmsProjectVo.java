package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研项目分阶段统计Vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申请数量")
    private Long applyNum;

    @ApiModelProperty(value = "立项数量")
    private Long approvalNum;

    @ApiModelProperty(value = "执行数量")
    private Long excuteNum;

    @ApiModelProperty(value = "结题数量")
    private Long conclusionNum;

    @ApiModelProperty(value = "提交成果数量")
    private Long resultNum;

}

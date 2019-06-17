package com.deloitte.platform.api.dss.scientific.vo;


import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 科研成果论文数量展示
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptPaperSCIVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    private Long deptId;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "论文数量")
    private Integer paper;

    @ApiModelProperty(value = "sci数量")
    private Integer sciNum;

}

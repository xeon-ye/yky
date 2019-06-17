package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-09
 * @Description : GccHighLevelPenson返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccHighLevelPensonCountVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "人才项目名称")
    private String projectName;

    @ApiModelProperty(value = "人才项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "历年人数")
    private Long numpeo;

    @ApiModelProperty(value = "在岗人数")
    private Long numjob;

}

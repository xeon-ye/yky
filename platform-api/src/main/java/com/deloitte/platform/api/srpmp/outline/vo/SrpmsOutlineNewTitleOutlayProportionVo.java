package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-20
 * @Description : 查询统计返回对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineNewTitleOutlayProportionVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "项目编号")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "题录-新获项目总经费")
    private Double totalOutlay;

    @ApiModelProperty(value = "题录-新获项目到位经费")
    private Double receiveOutlay;

    @ApiModelProperty(value = "题录-新获项目总经费求和")
    private Double sumTotalOutlay;

    @ApiModelProperty(value = "题录-新获项目到位经费求和")
    private Double sumReceiveOutlay;

}

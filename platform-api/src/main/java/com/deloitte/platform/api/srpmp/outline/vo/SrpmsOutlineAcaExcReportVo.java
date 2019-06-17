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
public class SrpmsOutlineAcaExcReportVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "国内会议主办数")
    private int inTeamHost;

    @ApiModelProperty(value = "国内会议参加数")
    private int inTeamJoin;

    @ApiModelProperty(value = "国际会议主办数")
    private int outTeamHost;

    @ApiModelProperty(value = "国际会议参加数")
    private int outTeamJoin;

}

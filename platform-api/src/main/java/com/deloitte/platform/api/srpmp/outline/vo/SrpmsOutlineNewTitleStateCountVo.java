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
public class SrpmsOutlineNewTitleStateCountVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "新中标项目统计")
    private int newCount;
    @ApiModelProperty(value = "在研项目统计")
    private int researchCount;
    @ApiModelProperty(value = "结题项目统计")
    private int endCount;
    @ApiModelProperty(value = "终止项目统计")
    private int terminationCount;
    @ApiModelProperty(value = "取消项目统计")
    private int cancelCount;
    @ApiModelProperty(value = "延期项目统计")
    private int delayCount;

}

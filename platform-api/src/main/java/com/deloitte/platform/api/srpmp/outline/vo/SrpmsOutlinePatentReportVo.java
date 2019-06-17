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
public class SrpmsOutlinePatentReportVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "专利总数")
    private int totalCount;

    @ApiModelProperty(value = "国内授权专利数")
    private int inAuthCount;

    @ApiModelProperty(value = "国内申请专利数")
    private int inNoAuthCount;

    @ApiModelProperty(value = "国外授权专利数")
    private int outAuthCount;

    @ApiModelProperty(value = "国外申请专利数")
    private int outNoAuthCount;

}

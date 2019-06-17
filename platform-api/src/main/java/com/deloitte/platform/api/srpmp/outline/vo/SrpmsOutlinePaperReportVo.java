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
public class SrpmsOutlinePaperReportVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "国内期刊全国数")
    private int inJournalNationCount;

    @ApiModelProperty(value = "国内期刊省内数")
    private int inJournalCount;

    @ApiModelProperty(value = "国外期刊数")
    private int outJournalCount;

    @ApiModelProperty(value = "国外期刊SCI一等数")
    private int outSciFirstCount;

    @ApiModelProperty(value = "国外期刊SCI二等数")
    private int outSciSecoendCount;

    @ApiModelProperty(value = "国外期刊SCI三等数")
    private int outSciThreeCount;

    @ApiModelProperty(value = "国外期刊SCI四等数")
    private int outSciFourCount;

}

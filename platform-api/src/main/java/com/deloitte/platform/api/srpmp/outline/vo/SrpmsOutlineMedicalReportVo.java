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
public class SrpmsOutlineMedicalReportVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "新药总数量")
    private int totalNewCount;
    @ApiModelProperty(value = "西药一类统计数量")
    private int wFirstCount;
    @ApiModelProperty(value = "西药二类类统计数量")
    private int wSecondCount;
    @ApiModelProperty(value = "西药三类统计数量")
    private int wThreeCount;
    @ApiModelProperty(value = "西药四类统计数量")
    private int wFourCount;

    @ApiModelProperty(value = "中药一类统计数量")
    private int zhFirstCount;
    @ApiModelProperty(value = "中药二类类统计数量")
    private int zhSecondCount;
    @ApiModelProperty(value = "中药三类统计数量")
    private int zhThreeCount;
    @ApiModelProperty(value = "中药四类统计数量")
    private int zhFourCount;
    @ApiModelProperty(value = "中药五类统计数量")
    private int zhFiveCount;

    @ApiModelProperty(value = "生物药剂一类统计数量")
    private int sFirstCount;
    @ApiModelProperty(value = "生物药剂二类类统计数量")
    private int sSecondCount;
    @ApiModelProperty(value = "生物药剂三类统计数量")
    private int sThreeCount;
    @ApiModelProperty(value = "生物药剂四类统计数量")
    private int sFourCount;

    @ApiModelProperty(value = "医疗器械统计总数量")
    private int totalMedicalCount;
    @ApiModelProperty(value = "医疗器械一类统计数量")
    private int qFirstCount;
    @ApiModelProperty(value = "医疗器械二类类统计数量")
    private int qSecondCount;
    @ApiModelProperty(value = "医疗器械三类统计数量")
    private int qThreeCount;
    @ApiModelProperty(value = "医疗器械四类统计数量")
    private int qFourCount;

}

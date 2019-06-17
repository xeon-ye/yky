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
public class SrpmsOutlineNewTitleReportOutlayVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "国家新药项目统计")
    private int gxProjectOutlay;
    @ApiModelProperty(value = "国家重点项目统计")
    private int gProjectOutlay;
    @ApiModelProperty(value = "技术创新引导专项统计")
    private int makeOutlay;
    @ApiModelProperty(value = "基地和人才专项统计")
    private int specialOutlay;
    @ApiModelProperty(value = "重大科学计划项目统计")
    private int techBraceOutlay;
    @ApiModelProperty(value = "科技支撑计划项目统计")
    private int bigTechOutlay;
    @ApiModelProperty(value = "973计划统计")
    private int planFirstOutlay;
    @ApiModelProperty(value = "863计划统计")
    private int planSecoendOutlay;
    @ApiModelProperty(value = "牵头单位统计")
    private int headOutlay;
    @ApiModelProperty(value = "国际合作项目统计")
    private int cooperateProjectOutlay;
    @ApiModelProperty(value = "其他计划项目统计")
    private int otherPlanOutlay;
    
    @ApiModelProperty(value = "面上项目统计")
    private int overallOutlay;
    @ApiModelProperty(value = "重点项目统计")
    private int pointProjectOutlay;
    @ApiModelProperty(value = "重大项目统计")
    private int bigProjectOutlay;
    @ApiModelProperty(value = "重大研究计划项目统计")
    private int planProjectOutlay;
    @ApiModelProperty(value = "青年科学基金项目统计")
    private int projectFirstOutlay;
    @ApiModelProperty(value = "地区科学基金项目统计")
    private int projectSecoendOutlay;
    @ApiModelProperty(value = "优秀青年科学基金项目统计")
    private int projectThreeOutlay;
    @ApiModelProperty(value = "国家杰出青年科学基金项目统计")
    private int projectFourOutlay;
    @ApiModelProperty(value = "创新群体项目统计")
    private int projectFiveOutlay;
    @ApiModelProperty(value = "国际（地区）合作研究项目统计")
    private int projectSixOutlay;
    @ApiModelProperty(value = "海外及港澳学者合作研究基金项目统计")
    private int projectSevenOutlay;
    @ApiModelProperty(value = "国家重大科研仪器研制项目统计")
    private int projectEightOutlay;
    @ApiModelProperty(value = "联合基金项目统计")
    private int projectNineOutlay;
    @ApiModelProperty(value = "应急管理项目统计")
    private int projectTenOutlay;

    @ApiModelProperty(value = "国家卫生健康委行业专项统计")
    private int guildOutlay;
    @ApiModelProperty(value = "教育部项目统计")
    private int moeProjectOutlay;
    @ApiModelProperty(value = "国家发改委项目统计")
    private int ndrcOutlay;
    @ApiModelProperty(value = "国家药监局项目统计")
    private int sfdaOutlay;
    @ApiModelProperty(value = "国家中医药局项目统计")
    private int satcmOutlay;
    @ApiModelProperty(value = "其他部委项目统计")
    private int otherMacOutlay;
    @ApiModelProperty(value = "地方项目统计")
    private int placeOutlay;
    @ApiModelProperty(value = "国际合作项目统计")
    private int natunalOutlay;
    @ApiModelProperty(value = "与企业联合项目统计")
    private int enterCooOutlay;

}

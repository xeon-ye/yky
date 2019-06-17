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
public class SrpmsOutlineNewTitleReportCountVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "国家新药项目统计")
    private int gxProjectCount;
    @ApiModelProperty(value = "国家新药课题统计")
    private int gxTopicCount;
    @ApiModelProperty(value = "国家新药任务统计")
    private int gxTaskCount;
    @ApiModelProperty(value = "国家传染项目统计")
    private int gcProjectCount;
    @ApiModelProperty(value = "国家传染课题统计")
    private int gcTopicCount;
    @ApiModelProperty(value = "国家传染任务统计")
    private int gcTaskCount;
    @ApiModelProperty(value = "国家重点项目统计")
    private int gProjectCount;
    @ApiModelProperty(value = "国家重点课题统计")
    private int gTopicCount;
    @ApiModelProperty(value = "国家重点任务统计")
    private int gTaskCount;
    @ApiModelProperty(value = "技术创新引导专项统计")
    private int makeCount;
    @ApiModelProperty(value = "基地和人才专项统计")
    private int specialCount;
    @ApiModelProperty(value = "牵头单位统计")
    private int headCount;
    @ApiModelProperty(value = "参加单位统计")
    private int joinCount;
    @ApiModelProperty(value = "国际合作项目统计")
    private int cooperateProjectCount;
    @ApiModelProperty(value = "其他计划项目统计")
    private int otherPlanCount;

    @ApiModelProperty(value = "面上项目统计")
    private int overallCount;
    @ApiModelProperty(value = "重点项目统计")
    private int pointProjectCount;
    @ApiModelProperty(value = "重点课题统计")
    private int pointTopicCount;
    @ApiModelProperty(value = "重大项目统计")
    private int bigProjectCount;
    @ApiModelProperty(value = "重大课题统计")
    private int bigTopicCount;
    @ApiModelProperty(value = "重大研究计划项目统计")
    private int planProjectCount;
    @ApiModelProperty(value = "重大研究计划课题统计")
    private int planTopicCount;
    @ApiModelProperty(value = "青年科学基金项目统计")
    private int projectFirstCount;
    @ApiModelProperty(value = "地区科学基金项目统计")
    private int projectSecoendCount;
    @ApiModelProperty(value = "优秀青年科学基金项目统计")
    private int projectThreeCount;
    @ApiModelProperty(value = "国家杰出青年科学基金项目统计")
    private int projectFourCount;
    @ApiModelProperty(value = "创新群体项目统计")
    private int projectFiveCount;
    @ApiModelProperty(value = "国际（地区）合作研究项目统计")
    private int projectSixCount;
    @ApiModelProperty(value = "海外及港澳学者合作研究基金项目统计")
    private int projectSevenCount;
    @ApiModelProperty(value = "国家重大科研仪器研制项目统计")
    private int projectEightCount;
    @ApiModelProperty(value = "联合基金项目统计")
    private int projectNineCount;
    @ApiModelProperty(value = "应急管理项目统计")
    private int projectTenCount;

    @ApiModelProperty(value = "国家卫生健康委行业专项统计")
    private int guildCount;
    @ApiModelProperty(value = "国家卫生健康委其他项目统计")
    private int otherCount;
    @ApiModelProperty(value = "教育部项目统计")
    private int moeProjectCount;
    @ApiModelProperty(value = "教育部创新团队项目统计")
    private int moeMakeCount;
    @ApiModelProperty(value = "国家发改委项目统计")
    private int ndrcCount;
    @ApiModelProperty(value = "国家药监局项目统计")
    private int sfdaCount;
    @ApiModelProperty(value = "国家中医药局项目统计")
    private int satcmCount;
    @ApiModelProperty(value = "其他部委项目统计")
    private int otherMacCount;
    @ApiModelProperty(value = "地方项目统计")
    private int placeCount;
    @ApiModelProperty(value = "国际合作项目统计")
    private int natunalCount;
    @ApiModelProperty(value = "与企业联合项目统计")
    private int enterCooCount;

}

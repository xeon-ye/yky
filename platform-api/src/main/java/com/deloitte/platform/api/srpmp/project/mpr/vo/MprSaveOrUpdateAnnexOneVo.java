package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author:LIJUN
 * Date:27/03/2019
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MprSaveOrUpdateAnnexOneVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本情况")
    private MprEvaBaseInfoVo baseInfoVo;

    @ApiModelProperty(value = "经费使用情况")
    private List<MprEvaFundsBudgetVo> fundsBudgetVoList = Lists.newArrayList();

    @ApiModelProperty(value = "任务列表")
    private List<SrpmsProjectTaskVo> projectTaskVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目参与人员")
    private List<SrpmsProjectPersonJoinVo> personJoinVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目中期绩效目标及考核指标完成情况")
    private List<MprEvaTaskIndicatorVo> taskIndicatorVoList = Lists.newArrayList();

    @ApiModelProperty(value = "代表性成果")
    private List<MprEvaOutcomeVo> outcomeVoList = Lists.newArrayList();

    @ApiModelProperty(value = "新理论、新技术、新产品等情况")
    private List<MprEvaTheoTechProTableVo> theoTechProTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "成果转化情况")
    private List<MprEvaTransTableVo> transTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "示范推广服务情况")
    private List<MprEvaPromotionTableVo> promotionTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "论文发表情况表-科普")
    private List<MprEvaTreaPopTableVo> treaPopTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "论文发表情况")
    private List<MprEvaTreaTableVo> treaTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "专著/教材发表情况")
    private List<MprEvaMonoTableVo> monoTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "专利申请授权情况")
    private List<MprEvaPatentTableVo> patentTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "获得新药、疫苗、医疗器械证书数、临床批件数情况")
    private List<MprEvaMedicineTableVo> medicineTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "技术标准获批情况")
    private List<MprEvaTechStanTableVo> techStanTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "科技奖励信息")
    private List<MprEvaTechRewardTableVo> techRewardTableVoLists = Lists.newArrayList();

    @ApiModelProperty(value = "项目内人员牵头国家重大科研任务情况")
    private List<MprEvaResearchTaskTableVo> researchTaskTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "举办学术会议情况")
    private List<MprEvaAcadConfTableVo> acadConfTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目内人员重要学术组织任职情况")
    private List<MprAcadPostTableVo> acadPostTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "项目内人员重要期刊任职情况")
    private List<MprJouPostTableVo> jouPostTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "高层次人才培养情况")
    private List<MprEvaHighLevelTableVo> highLevelTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "团队各任务间协作成果")
    private List<MprEvaCoopResultTableVo> coopResultTableVoList = Lists.newArrayList();

    @ApiModelProperty(value = "统计信息")
    private String annexStat;
}


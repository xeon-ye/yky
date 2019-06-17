package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author:LIJUN
 * Date:27/03/2019
 * Description:
 */
@Data
@ApiModel("新增或者更新附件一表单")
public class MprSaveOrUpdateAnnexOneForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目基本情况")
    @NotNull
    private MprEvaBaseInfoForm baseInfoForm;

    @ApiModelProperty(value = "经费使用情况")
    private List<MprEvaFundsBudgetForm> fundsBudgetFormList;

    @ApiModelProperty(value = "项目中期绩效目标及考核指标完成情况")
    private List<MprEvaTaskIndicatorForm> taskIndicatorFormList;

    @ApiModelProperty(value = "代表性成果")
    private List<MprEvaOutcomeForm> outcomeFormList;

    @ApiModelProperty(value = "新理论、新技术、新产品等情况")
    private List<MprEvaTheoTechProTableForm> theoTechProTableFormList;

    @ApiModelProperty(value = "成果转化情况")
    private List<MprEvaTransTableForm> transTableFormList;

    @ApiModelProperty(value = "示范推广服务情况")
    private List<MprEvaPromotionTableForm> promotionTableFormList;

    @ApiModelProperty(value = "论文发表情况表-科普")
    private List<MprEvaTreaPopTableForm> treaPopTableFormList;

    @ApiModelProperty(value = "论文发表情况表")
    private List<MprEvaTreaTableForm> treaTableFormList;

    @ApiModelProperty(value = "专著/教材发表情况")
    private List<MprEvaMonoTableForm> monoTableFormList;

    @ApiModelProperty(value = "专利申请授权情况")
    private List<MprEvaPatentTableForm> patentTableFormList;

    @ApiModelProperty(value = "获得新药、疫苗、医疗器械证书数、临床批件数情况")
    private List<MprEvaMedicineTableForm> medicineTableFormList;

    @ApiModelProperty(value = "技术标准获批情况")
    private List<MprEvaTechStanTableForm> techStanTableFormList;

    @ApiModelProperty(value = "科技奖励信息")
    private List<MprEvaTechRewardTableForm> techRewardTableFormLists;

    @ApiModelProperty(value = "项目内人员牵头国家重大科研任务情况")
    private List<MprEvaResearchTaskTableForm> researchTaskTableFormList;

    @ApiModelProperty(value = "举办学术会议情况")
    private List<MprEvaAcadConfTableForm> acadConfTableFormList;

    @ApiModelProperty(value = "项目内人员重要学术组织任职情况")
    private List<MprAcadPostTableForm> acadPostTableFormList;

    @ApiModelProperty(value = "项目内人员重要期刊任职情况")
    private List<MprJouPostTableForm> jouPostTableFormList;

    @ApiModelProperty(value = "高层次人才培养情况")
    private List<MprEvaHighLevelTableForm> highLevelTableFormList;

    @ApiModelProperty(value = "团队各任务间协作成果")
    private List<MprEvaCoopResultTableForm> coopResultTableFormList;

    @ApiModelProperty(value = "附件一统计信息")
    private String annexStat;
}


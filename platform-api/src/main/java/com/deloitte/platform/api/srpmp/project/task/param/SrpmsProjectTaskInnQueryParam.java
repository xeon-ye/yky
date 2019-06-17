package com.deloitte.platform.api.srpmp.project.task.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :  SrpmsProjectTaskInn查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectTaskInnQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private Long leadPersonWorkTime;
    private Long bothTopWorkTime;
    private Long researchMemberSize;
    private Double researchWorkTime;
    private String leadPersonNote;
    private String bothTopNote;
    private String approvalNecessay;
    private String researchContentMain;
    private String researchTarget;
    private String taskMasterMethod;
    private String achievementForm;
    private String mainContents;
    private String exchangeProgramme;
    private String achievementBenefit;
    private String researchContentMethod;
    private String taskOrgManageMode;
    private String knowledgeResultManage;
    private String riskAnalyzeManage;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}

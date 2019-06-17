package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  PostdoctorHarvest查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostdoctorHarvestQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long postdoctorApplyId;
    private String projectName;
    private String source;
    private Long projectFunds;
    private LocalDateTime projectStartTime;
    private LocalDateTime projectEndTime;
    private String projectLevel;
    private String assumeRole;
    private String attachmentProjectId;
    private String paperName;
    private String publishPress;
    private String influenceFactor;
    private String record;
    private String attachmentPaperId;
    private String bookName;
    private String press;
    private String attachmentBookId;
    private String patentName;
    private String patentCode;
    private String fromCountry;
    private LocalDateTime fromTime;
    private String attachmentPatentId;
    private String fundName;
    private Long supportFunds;
    private LocalDateTime getTime;
    private String getLevel;
    private String isOver;
    private String attachmentFundId;
    private String academicReportName;
    private String meetingName;
    private LocalDateTime meetingTime;
    private String meetingPlace;
    private String remark;
    private String attachmentReportId;
    private LocalDateTime pullTime;
    private String honorName;
    private LocalDateTime approvalTime;
    private String attachmentHonorId;
    private Integer harvestType;
    private LocalDateTime paperPublishTime;
    private String paperRanking;
    private String postdoctorName;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String bookRanking;
    private String patentRanking;
    private LocalDateTime bookPublishTime;
    private String orgCode;

}

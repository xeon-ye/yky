package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorHarvest新增修改form对象
 * @Modified :
 */
@ApiModel("在站成果自助申请表单")
@Data
public class PostdoctorHarvestForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申请人ID")
    private Long postdoctorId;

    @NotNull(message = "保存类型不能为空")
    @Range(min = 1,max = 2,message = "保存类型值错误")
    @ApiModelProperty(value = "保存类型（1保存，2提交）")
    private Integer keep;

    @ApiModelProperty(value = "项目课题名称")
    private String projectName;

    @ApiModelProperty(value = "项目来源")
    private String source;

    @ApiModelProperty(value = "科研项目经费（万元）")
    private Long projectFunds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "项目开始时间")
    private LocalDate  projectStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "项目结束时间")
    private LocalDate projectEndTime;

    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

    @ApiModelProperty(value = "担任角色（负责人/主要参与/一般参与）")
    private String assumeRole;

    @ApiModelProperty(value = "项目附件URL")
    private String attachmentProjectId;

    @ApiModelProperty(value = "论文题目")
    private String paperName;

    @ApiModelProperty(value = "发表刊物名称")
    private String publishPress;

    @ApiModelProperty(value = "影响因子")
    private String influenceFactor;

    @ApiModelProperty(value = "SCI/EI收录/核心期刊")
    private String record;

    @ApiModelProperty(value = "论文附件URL")
    private String attachmentPaperId;

    @ApiModelProperty(value = "著作名称")
    private String bookName;

    @ApiModelProperty(value = "出版单位")
    private String press;

    @ApiModelProperty(value = "著作附件URL")
    private String attachmentBookId;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利编号")
    private String patentCode;

    @ApiModelProperty(value = "授予国家")
    private String fromCountry;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "授予时间")
    private LocalDate fromTime;

    @ApiModelProperty(value = "专利附件URL")
    private String attachmentPatentId;

    @ApiModelProperty(value = "基金名称")
    private String fundName;

    @ApiModelProperty(value = "获资助金额(万元)")
    private Long supportFunds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "获取时间")
    private LocalDate getTime;

    @ApiModelProperty(value = "获资助等级（一等、二等、三等）")
    private String getLevel;

    @ApiModelProperty(value = "是否结题（1是，2否）")
    private String isOver;

    @ApiModelProperty(value = "基金附件URL")
    private String attachmentFundId;

    @ApiModelProperty(value = "学术报告（论文）名称")
    private String academicReportName;

    @ApiModelProperty(value = "会议名称")
    private String meetingName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "会议时间")
    private LocalDate meetingTime;

    @ApiModelProperty(value = "会议地点")
    private String meetingPlace;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "学术报告附件URL")
    private String attachmentReportId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "奖励及荣誉称号名称")
    private String honorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "批准时间")
    private LocalDate approvalTime;

    @ApiModelProperty(value = "荣誉附件URL ")
    private String attachmentHonorId;

//    @ApiModelProperty(value = "科研成果类型（1参与科研项目，2科研论文发表，3出版著作，4获得专利，5获得基金资助，6国际交流，7博士后获得奖励及荣誉）")
//    private Integer harvestType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "论文出版时间")
    private LocalDate paperPublishTime;

    @ApiModelProperty(value = "论文本人排名")
    private String paperRanking;

    @ApiModelProperty(value = "博士后姓名 ")
    private String postdoctorName;

    @ApiModelProperty(value = "著作本人排名")
    private String bookRanking;

    @ApiModelProperty(value = "专利本人排名")
    private String patentRanking;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "著作出版时间")
    private LocalDate bookPublishTime;


}

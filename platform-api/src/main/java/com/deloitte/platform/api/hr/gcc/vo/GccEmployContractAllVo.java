package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description : GccEmployContract返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccEmployContractAllVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String  id;

    @ApiModelProperty(value = "人员编号")
    private String userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "入选年份")
    private String choiceYear;

    @ApiModelProperty(value = "人才项目名称")
    private String projectName;

    @ApiModelProperty(value = "人才项目类别")
    private String projectCategory;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否提醒")
    private String warn;

    @ApiModelProperty(value = "一级学科")
    private String firstLevelSubject;

    @ApiModelProperty(value = "二级学科")
    private String secondLevelSubject;

    @ApiModelProperty(value = "研究方向")
    private String researchDirection;

    @ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEnddate;

    @ApiModelProperty(value = "是否需签合同")
    private String signContract;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "合同到期日期 -1为过期,0表示1-3,1为未到期 ")
    private int betweenMonth;

    @ApiModelProperty(value = "入选人员编号")
    private String  highLevelId;

    @ApiModelProperty(value = "附件信息对象-附件")
    private HrAttachmentVo attachmentVo;

    @ApiModelProperty(value = "附件信息对象-离职报告证明")
    private HrAttachmentVo attachmentVo1;

    @ApiModelProperty(value = "附件信息对象-离职报告证明")
    private HrAttachmentVo attachmentVo2;

}

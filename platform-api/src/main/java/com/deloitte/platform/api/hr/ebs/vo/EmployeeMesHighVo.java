package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesHigh返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMesHighVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;

    @ApiModelProperty(value = "入选人才项目")
    private String includeProject;

    @ApiModelProperty(value = "人才流入")
    private String brainGain;

    @ApiModelProperty(value = "一级学科")
    private String subject1;

    @ApiModelProperty(value = "二级学科")
    private String subject2;

    @ApiModelProperty(value = "三级学科")
    private String subject3;

    @ApiModelProperty(value = "当前研究方向")
    private String researchDir;

    @ApiModelProperty(value = "入选年度")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "人员现状")
    private String situation;

    @ApiModelProperty(value = "调入时间")
    private LocalDateTime comeTime;

    @ApiModelProperty(value = "调出时间")
    private LocalDateTime transferTime;

    @ApiModelProperty(value = "人员现状变动时间")
    private LocalDateTime changeTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创新团队名称")
    private String innovationName;

    @ApiModelProperty(value = "重点领域创新团队（名称）/示范基地")
    private String innovationArea;

    @ApiModelProperty(value = "课题名称")
    private String subjectName;

    @ApiModelProperty(value = "团队研究方向内容")
    private String directionTeam;

    @ApiModelProperty(value = "回国前任职单位")
    private String beforeUnit;

    @ApiModelProperty(value = "回国前任职单位（英文）")
    private String beforeUnitEs;

    @ApiModelProperty(value = "回国前所任职称或职务")
    private String beforePostion;

    @ApiModelProperty(value = "回国前所任职称或职务（英文）")
    private String beforePostionEs;

    @ApiModelProperty(value = "聘任部门")
    private String employDepart;

    @ApiModelProperty(value = "聘任职务")
    private String employPostion;

    @ApiModelProperty(value = "资助类别")
    private String fundingCategory;

    @ApiModelProperty(value = "资助年度")
    private String fundingYear;

    @ApiModelProperty(value = "资助金额（万")
    private String fundingAmout;

}

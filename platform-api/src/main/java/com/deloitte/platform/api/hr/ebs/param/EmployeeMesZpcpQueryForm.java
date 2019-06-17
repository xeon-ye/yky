package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesZpcp查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesZpcp查询表单")
@Data
public class EmployeeMesZpcpQueryForm extends BaseQueryForm<EmployeeMesZpcpQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

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

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "岗位")
    private String postion;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "岗位类型")
    private String postionType;

    @ApiModelProperty(value = "一级学科")
    private String subject1;

    @ApiModelProperty(value = "二级学科")
    private String subject2;

    @ApiModelProperty(value = "专业技术职务")
    private String prolTechnicians;

    @ApiModelProperty(value = "专业研究方向")
    private String majorDirection;

    @ApiModelProperty(value = "研究所导师资格")
    private String graduateQualification;

    @ApiModelProperty(value = "从事专业一级学科")
    private String majorSubject1;

    @ApiModelProperty(value = "从事专业二级学科")
    private String majorSubject2;

    @ApiModelProperty(value = "从事专业三级学科")
    private String majorSubject3;

    @ApiModelProperty(value = "考核类型")
    private String inspectionType;

    @ApiModelProperty(value = "考核日期")
    private String inspectionDate;

    @ApiModelProperty(value = "考核结果")
    private String inspectionResult;

    @ApiModelProperty(value = "考核意见")
    private String inspectionOption;

    @ApiModelProperty(value = "聘用状态")
    private String employStatus;

    @ApiModelProperty(value = "聘用开始日期")
    private LocalDateTime employStartDate;

    @ApiModelProperty(value = "聘用结束日期")
    private LocalDateTime employEndDate;

    @ApiModelProperty(value = "任职年限（年）")
    private String servedYear;

    @ApiModelProperty(value = "基本薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪（万元）")
    private String annualPay;

    @ApiModelProperty(value = "安家费（万元）")
    private String settleFree;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDateTime contractStartDate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDateTime contractEndDate;

    @ApiModelProperty(value = "聘用时长（月）")
    private String employDuration;
}

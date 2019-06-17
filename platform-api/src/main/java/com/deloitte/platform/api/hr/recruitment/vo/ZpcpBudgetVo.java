package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description : ZpcpBudget返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpBudgetVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    /*ZB.ID,
            T.YEAR,
            T.BATCH,
            T.EMP_CODE,
            T.NAME,
            D.DEPT_NAME,
            S.STATION_NAME,
            AA.EMPLOYMENT_STATUS,
            AA.START_TIME,
            AA.END_TIME,
            BB.SALARY_SYSTEM,
            ZB.TOTAL_SALAY,
            ZB.TOTAL_SETTLE,
            ZB.YEAR,
            ZB.SETTL_PAY,
            ZB.REMARKS*/

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "年份")
    private String year1;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "职工编号")
    private String empCode;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String deptName;

    @ApiModelProperty(value = "单位编号")
    private String deptCode;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用时间起")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "聘用时间止")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪总额")
    private Long totalSalay;

    @ApiModelProperty(value = "在聘信息表id")
    private Long infoId;

    @ApiModelProperty(value = "安家预算总额（元）")
    private Long totalSettle;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "拨付安家费")
    private Long settlPay;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

}

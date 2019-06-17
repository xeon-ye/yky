package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-24
 * @Description : HrZpcpSalaryWelfare返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrZpcpSalaryWelfareVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String deptName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "在聘信息表ID")
    private String infoId;

    @ApiModelProperty(value = "岗位")
    private String post;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long salaryYear;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "安家费")
    private Double settle;

    @ApiModelProperty(value = "任职年限")
    private String tenureService;

    @ApiModelProperty(value = "文件对象")
    private HrAttachmentVo hrAttachmentVo;

}

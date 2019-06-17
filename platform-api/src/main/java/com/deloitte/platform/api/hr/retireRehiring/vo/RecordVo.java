package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description : RetireRemindRecord返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别 1 男 2女 3不详")
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    @ApiModelProperty(value = "部门编码")
    private String organizationCode;

    @ApiModelProperty(value = "部门名称")
    private String organizationName;

    @ApiModelProperty(value = "拟定退休日期")
    private LocalDate retireDate;

    @ApiModelProperty(value = "拟定返聘截止日期")
    private LocalDate rebateData;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "专业技术职务")
    private String specTechJob;


    @ApiModelProperty(value = "返聘部门")
    private Long rehiringDept;

    @ApiModelProperty(value = "返聘岗位")
    private Long rehiringPost;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDate endTime;

}

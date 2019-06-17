package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrApplyingFlow返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrApplyingFlowVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "流程")
    private String technologicalProcess;

    @ApiModelProperty(value = "结果：待审核，通过，未通过")
    private String result;

    @ApiModelProperty(value = "拟入职日期")
    private LocalDateTime proposedEntryDate;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value= "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请部门编码")
    private String appDepcode;

    @ApiModelProperty(value = "申请岗位编码")
    private String appPostcode;

    private String type;


}

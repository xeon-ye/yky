package com.deloitte.platform.api.hr.registrationResultEnquiry.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description :   HrApplyingFlow查询from对象
 * @Modified :
 */
@ApiModel("HrApplyingFlow查询表单")
@Data
public class HrApplyingFlowQueryForm extends BaseQueryForm<HrApplyingFlowQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "流程")
    private String technologicalProcess;

    @ApiModelProperty(value = "结果：待审核，通过，未通过")
    private String result;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "填表年份")
    private String year;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;
}

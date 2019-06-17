package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccAnnualCheckUser返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccAnnualCheckUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "序号")
    private String orderNumber;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申请项目名称")
    private String applyProjectName;

    @ApiModelProperty(value = "申请项目类别")
    private String applyType;

    @ApiModelProperty(value = "教职工id")
    private String userId;

    @ApiModelProperty(value = "考核年度")
    private String checkYear;

    @ApiModelProperty(value = "考核日期")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "考核结果")
    private String checkResult;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}

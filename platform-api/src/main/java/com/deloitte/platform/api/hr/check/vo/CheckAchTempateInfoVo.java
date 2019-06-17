package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description : CheckAchTempate返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchTempateInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "业绩考核模板名称")
    private String achTempateName;

    @ApiModelProperty(value = "考核表类型")
    private String checkTableTypeId;

    @ApiModelProperty(value = "考核表类型名称")
    private String checkTableTypeName;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkId;

    @ApiModelProperty(value = "考核工作名称")
    private String checkWorkName;

    @ApiModelProperty(value = "考核单位")
    private String checkDepartId;

    @ApiModelProperty(value = "权重")
    private String weight;

    @ApiModelProperty(value = "允许条数")
    private Long allowNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

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

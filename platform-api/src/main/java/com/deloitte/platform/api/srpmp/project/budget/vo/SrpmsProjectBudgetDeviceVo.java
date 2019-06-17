package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectBudgetDevice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectBudgetDeviceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "序号")
    private Long serial;

    @ApiModelProperty(value = "聚合类型CODE")
    private String joinWay;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备分类CODE")
    private String deviceCat;

    @ApiModelProperty(value = "设备类型CODE")
    private String deviceType;

    @ApiModelProperty(value = "设备型号")
    private String deviceNo;

    @ApiModelProperty(value = "设备数量")
    private String deviceNum;

    @ApiModelProperty(value = "设备生产国别与地区")
    private String producer;

    @ApiModelProperty(value = "主要技术性能指标")
    private String techQuota;

    @ApiModelProperty(value = "用途")
    private String useage;

    @ApiModelProperty(value = "测试化验加工内容(测试化验)")
    private String content;

    @ApiModelProperty(value = "测试化验加工单位(测试化验)")
    private String deptName;

    @ApiModelProperty(value = "计量单位(测试化验)")
    private String measurementUnit;

    @ApiModelProperty(value = "单价")
    private String unitPrice;

    @ApiModelProperty(value = "数量")
    private String deviceCount;

    @ApiModelProperty(value = "总价")
    private String amount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}

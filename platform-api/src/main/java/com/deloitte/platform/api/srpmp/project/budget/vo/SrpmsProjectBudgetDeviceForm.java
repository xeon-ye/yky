package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description : SrpmsProjectBudgetDevice新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectBudgetDevice表单")
@Data
public class SrpmsProjectBudgetDeviceForm extends BaseForm {
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
    private Double unitPrice;

    @ApiModelProperty(value = "数量")
    private Double deviceCount;

    @ApiModelProperty(value = "总价")
    private Double amount;

}

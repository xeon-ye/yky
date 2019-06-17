package com.deloitte.services.srpmp.project.budget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 设备化验预算明细表
 * </p>
 *
 * @author lixin
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_BUDGET_DEVICE")
@ApiModel(value="SrpmsProjectBudgetDevice对象", description="设备化验预算明细表")
public class SrpmsProjectBudgetDevice extends Model<SrpmsProjectBudgetDevice> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "序号")
    @TableField("SERIAL")
    private Long serial;

    @ApiModelProperty(value = "聚合类型CODE")
    @TableField("JOIN_WAY")
    private String joinWay;

    @ApiModelProperty(value = "设备名称")
    @TableField("DEVICE_NAME")
    private String deviceName;

    @ApiModelProperty(value = "设备分类CODE")
    @TableField("DEVICE_CAT")
    private String deviceCat;

    @ApiModelProperty(value = "设备类型CODE")
    @TableField("DEVICE_TYPE")
    private String deviceType;

    @ApiModelProperty(value = "设备型号")
    @TableField("DEVICE_NO")
    private String deviceNo;

    @ApiModelProperty(value = "设备数量")
    @TableField("DEVICE_NUM")
    private String deviceNum;

    @ApiModelProperty(value = "设备生产国别与地区")
    @TableField("PRODUCER")
    private String producer;

    @ApiModelProperty(value = "主要技术性能指标")
    @TableField("TECH_QUOTA")
    private String techQuota;

    @ApiModelProperty(value = "用途")
    @TableField("USEAGE")
    private String useage;

    @ApiModelProperty(value = "测试化验加工内容(测试化验)")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "测试化验加工单位(测试化验)")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "计量单位(测试化验)")
    @TableField("MEASUREMENT_UNIT")
    private String measurementUnit;

    @ApiModelProperty(value = "单价")
    @TableField("UNIT_PRICE")
    private Double unitPrice;

    @ApiModelProperty(value = "数量")
    @TableField("DEVICE_COUNT")
    private Double deviceCount;

    @ApiModelProperty(value = "总价")
    @TableField("AMOUNT")
    private Double amount;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String SERIAL = "SERIAL";

    public static final String JOIN_WAY = "JOIN_WAY";

    public static final String DEVICE_NAME = "DEVICE_NAME";

    public static final String DEVICE_CAT = "DEVICE_CAT";

    public static final String DEVICE_TYPE = "DEVICE_TYPE";

    public static final String DEVICE_NO = "DEVICE_NO";

    public static final String DEVICE_NUM = "DEVICE_NUM";

    public static final String PRODUCER = "PRODUCER";

    public static final String TECH_QUOTA = "TECH_QUOTA";

    public static final String USEAGE = "USEAGE";

    public static final String CONTENT = "CONTENT";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String MEASUREMENT_UNIT = "MEASUREMENT_UNIT";

    public static final String UNIT_PRICE = "UNIT_PRICE";

    public static final String DEVICE_COUNT = "DEVICE_COUNT";

    public static final String AMOUNT = "AMOUNT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

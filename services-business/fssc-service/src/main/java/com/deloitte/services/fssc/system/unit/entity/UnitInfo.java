package com.deloitte.services.fssc.system.unit.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 单位表
 * </p>
 *
 * @author qiliao
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_UNIT_INFO")
@ApiModel(value="UnitInfo对象", description="单位表")
public class UnitInfo extends Model<UnitInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private Long unitCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "单位类型")
    @TableField("UNIT_TYPE")
    private String unitType;

    @ApiModelProperty(value = "所属区域")
    @TableField("AREA_ID")
    private Long areaId;


    @ApiModelProperty(value = "联系人")
    @TableField("CONCAT_USER_NAME")
    private String concatUserName;
    @ApiModelProperty(value = "联系方式")
    @TableField("CONCAT_WAY")
    private String concatWay;

    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "是否有效")
    @TableField("IS_VALID")
    private String isValid;

    @ApiModelProperty(value = "状态")
    @TableField("AUDIT_STATUS")
    private String auditStatus;

    @ApiModelProperty(value = "纳税识别号")
    @TableField("TAX_REGIST_NUM")
    private String taxRegistNum;


    @ApiModelProperty(value = "收款方式")
    @TableField("RECIEVE_MONEY_TYPE")
    private String recieveMoneyType;



    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT_1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT_2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT_3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT_4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT_5")
    private String ext5;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "版本")
    @TableField(value = "VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "地址")
    @TableField(value = "ADDRESS")
    private String address;

    @ApiModelProperty(value = "统一信用代码")
    @TableField(value = "COMMON_CREDIT_CODE")
    private String commonCreditCode;

    public static final String ID = "ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String UNIT_TYPE = "UNIT_TYPE";

    public static final String UNIT_AREA = "UNIT_AREA";

    public static final String CONCAT_USER_ID = "CONCAT_USER_ID";

    public static final String CREATE_USER_ID = "CREATE_USER_ID";

    public static final String IS_VALID = "IS_VALID";

    public static final String AUDIT_STATUS = "AUDIT_STATUS";

    public static final String TAX_REGIST_NUM = "TAX_REGIST_NUM";

    public static final String BANK_ID = "BANK_ID";

    public static final String RECIEVE_MONEY_TYPE = "RECIEVE_MONEY_TYPE";

    public static final String ADDRESS = "ADDRESS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

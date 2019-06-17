package com.deloitte.services.contract.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 签约主体 数据来源：HR系统
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_SIGN_SUBJECT_INFO")
@ApiModel(value="SysSignSubjectInfo对象", description="签约主体 数据来源：HR系统")
public class SysSignSubjectInfo extends Model<SysSignSubjectInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "签约主体编码")
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    @ApiModelProperty(value = "签约主体")
    @TableField("SUBJECT_NAME")
    private String subjectName;

    @ApiModelProperty(value = "纳税人识别号")
    @TableField("TAX_NUM")
    private String taxNum;

    @ApiModelProperty(value = "增值税纳税人类型")
    @TableField("TAX_PAY_TYPE")
    private String taxPayType;

    @ApiModelProperty(value = "详细地址")
    @TableField("SUBJECT_ADDRESS")
    private String subjectAddress;

    @ApiModelProperty(value = "状态")
    @TableField("STATUE")
    private String statue;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;

    @ApiModelProperty(value = "开户银行")
    @TableField("ACCOUNT_BANK")
    private String accountBank;

    @ApiModelProperty(value = "账号名称")
    @TableField("ACCOUNT_NAME")
    private String accountName;

    @ApiModelProperty(value = "银行账号")
    @TableField("BANK_NAME")
    private String bankName;


    public static final String ID = "ID";

    public static final String SUBJECT_CODE = "SUBJECT_CODE";

    public static final String SUBJECT_NAME = "SUBJECT_NAME";

    public static final String TAX_NUM = "TAX_NUM";

    public static final String TAX_PAY_TYPE = "TAX_PAY_TYPE";

    public static final String SUBJECT_ADDRESS = "SUBJECT_ADDRESS";

    public static final String STATUE = "STATUE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String IS_USED = "IS_USED";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String ACCOUNT_BANK = "ACCOUNT_BANK";

    public static final String ACCOUNT_NAME = "ACCOUNT_NAME";

    public static final String BANK_NAME = "BANK_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.deloitte.services.isump.entity;

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
 * 
 * </p>
 *
 * @author zhangdi
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_FSSC_USER")
@ApiModel(value="FsscUser对象", description="")
public class FsscUser extends Model<FsscUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "工资卡号")
    @TableField("WAGE_CARD_NUMBER")
    private String wageCardNumber;

    @ApiModelProperty(value = "公务卡号")
    @TableField("OFFICIAL_CARD_NUMBER")
    private String officialCardNumber;

    @ApiModelProperty(value = "工资卡银行名称")
    @TableField("WAGE_CARD_BANKNAME")
    private String wageCardBankname;

    @ApiModelProperty(value = "公务卡银行名称")
    @TableField("OFFICIAL_CARD_BANKNAME")
    private String officialCardBankname;

    @ApiModelProperty(value = "工资卡联行号")
    @TableField("WAGE_CARD_LINKNUMBER")
    private String wageCardLinknumber;

    @ApiModelProperty(value = "公务卡联行号")
    @TableField("OFFICIAL_CARD_LINKNUMBER")
    private String officialCardLinknumber;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "首次来华时间")
    @TableField("FIRST_VISIT_DATE")
    private LocalDateTime firstVisitDate;

    @ApiModelProperty(value = "预计离境时间")
    @TableField("EXPECTED_DEPART_DATE")
    private LocalDateTime expectedDepartDate;

    @ApiModelProperty(value = "任职受雇从业类型")
    @TableField("EMPLOY_TYPE")
    private String employType;

    @ApiModelProperty(value = "任职受雇从业日期")
    @TableField("EMPLOY_DATE")
    private LocalDateTime employDate;

    @ApiModelProperty(value = "有无境内住所(0：无 1：有)")
    @TableField("DWELLING_PLACE")
    private Integer dwellingPlace;

    @ApiModelProperty(value = "是否个人居民(0：否 1：是)")
    @TableField("PERSONAL_RESIDENT")
    private Integer personalResident;

    @ApiModelProperty(value = "涉税事由")
    @TableField("TAX_CAUSE")
    private String taxCause;

    @ApiModelProperty(value = "收款人类型")
    @TableField("PAYEE_TYPE")
    private String payeeType;

    public static final String ID = "ID";

    public static final String USER_ID = "USER_ID";

    public static final String WAGE_CARD_NUMBER = "WAGE_CARD_NUMBER";

    public static final String OFFICIAL_CARD_NUMBER = "OFFICIAL_CARD_NUMBER";

    public static final String WAGE_CARD_BANKNAME = "WAGE_CARD_BANKNAME";

    public static final String OFFICIAL_CARD_BANKNAME = "OFFICIAL_CARD_BANKNAME";

    public static final String WAGE_CARD_LINKNUMBER = "WAGE_CARD_LINKNUMBER";

    public static final String OFFICIAL_CARD_LINKNUMBER = "OFFICIAL_CARD_LINKNUMBER";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String FIRST_VISIT_DATE = "FIRST_VISIT_DATE";

    public static final String EXPECTED_DEPART_DATE = "EXPECTED_DEPART_DATE";

    public static final String EMPLOY_TYPE = "EMPLOY_TYPE";

    public static final String EMPLOY_DATE = "EMPLOY_DATE";

    public static final String DWELLING_PLACE = "DWELLING_PLACE";

    public static final String PERSONAL_RESIDENT = "PERSONAL_RESIDENT";

    public static final String TAX_CAUSE = "TAX_CAUSE";

    public static final String PAYEE_TYPE = "PAYEE_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 合同基本信息配置
 * </p>
 *
 * @author yangyq
 * @since 2019-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_BASIC_CONFIGURA")
@ApiModel(value="BasicConfigura对象", description="合同基本信息配置")
public class BasicConfigura extends Model<BasicConfigura> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "合同类型")
    @TableField("CONTRACT_CODE")
    private String contractCode;

    @ApiModelProperty(value = "基本信息（1：显示；0：不显示）")
    @TableField("BASIC_FLAG")
    private String basicFlag;

    @ApiModelProperty(value = "关联合同（1：显示；0：不显示）")
    @TableField("CONTRACT_FLAG")
    private String contractFlag;

    @ApiModelProperty(value = "履行人信息（1：显示；0：不显示）")
    @TableField("EXECUT_FLAG")
    private String executFlag;

    @ApiModelProperty(value = "财务信息（1：显示；0：不显示）")
    @TableField("FINANCE_FLAG")
    private String financeFlag;

    @ApiModelProperty(value = "订单信息（1：显示；0：不显示）")
    @TableField("ORDER_FLAG")
    private String orderFlag;

    @ApiModelProperty(value = "项目信息（1：显示；0：不显示）")
    @TableField("PROJECT_FLAG")
    private String projectFlag;

    @ApiModelProperty(value = "其他监控计划（1：显示；0：不显示）")
    @TableField("MONITOR_FLAG")
    private String monitorFlag;

    @ApiModelProperty(value = "收票信息（1：显示；0：不显示）")
    @TableField("TICKET_FLAG")
    private String ticketFlag;

    @ApiModelProperty(value = "履行计划1，履行查验2")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK_FLAG")
    private String remarkFlag;

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


    public static final String ID = "ID";

    public static final String CONTRACT_CODE = "CONTRACT_CODE";

    public static final String BASIC_FLAG = "BASIC_FLAG";

    public static final String CONTRACT_FLAG = "CONTRACT_FLAG";

    public static final String EXECUT_FLAG = "EXECUT_FLAG";

    public static final String FINANCE_FLAG = "FINANCE_FLAG";

    public static final String ORDER_FLAG = "ORDER_FLAG";

    public static final String PROJECT_FLAG = "PROJECT_FLAG";

    public static final String MONITOR_FLAG = "MONITOR_FLAG";

    public static final String TICKET_FLAG = "TICKET_FLAG";

    public static final String TYPE = "TYPE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}

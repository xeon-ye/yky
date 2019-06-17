package com.deloitte.services.srpmp.project.mpr.entity;

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
 * 成果转化情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_TRANS_TABLE")
@ApiModel(value="MprEvaTransTable对象", description="成果转化情况表")
public class MprEvaTransTable extends Model<MprEvaTransTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    @TableField("OUTCOME_NAME")
    private String outcomeName;

    @ApiModelProperty(value = "技术转让")
    @TableField("TECH_TRANS")
    private String techTrans;

    @ApiModelProperty(value = "联合研发")
    @TableField("UNION_DEV")
    private String unionDev;

    @ApiModelProperty(value = "技术服务")
    @TableField("TECH_SERVICE")
    private String techService;

    @ApiModelProperty(value = "合同签订年份")
    @TableField("CONTRACT_SIGN_YEAR")
    private String contractSignYear;

    @ApiModelProperty(value = "合同金额（万元）")
    @TableField("CONTRACT_AMOUNT")
    private String contractAmount;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String OUTCOME_NAME = "OUTCOME_NAME";

    public static final String TECH_TRANS = "TECH_TRANS";

    public static final String UNION_DEV = "UNION_DEV";

    public static final String TECH_SERVICE = "TECH_SERVICE";

    public static final String CONTRACT_SIGN_YEAR = "CONTRACT_SIGN_YEAR";

    public static final String CONTRACT_AMOUNT = "CONTRACT_AMOUNT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

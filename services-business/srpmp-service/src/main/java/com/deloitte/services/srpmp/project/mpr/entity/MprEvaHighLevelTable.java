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
 * 高层次人才培养情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_HIGH_LEVEL_TABLE")
@ApiModel(value="MprEvaHighLevelTable对象", description="高层次人才培养情况表")
public class MprEvaHighLevelTable extends Model<MprEvaHighLevelTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = " 主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "人才类型")
    @TableField("TALENT_TYPE")
    private String talentType;

    @ApiModelProperty(value = "批准编号")
    @TableField("APPROVAL_NUMBER")
    private String approvalNumber;

    @ApiModelProperty(value = "批次")
    @TableField("BATCH")
    private String batch;

    @ApiModelProperty(value = "当选时间")
    @TableField("ELECTED_DATE")
    private String electedDate;

    @ApiModelProperty(value = "单位")
    @TableField("UNIT")
    private String unit;

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

    public static final String NAME = "NAME";

    public static final String TALENT_TYPE = "TALENT_TYPE";

    public static final String APPROVAL_NUMBER = "APPROVAL_NUMBER";

    public static final String BATCH = "BATCH";

    public static final String ELECTED_DATE = "ELECTED_DATE";

    public static final String UNIT = "UNIT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

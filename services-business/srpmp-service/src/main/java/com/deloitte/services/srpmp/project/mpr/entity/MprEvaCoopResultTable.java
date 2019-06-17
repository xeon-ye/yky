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
 * 团队各任务间协作成果
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_COOP_RESULT_TABLE")
@ApiModel(value="MprEvaCoopResultTable对象", description="团队各任务间协作成果")
public class MprEvaCoopResultTable extends Model<MprEvaCoopResultTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    @TableField("RESULT_NAME")
    private String resultName;

    @ApiModelProperty(value = "成果类型")
    @TableField("RESULT_TYPE")
    private String resultType;

    @ApiModelProperty(value = "协作单位")
    @TableField("COOP_UNIT")
    private String coopUnit;

    @ApiModelProperty(value = "协作人")
    @TableField("COOP_PERSON")
    private String coopPerson;

    @ApiModelProperty(value = "完成人员所在的任务")
    @TableField("COMPLETE_PERSON_TASK")
    private String completePersonTask;

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

    public static final String RESULT_NAME = "RESULT_NAME";

    public static final String RESULT_TYPE = "RESULT_TYPE";

    public static final String COOP_UNIT = "COOP_UNIT";

    public static final String COOP_PERSON = "COOP_PERSON";

    public static final String COMPLETE_PERSON_TASK = "COMPLETE_PERSON_TASK";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

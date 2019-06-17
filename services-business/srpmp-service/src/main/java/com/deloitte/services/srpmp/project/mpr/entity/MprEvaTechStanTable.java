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
 * 技术标准获批情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_TECH_STAN_TABLE")
@ApiModel(value="MprEvaTechStanTable对象", description="技术标准获批情况表")
public class MprEvaTechStanTable extends Model<MprEvaTechStanTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "获得技术标准名称")
    @TableField("OBT_TECH_STAN_NAME")
    private String obtTechStanName;

    @ApiModelProperty(value = "标准类型")
    @TableField("STAN_TYPE")
    private String stanType;

    @ApiModelProperty(value = "标准号")
    @TableField("STAN_NUM")
    private String stanNum;

    @ApiModelProperty(value = "完成人")
    @TableField("COMPLETE_PERSON")
    private String completePerson;

    @ApiModelProperty(value = "发布时间")
    @TableField("POST_DATE")
    private String postDate;

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

    public static final String OBT_TECH_STAN_NAME = "OBT_TECH_STAN_NAME";

    public static final String STAN_TYPE = "STAN_TYPE";

    public static final String STAN_NUM = "STAN_NUM";

    public static final String COMPLETE_PERSON = "COMPLETE_PERSON";

    public static final String POST_DATE = "POST_DATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

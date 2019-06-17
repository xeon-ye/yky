package com.deloitte.services.srpmp.project.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目专家列表
 * </p>
 *
 * @author pengchao
 * @since 2019-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_EXPERT")
@ApiModel(value="SrpmsProjectExpert对象", description="项目专家列表")
public class SrpmsProjectExpert extends Model<SrpmsProjectExpert> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @JsonSerialize(using = LongJsonSerializer.class)
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "员工编号")
    @TableField("EXPERT_ID")
    private String expertId;

    @ApiModelProperty(value = "员工编号")
    @TableField("EXPERT_NUM")
    private String expertNum;

    @ApiModelProperty(value = "专家名称")
    @TableField("EXPERT_NAME")
    private String expertName;

    @ApiModelProperty(value = "性别")
    @TableField("EXPERT_SEX")
    private String expertSex;

    @ApiModelProperty(value = "职称")
    @TableField("EXPERT_TITLE")
    private String expertTitle;

    @ApiModelProperty(value = "单位")
    @TableField("EXPERT_ORG")
    private String expertOrg;

    @ApiModelProperty(value = "学科")
    @TableField("EXPERT_SUBJECT")
    private String expertSubject;

    @ApiModelProperty(value = "擅长领域")
    @TableField("EXPERT_FIELD")
    private String expertField;

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

    @TableField("EXPERT_TEL")
    private String expertTel;

    @TableField("TYPE")
    private String type;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String EXPERT_ID = "EXPERT_ID";

    public static final String EXPERT_NUM = "EXPERT_NUM";

    public static final String EXPERT_NAME = "EXPERT_NAME";

    public static final String EXPERT_SEX = "EXPERT_SEX";

    public static final String EXPERT_TITLE = "EXPERT_TITLE";

    public static final String EXPERT_ORG = "EXPERT_ORG";

    public static final String EXPERT_SUBJECT = "EXPERT_SUBJECT";

    public static final String EXPERT_FIELD = "EXPERT_FIELD";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXPERT_TEL = "EXPERT_TEL";

    public static final String TYPE = "TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

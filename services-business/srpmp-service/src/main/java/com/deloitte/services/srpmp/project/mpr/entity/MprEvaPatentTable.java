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
 * 专利申请授权情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_PATENT_TABLE")
@ApiModel(value="MprEvaPatentTable对象", description="专利申请授权情况表")
public class MprEvaPatentTable extends Model<MprEvaPatentTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "专利名称")
    @TableField("PATENT_NAME")
    private String patentName;

    @ApiModelProperty(value = "申请号/授权号")
    @TableField("APPLY_AUTH_NUM")
    private String applyAuthNum;

    @ApiModelProperty(value = "申请/批准国别")
    @TableField("APPLY_APPROVAL_COUNTRY")
    private String applyApprovalCountry;

    @ApiModelProperty(value = "专利类别（发明专利、实用新型专利、外观专利）")
    @TableField("PATENT_TYPE")
    private String patentType;

    @ApiModelProperty(value = "完成人")
    @TableField("COMPLETE_PERSON")
    private String completePerson;

    @ApiModelProperty(value = "完成时间")
    @TableField("COMPLETE_DATE")
    private String completeDate;

    @ApiModelProperty(value = "状态（申请/获批）")
    @TableField("PATENT_STATUS")
    private String patentStatus;

    @ApiModelProperty(value = "是否应用")
    @TableField("IS_USE")
    private String isUse;

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

    public static final String PATENT_NAME = "PATENT_NAME";

    public static final String APPLY_AUTH_NUM = "APPLY_AUTH_NUM";

    public static final String APPLY_APPROVAL_COUNTRY = "APPLY_APPROVAL_COUNTRY";

    public static final String PATENT_TYPE = "PATENT_TYPE";

    public static final String COMPLETE_PERSON = "COMPLETE_PERSON";

    public static final String COMPLETE_DATE = "COMPLETE_DATE";

    public static final String PATENT_STATUS = "PATENT_STATUS";

    public static final String IS_USE = "IS_USE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

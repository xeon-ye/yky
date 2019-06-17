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
 * 合同管理系统审批意见表 合同管理系统审批意见表
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_APPROVAL_OPINION")
@ApiModel(value="ApprovalOpinion对象", description="合同管理系统审批意见表 合同管理系统审批意见表")
public class ApprovalOpinion extends Model<ApprovalOpinion> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "流程节点ID")
    @TableField("PROCESS_TASK_ID")
    private String processTaskId;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_ID")
    private String contractId;

    @ApiModelProperty(value = "审批意见类型")
    @TableField("OPINION_TYPE")
    private String opinionType;

    @ApiModelProperty(value = "审批意见")
    @TableField("OPINION")
    private String opinion;

    @ApiModelProperty(value = "审批人ID")
    @TableField("APPROVALER_CODE")
    private String approvalerCode;

    @ApiModelProperty(value = "审批人")
    @TableField("APPROVALER")
    private String approvaler;

    @ApiModelProperty(value = "回复内容")
    @TableField("REPLY_TEXT")
    private String replyText;

    @ApiModelProperty(value = "回复人ID")
    @TableField("REPLY_PERSON_CODE")
    private String replyPersonCode;

    @ApiModelProperty(value = "回复人")
    @TableField("REPLY_PERSON")
    private String replyPerson;

    @ApiModelProperty(value = "反馈意见ID")
    @TableField("OPINION_FILE_ID")
    private String opinionFileId;

    @ApiModelProperty(value = "反馈意见url")
    @TableField("OPINION_FILE_URL")
    private String opinionFileUrl;

    @ApiModelProperty(value = "反馈意见文件名")
    @TableField("OPINION_FILE_NAME")
    private String opinionFileName;

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


    public static final String ID = "ID";

    public static final String PROCESS_TASK_ID = "PROCESS_TASK_ID";

    public static final String CONTRACT_ID = "CONTRACT_ID";

    public static final String OPINION_TYPE = "OPINION_TYPE";

    public static final String OPINION = "OPINION";

    public static final String APPROVALER_CODE = "APPROVALER_CODE";

    public static final String APPROVALER = "APPROVALER";

    public static final String REPLY_TEXT = "REPLY_TEXT";

    public static final String REPLY_PERSON_CODE = "REPLY_PERSON_CODE";

    public static final String REPLY_PERSON = "REPLY_PERSON";

    public static final String OPINION_FILE_ID = "OPINION_FILE_ID";

    public static final String OPINION_FILE_URL = "OPINION_FILE_URL";

    public static final String OPINION_FILE_NAME = "OPINION_FILE_NAME";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

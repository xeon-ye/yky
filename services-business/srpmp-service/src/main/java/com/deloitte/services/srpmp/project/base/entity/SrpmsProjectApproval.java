package com.deloitte.services.srpmp.project.base.entity;

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
 * 项目审批意见
 * </p>
 *
 * @author lixin
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPROVAL")
@ApiModel(value="SrpmsProjectApproval对象", description="项目审批意见")
public class SrpmsProjectApproval extends Model<SrpmsProjectApproval> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "审批意见")
    @TableField("APPROVAL_COMMENTS")
    private String approvalComments;

    @ApiModelProperty(value = "审批时间")
    @TableField("APPROVAL_TIME")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "审批员工")
    @TableField("APPROVER")
    private Long approver;

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

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPROVAL_COMMENTS = "APPROVAL_COMMENTS";

    public static final String APPROVAL_TIME = "APPROVAL_TIME";

    public static final String APPROVER = "APPROVER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

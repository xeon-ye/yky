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
 * 项目合作单位信息表
 * </p>
 *
 * @author wangyanyun
 * @since 2019-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_DEPT_JOIN")
@ApiModel(value="SrpmsProjectDeptJoin对象", description="项目合作单位信息表")
public class SrpmsProjectDeptJoin extends Model<SrpmsProjectDeptJoin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "序号")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NUM")
    private String projectNum;

    @ApiModelProperty(value = "合作类型CODE")
    @TableField("JOIN_WAY")
    private String joinWay;

    @ApiModelProperty(value = "单位名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "任务负责人")
    @TableField("TASK_LEADER_NAME")
    private String taskLeaderName;

    @ApiModelProperty(value = "人数")
    @TableField("PEOPLE_COUNT")
    private String peopleCount;

    @ApiModelProperty(value = "联系邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "联系电话")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "联系邮箱或电话")
    @TableField("PHONE_OR_EMAIL")
    private String phoneOrEmail;

    @ApiModelProperty(value = "国家")
    @TableField("COUNTRY")
    private String country;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String SORT = "SORT";

    public static final String PROJECT_NUM = "PROJECT_NUM";

    public static final String JOIN_WAY = "JOIN_WAY";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String TASK_LEADER_NAME = "TASK_LEADER_NAME";

    public static final String EMAIL = "EMAIL";

    public static final String PHONE = "PHONE";

    public static final String PHONE_OR_EMAIL = "PHONE_OR_EMAIL";

    public static final String COUNTRY = "COUNTRY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

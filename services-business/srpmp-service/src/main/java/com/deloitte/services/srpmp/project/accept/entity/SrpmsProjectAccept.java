package com.deloitte.services.srpmp.project.accept.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 项目验收表
 * </p>
 *
 * @author Apeng
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_ACCEPT")
@ApiModel(value="SrpmsProjectAccept对象", description="项目验收表")
public class SrpmsProjectAccept extends Model<SrpmsProjectAccept> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "验收编号")
    @TableField("ACCEPT_NUM")
    private String acceptNum;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NUM")
    private String projectNum;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "单位ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "依托单位")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "项目开始时间")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目结束时间")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "验收状态")
    @TableField("STATUS")
    private String status;

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

    @ApiModelProperty(value = "项目标识位")
    @TableField("PROJECT_FLAG")
    private String projectFlag;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String ACCEPT_NUM = "ACCEPT_NUM";

    public static final String PROJECT_NUM = "PROJECT_NUM";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String PROJECT_FLAG = "PROJECT_FLAG";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

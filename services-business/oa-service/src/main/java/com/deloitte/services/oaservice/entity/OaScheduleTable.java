package com.deloitte.services.oaservice.entity;

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

/**
 * <p>
 * 
 * </p>
 *
 * @author fuqiao
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_SCHEDULE_TABLE")
@ApiModel(value="OaScheduleTable对象", description="")
public class OaScheduleTable extends Model<OaScheduleTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行号")
    @TableField("ROW_NUM")
    private int rowNum;

    @ApiModelProperty(value = "业务单据主键")
    @TableField("BUSINESS_ID")
    private String businessId;

    @ApiModelProperty(value = "日程类型")
    @TableField("WORK_TYPE")
    private String workType;

    @ApiModelProperty(value = "日程简述")
    @TableField("WORK_NAME")
    private String workName;

    @ApiModelProperty(value = "日程内容")
    @TableField("WORK_DESC")
    private String workDesc;

    @ApiModelProperty(value = "日程状态")
    @TableField("WORK_STATUS")
    private String workStatus;

    @ApiModelProperty(value = "是否显示")
    @TableField("SHOW_FLAG")
    private String showFlag;

    @ApiModelProperty(value = "日程对象")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "日程对象名称")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "日程对象所属部门ID")
    @TableField("DEPT_ID")
    private String deptId;

    @ApiModelProperty(value = "日程对象所属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "日程开始时间")
    @TableField("START_TIME")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "日程结束时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "日程创建人")
    @TableField("CREATE_BY")
    private String createBy;

    @ApiModelProperty(value = "日程创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "日程最后更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "日程最后更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "个人日程类型")
    @TableField(value = "PERSON_TYPE")
    private String personType;

    @ApiModelProperty(value = "提醒方式")
    @TableField(value = "NOTICE_TYPE")
    private String noticeType;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String ROW_NUM = "ROW_NUM";

    public static final String BUSINESS_ID = "BUSINESS_ID";

    public static final String WORK_TYPE = "WORK_TYPE";

    public static final String WORK_NAME = "WORK_NAME";

    public static final String WORK_DESC = "WORK_DESC";

    public static final String WORK_STATUS = "WORK_STATUS";

    public static final String SHOW_FLAG = "SHOW_FLAG";

    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String START_TIME = "START_TIME";

    public static final String END_TIME = "END_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String PERSON_TYPE = "PERSON_TYPE";

    public static final String NOTICE_TYPE = "NOTICE_TYPE";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

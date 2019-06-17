package com.deloitte.services.isump.entity;

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
 * 组织架构表
 * </p>
 *
 * @author zhangdi
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_ORGANIZATION")
@ApiModel(value="Organization对象", description="组织架构表")
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "上级ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "预留字段1")
    @TableField("RESERVE")
    private String reserve;

    @ApiModelProperty(value = "预留字段2")
    @TableField("VERSION")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "上级编码")
    @TableField("PARENT_CODE")
    private String parentCode;

    @ApiModelProperty(value = "路径")
    @TableField("PATH")
    private String path;

    @ApiModelProperty(value = "统一信用代码")
    @TableField("COMMON_CREDIT_CODE")
    private String commonCreditCode;

    @ApiModelProperty(value = "分组（ 虚拟组：fiction）")
    @TableField("GROUP_TYPE")
    private String groupType;

    @ApiModelProperty(value = "处室负责人")
    @TableField("DUTYPERSON")
    private String dutyperson;

    @ApiModelProperty(value = "分管领导")
    @TableField("LEADER")
    private String leader;

    @ApiModelProperty(value = "简称")
    @TableField("SIMPLE_NAME")
    private String simpleName;

    @ApiModelProperty(value = "层级")
    @TableField("ORG_LEVEL")
    private String orgLevel;


    public static final String ID = "ID";

    public static final String NAME = "NAME";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String TYPE = "TYPE";

    public static final String STATE = "STATE";

    public static final String SORT = "SORT";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String CODE = "CODE";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String PATH = "PATH";

    public static final String COMMON_CREDIT_CODE = "COMMON_CREDIT_CODE";

    public static final String GROUP_TYPE = "GROUP_TYPE";

    public static final String DUTYPERSON = "DUTYPERSON";

    public static final String LEADER = "LEADER";

    public static final String SIMPLE_NAME = "SIMPLE_NAME";

    public static final String ORG_LEVEL = "ORG_LEVEL";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

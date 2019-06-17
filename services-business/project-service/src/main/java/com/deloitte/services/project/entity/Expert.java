package com.deloitte.services.project.entity;

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
 * 专家列表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EXPERT")
@ApiModel(value="Expert对象", description="专家列表")
public class Expert extends Model<Expert> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "专家列表ID")
    @TableField("EXPERT_ID")
    private String expertId;

    @ApiModelProperty(value = "员工编号")
    @TableField("STAFF_NO")
    private String staffNo;

    @ApiModelProperty(value = "专家名称")
    @TableField("EXPERT_NAME")
    private String expertName;

    @ApiModelProperty(value = "性别")
    @TableField("GENDER")
    private String gender;

    @ApiModelProperty(value = "职称")
    @TableField("EXPERT_POST")
    private String expertPost;

    @ApiModelProperty(value = "单位")
    @TableField("COMPANY")
    private String company;

    @ApiModelProperty(value = "学科")
    @TableField("SUBJECT")
    private String subject;

    @ApiModelProperty(value = "擅长领域")
    @TableField("GOOD_AT_FIELD")
    private String goodAtField;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "单位id")
    @TableField("COMPANY_ID")
    private String companyId;


    public static final String ID = "ID";

    public static final String EXPERT_ID = "EXPERT_ID";

    public static final String STAFF_NO = "STAFF_NO";

    public static final String EXPERT_NAME = "EXPERT_NAME";

    public static final String GENDER = "GENDER";

    public static final String EXPERT_POST = "EXPERT_POST";

    public static final String COMPANY = "COMPANY";

    public static final String SUBJECT = "SUBJECT";

    public static final String GOOD_AT_FIELD = "GOOD_AT_FIELD";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String COMPANY_ID = "COMPANY_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

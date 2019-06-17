package com.deloitte.services.srpmp.project.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.deloitte.platform.api.srpmp.common.config.LongToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 项目附件表
 * </p>
 *
 * @author lixin
 * @since 2019-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_ATTACHMENT")
@ApiModel(value="SrpmsProjectAttachment对象", description="项目附件表")
public class SrpmsProjectAttachment extends Model<SrpmsProjectAttachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "序号")
    @TableField("SERIAL")
    private Long serial;

    @ApiModelProperty(value = "附件类型")
    @TableField("ATTACHMENT_CATEGORY")
    private String attachmentCategory;

    @ApiModelProperty(value = "文档类型")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty(value = "附件名称")
    @TableField("ATTACHMENT_NAME")
    private String attachmentName;

    @ApiModelProperty(value = "附件路径")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "附件说明")
    @TableField("ATTACHMENT_REMARK")
    private String attachmentRemark;

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

    public static final String SERIAL = "SERIAL";

    public static final String ATTACHMENT_CATEGORY = "ATTACHMENT_CATEGORY";

    public static final String FILE_TYPE = "FILE_TYPE";

    public static final String ATTACHMENT_NAME = "ATTACHMENT_NAME";

    public static final String FILE_URL = "FILE_URL";

    public static final String ATTACHMENT_REMARK = "ATTACHMENT_REMARK";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public static void main(String[] args){
        SrpmsProjectAttachment a = new SrpmsProjectAttachment();
        a.setId(122221231L);
        JSONObject relust = new JSONObject();
        relust.put("a", a);
        System.out.println(JSONObject.toJSONString(a));
        System.out.println(relust.toJSONString());
    }

}

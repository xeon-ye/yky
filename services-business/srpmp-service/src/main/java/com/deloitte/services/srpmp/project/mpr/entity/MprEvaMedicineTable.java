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
 * 获得新药、疫苗、医疗器械证书数、临床批件数情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_MEDICINE_TABLE")
@ApiModel(value="MprEvaMedicineTable对象", description="获得新药、疫苗、医疗器械证书数、临床批件数情况表")
public class MprEvaMedicineTable extends Model<MprEvaMedicineTable> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "产品名称")
    @TableField("PRODUCT_NAME")
    private String productName;

    @ApiModelProperty(value = "产品类别")
    @TableField("PRODUCT_TYPE")
    private String productType;

    @ApiModelProperty(value = "申报/注册分类")
    @TableField("REGISTRATION_TYPE")
    private String registrationType;

    @ApiModelProperty(value = "证书号")
    @TableField("CERT_NO")
    private String certNo;

    @ApiModelProperty(value = "参与人员")
    @TableField("JOIN_PERSON")
    private String joinPerson;

    @ApiModelProperty(value = "批准时间")
    @TableField("APPROVAL_DATE")
    private String approvalDate;

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

    public static final String PRODUCT_NAME = "PRODUCT_NAME";

    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";

    public static final String REGISTRATION_TYPE = "REGISTRATION_TYPE";

    public static final String CERT_NO = "CERT_NO";

    public static final String JOIN_PERSON = "JOIN_PERSON";

    public static final String APPROVAL_DATE = "APPROVAL_DATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

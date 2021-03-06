package com.deloitte.services.srpmp.outline.entity;

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
 * 题录 05专利信息表
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_PATENT")
@ApiModel(value="SrpmsOutlinePatent对象", description="题录 05专利信息表")
public class SrpmsOutlinePatent extends Model<SrpmsOutlinePatent> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "申请专利号")
    @TableField("APPLICATION_NUM")
    private String applicationNum;

    @ApiModelProperty(value = "授权专利号")
    @TableField("PATENT_NUM")
    private String patentNum;

    @ApiModelProperty(value = "专利名称")
    @TableField("PATENT_NAME")
    private String patentName;

    @ApiModelProperty(value = "专利类别")
    @TableField("PATENT_CAT")
    private String patentCat;

    @ApiModelProperty(value = "专利是否授权")
    @TableField("AUTHORIZED_FLAG")
    private String authorizedFlag;

    @ApiModelProperty(value = "申请人")
    @TableField("PROPOSER")
    private String proposer;

    @ApiModelProperty(value = "申请时间")
    @TableField("APPLICATION_TIME")
    private LocalDateTime applicationTime;

    @ApiModelProperty(value = "授权时间")
    @TableField("AUTHORIZED_TIME")
    private LocalDateTime authorizedTime;

    @ApiModelProperty(value = "区域(国内、国外)")
    @TableField("AUTHORIZED_REGION")
    private String authorizedRegion;

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

    public static final String BASE_ID = "BASE_ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String APPLICATION_NUM = "APPLICATION_NUM";

    public static final String PATENT_NUM = "PATENT_NUM";

    public static final String PATENT_NAME = "PATENT_NAME";

    public static final String PATENT_CAT = "PATENT_CAT";

    public static final String AUTHORIZED_FLAG = "AUTHORIZED_FLAG";

    public static final String PROPOSER = "PROPOSER";

    public static final String APPLICATION_TIME = "APPLICATION_TIME";

    public static final String AUTHORIZED_TIME = "AUTHORIZED_TIME";

    public static final String AUTHORIZED_REGION = "AUTHORIZED_REGION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

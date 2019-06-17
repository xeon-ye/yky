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
 * 合同标准文本 合同标准文本
 * </p>
 *
 * @author yangyq
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_STANDARD_TEMPLATE")
@ApiModel(value="StandardTemplate对象", description="合同标准文本 合同标准文本")
public class StandardTemplate extends Model<StandardTemplate> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "标准文本编号")
    @TableField("TEMPLATE_CODE")
    private String templateCode;

    @ApiModelProperty(value = "标准文本名称")
    @TableField("TEMPLATE_NAME")
    private String templateName;

    @ApiModelProperty(value = "属性编号")
    @TableField("ATTRIBUTE_CODE")
    private String attributeCode;

    @ApiModelProperty(value = "属性值")
    @TableField("ATTRIBUTE")
    private String attribute;

    @ApiModelProperty(value = "组织范围编号")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "组织范围值")
    @TableField("ORG")
    private String org;

    @ApiModelProperty(value = "合同类型编号")
    @TableField("CONTRACT_TYPE_CODE")
    private String contractTypeCode;

    @ApiModelProperty(value = "合同类型")
    @TableField("CONTRACT_TYPE")
    private String contractType;

    @ApiModelProperty(value = "审批状态")
    @TableField("STATUE")
    private String statue;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "变更时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "变更人")
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

    @ApiModelProperty(value = "文件编号")
    @TableField("ATTAMENT_ID")
    private String attamentId;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "备注说明")
    @TableField("STANDARD_REMARK")
    private String standardRemark;

    @ApiModelProperty(value = "创建人名称")
    @TableField("CREATE_BY_NAME")
    private String createByName;


    public static final String ID = "ID";

    public static final String TEMPLATE_CODE = "TEMPLATE_CODE";

    public static final String TEMPLATE_NAME = "TEMPLATE_NAME";

    public static final String ATTRIBUTE_CODE = "ATTRIBUTE_CODE";

    public static final String ATTRIBUTE = "ATTRIBUTE";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORG = "ORG";

    public static final String CONTRACT_TYPE_CODE = "CONTRACT_TYPE_CODE";

    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";

    public static final String STATUE = "STATUE";

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

    public static final String ATTAMENT_ID = "ATTAMENT_ID";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String FILE_URL = "FILE_URL";

    public static final String STANDARD_REMARK = "STANDARD_REMARK";

    public static final String CREATE_BY_NAME = "CREATE_BY_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

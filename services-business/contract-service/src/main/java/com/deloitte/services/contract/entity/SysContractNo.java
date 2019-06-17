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
 * 合同编号配置
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_CONTRACT_NO")
@ApiModel(value="SysContractNo对象", description="合同编号配置")
public class SysContractNo extends Model<SysContractNo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "公司编号")
    @TableField("COMPANY_CODE")
    private String companyCode;

    @ApiModelProperty(value = "公司名称")
    @TableField("COMPANY_NAME")
    private String companyName;

    @ApiModelProperty(value = "公司简称")
    @TableField("COMPANY_SHORT")
    private String companyShort;

    @ApiModelProperty(value = "部门编号")
    @TableField("DEPARTMENT_CODE")
    private String departmentCode;

    @ApiModelProperty(value = "部门名称")
    @TableField("DEPARTMENT_NAME")
    private String departmentName;

    @ApiModelProperty(value = "部门简称")
    @TableField("DEPARTMENT_SHORT")
    private String departmentShort;

    @ApiModelProperty(value = "流水号")
    @TableField("SERIAL_NUMBER")
    private String serialNumber;

    @ApiModelProperty(value = "年份")
    @TableField("CONTRACT_YEAR")
    private String contractYear;

    @ApiModelProperty(value = "流水号长度")
    @TableField("SERIAL_SIZE")
    private String serialSize;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
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


    public static final String ID = "ID";

    public static final String COMPANY_CODE = "COMPANY_CODE";

    public static final String COMPANY_NAME = "COMPANY_NAME";

    public static final String COMPANY_SHORT = "COMPANY_SHORT";

    public static final String DEPARTMENT_CODE = "DEPARTMENT_CODE";

    public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";

    public static final String DEPARTMENT_SHORT = "DEPARTMENT_SHORT";

    public static final String SERIAL_NUMBER = "SERIAL_NUMBER";

    public static final String CONTRACT_YEAR = "CONTRACT_YEAR";

    public static final String SERIAL_SIZE = "SERIAL_SIZE";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

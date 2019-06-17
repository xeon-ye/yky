package com.deloitte.services.contract.entity;

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
 * 合同管理系统文件信息表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_SERIAL_NUMBER")
@ApiModel(value="ContractSerialNumber对象", description="合同流水号信息表")
public class SerialNumber extends Model<SerialNumber> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private long id;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "流水号")
    @TableField("SERIAL_NUMBER")
    private String serialNumber;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField("UPDATE_BY")
    private Long updateBy;

    @ApiModelProperty(value = "类型（1：合同流水号：2：标准文本流水号）")
    @TableField("TYPE")
    private String type;


    public static final String ID = "ID";

    public static final String YEAR = "YEAR";

    public static final String SERIAL_NUMBER = "SERIAL_NUMBER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String TYPE = "TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package com.deloitte.services.fssc.engine.dockingEbs.entity;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * COA_凭证类别_币种 三种类型基础数据
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AvBaseFromEbs对象", description="COA_凭证类别_币种 三种类型基础数据")
public class AvBaseFromEbs {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String dataType;

    @ApiModelProperty(value = "数据编码")
    private String dataCode;

    @ApiModelProperty(value = "数据名称")
    private String dataDesc;

    @ApiModelProperty(value = "数据状态（N/Y）")
    private String dataStatus;

    @ApiModelProperty(value = "预留字段1")
    private String attribute1;

    @ApiModelProperty(value = "预留字段2")
    private String attribute2;

    @ApiModelProperty(value = "预留字段3")
    private String attribute3;

    @ApiModelProperty(value = "是否是父类")
    private String summaryFlag;




}

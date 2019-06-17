package com.deloitte.services.fssc.business.travle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;

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
 * @author hjy
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TAS_TRAVEL_TIME")
@ApiModel(value="TasTravelTime对象", description="")
public class TasTravelTime extends Model<TasTravelTime> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "关联行表ID")
    @TableField("TRAVEL_LINE_ID")
    private Long travelLineId;

    @ApiModelProperty(value = "差旅起始时间")
    @TableField("TRAVEL_BEGIN_TIME")
    private LocalDateTime travelBeginTime;

    @ApiModelProperty(value = "差旅结束时间")
    @TableField("TRAVEL_EDN_TIME")
    private LocalDateTime travelEdnTime;

    @ApiModelProperty(value="关联类型")
    @TableField("DOCUMENT_TYPE")
    private  String documentType;


    public static final String ID = "ID";

    public static final String TRAVEL_LINE_ID = "TRAVEL_LINE_ID";

    public static final String TRAVEL_BEGIN_TIME = "TRAVEL_BEGIN_TIME";

    public static final String TRAVEL_EDN_TIME = "TRAVEL_EDN_TIME";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}

package com.deloitte.services.fastway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 快速通道表
 * </p>
 *
 * @author yidaojun
 * @since 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("FAST_WAY")
@ApiModel(value = "FastWay对象", description = "快速通道表")
public class FastWay extends Model<FastWay> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "图片URL")
    @TableField("PIC_URL")
    private String picUrl;

    @ApiModelProperty(value = "应用名称")
    @TableField("APPLY_NAME")
    private String applyName;

    @ApiModelProperty(value = "应用排序")
    @TableField("APPLY_SORT")
    private String applySort;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    @TableField("APPLY_DATETIME")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    @TableField("APPLY_UPDATETIME")
    private LocalDateTime applyUpdatetime;

    @ApiModelProperty(value = "跳转的url")
    @TableField("APPLY_URL")
    private String applyUrl;


    public static final String ID = "ID";

    public static final String PIC_URL = "PIC_URL";

    public static final String APPLY_NAME = "APPLY_NAME";

    public static final String APPLY_SORT = "APPLY_SORT";

    public static final String APPLY_DATETIME = "APPLY_DATETIME";

    public static final String APPLY_UPDATETIME = "APPLY_UPDATETIME";

    public static final String APPLY_URL = "APPLY_URL";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

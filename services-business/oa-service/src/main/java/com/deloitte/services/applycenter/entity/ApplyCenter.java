package com.deloitte.services.applycenter.entity;

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
 * 应用中心表
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_APPLY_CENTER")
@ApiModel(value = "ApplyCenter对象", description = "应用中心表")
public class ApplyCenter extends Model<ApplyCenter> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    @TableId(value = "APPLY_ID", type = IdType.ID_WORKER)
    private Long applyId;

    @ApiModelProperty(value = "图片URL，常用流程中该字段为空")
    @TableField("PIC_URL")
    private String picUrl;

    @ApiModelProperty(value = "应用名称")
    @TableField("APPLY_NAME")
    private String applyName;

    @ApiModelProperty(value = "应用排序")
    @TableField("APPLY_SORT")
    private String applySort;

    @ApiModelProperty(value = "是否可见，1表示可见，0表示不可见")
    @TableField("IS_VISIABLE")
    private String isVisiable;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    @TableField("APPLY_DATETIME")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    @TableField("APPLY_UPDATETIME")
    private LocalDateTime applyUpdatetime;

    @ApiModelProperty(value = "类型，应用中心或常用流程")
    @TableField("TYPE_NAME")
    private String typeName;

    @ApiModelProperty(value = "删除状态，默认0，表示未删除")
    @TableField("DEL_FLAG")
    private String delFlag;

    @ApiModelProperty(value = "跳转到其他系统的url")
    @TableField("APPLY_URL")
    private String applyUrl;

    @ApiModelProperty(value = "对应applyName，字典表中查询")
    @TableField("APPLY_CODE")
    private String applyCode;

    @ApiModelProperty(value = "系统标识")
    @TableField("SYSTEM_CODE")
    private String systemCode;


    public static final String APPLY_ID = "APPLY_ID";

    public static final String PIC_URL = "PIC_URL";

    public static final String APPLY_NAME = "APPLY_NAME";

    public static final String APPLY_SORT = "APPLY_SORT";

    public static final String IS_VISIABLE = "IS_VISIABLE";

    public static final String APPLY_DATETIME = "APPLY_DATETIME";

    public static final String APPLY_UPDATETIME = "APPLY_UPDATETIME";

    public static final String TYPE_NAME = "TYPE_NAME";

    public static final String DEL_FLAG = "DEL_FLAG";

    public static final String APPLY_URL = "APPLY_URL";

    public static final String APPLY_CODE = "APPLY_CODE";

    public static final String SYSTEM_CODE = "SYSTEM_CODE";

    @Override
    protected Serializable pkVal() {
        return this.applyId;
    }

}

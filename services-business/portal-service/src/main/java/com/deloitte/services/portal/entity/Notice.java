package com.deloitte.services.portal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("NOTICE")
@ApiModel(value="Notice对象", description="通知公告表")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    @TableField("NOTICE_ID")
    private Long noticeId;

    @ApiModelProperty(value = "标题")
    @TableField("NOTICE_TITLE")
    private String noticeTitle;

    @ApiModelProperty(value = "点击次数")
    @TableField("NOTICE_HIT")
    private Long noticeHit;

    @ApiModelProperty(value = " 发布时间")
    @TableField("NOTICE_DATETIME")
    private LocalDateTime noticeDatetime;

    @ApiModelProperty(value = "更新时间")
    @TableField("NOTICE_UPDATETIME")
    private LocalDateTime noticeUpdatetime;

    @ApiModelProperty(value = "文章作者")
    @TableField("NOTICE_AUTHOR")
    private String noticeAuthor;

    @ApiModelProperty(value = "公告内容")
    @TableField("NOTICE_CONTENT")
    private String noticeContent;

    @ApiModelProperty(value = "删除状态")
    @TableField("DEL_FLG")
    private String delFlg;


    public static final String NOTICE_ID = "NOTICE_ID";

    public static final String NOTICE_TITLE = "NOTICE_TITLE";

    public static final String NOTICE_HIT = "NOTICE_HIT";

    public static final String NOTICE_DATETIME = "NOTICE_DATETIME";

    public static final String NOTICE_UPDATETIME = "NOTICE_UPDATETIME";

    public static final String NOTICE_AUTHOR = "NOTICE_AUTHOR";

    public static final String NOTICE_CONTENT = "NOTICE_CONTENT";

    public static final String DEL_FLG = "DEL_FLG";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}

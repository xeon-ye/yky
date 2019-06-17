package com.deloitte.services.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.deloitte.services.attachment.entity.OaAttachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 院校新闻表
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_NEWS")
@ApiModel(value = "News对象", description = "院校新闻表")
public class News extends Model<News> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    @TableId(value = "NEWS_ID", type = IdType.ID_WORKER)
    private Long newsId;


    @ApiModelProperty(value = "新闻类型，取字典表CODE")
    @TableField("NEWS_TYPE_CODE")
    private String newsTypeCode;

    @ApiModelProperty(value = "新闻类别名称，取字典表name字段")
    @TableField("NEWS_TYPE_NAME")
    private String newsTypeName;

    @ApiModelProperty(value = "新闻标题")
    @TableField("NEWS_TITLE")
    private String newsTitle;

    /**
     * 即首页滚动图片
     */
    @ApiModelProperty(value = "新闻缩略图")
    @TableField("NEWS_THUMBNAILS_URL")
    private String newsThumbnailsUrl;

    @ApiModelProperty(value = "是否是轮播图片，1表示是，0表示否")
    @TableField("ISPIC_NEWS")
    private String ispicNews;

    @ApiModelProperty(value = "点击次数")
    @TableField("NEWS_HIT")
    private Long newsHit;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    @TableField("NEWS_DATETIME")
    private LocalDateTime newsDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    @TableField("NEWS_UPDATETIME")
    private LocalDateTime newsUpdatetime;

    @ApiModelProperty(value = "新闻供稿人")
    @TableField("NEWS_CONTRIBUTOR")
    private String newsContributor;

    @ApiModelProperty(value = "新闻供稿人所在部门code")
    @TableField("NEWS_CONTRIBUTOR_DEPT_CODE")
    private String newsContributorDeptCode;

    @ApiModelProperty(value = "供稿人联系方式")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "新闻申请日期")
    @TableField("NEWS_APPLYTIME")
    private LocalDateTime newsApplytime;

    @ApiModelProperty(value = "新闻内容")
    @TableField("NEWS_CONTENT")
    private String newsContent;

    @ApiModelProperty(value = "新闻配图url")
    @TableField("NEWS_IMAGE_URL")
    private String newsImageUrl;

    @ApiModelProperty(value = "新闻视频url")
    @TableField("NEWS_MOVIE_URL")
    private String newsMovieUrl;

    @ApiModelProperty(value = "新闻关键字")
    @TableField("NEWS_KEYWORD")
    private String newsKeyword;

    @ApiModelProperty(value = "新闻是否置顶，1置顶，默认0")
    @TableField("NEWS_ISTOP")
    private String newsIstop;

    @ApiModelProperty(value = "新闻需要审批，1是，0否")
    @TableField("NEWS_ISAPPROVAL")
    private String newsIsapproval;

    @ApiModelProperty(value = "紧急程度，1特急，2急，3正常")
    @TableField("NEWS_URGENT_LEVEL")
    private String newsUrgentLevel;

    @ApiModelProperty(value = "供稿部门")
    @TableField("NEWS_CONTRIBUT_DEPT")
    private String newsContributDept;

    @ApiModelProperty(value = "投稿平台")
    @TableField("NEWS_CONTRIBUT_PLATFORM")
    private String newsContributPlatform;

    @ApiModelProperty(value = "投稿平台对应字典表code")
    @TableField("NEWS_CONTRIBUT_PLATFORM_CODE")
    private String newsContributPlatformCode;

    @ApiModelProperty(value = "投递部门")
    @TableField("NEWS_DELIVERY_DEPT")
    private String newsDeliveryDept;

    @ApiModelProperty(value = "投递部门对应字典表code")
    @TableField("NEWS_DELIVERY_DEPT_CODE")
    private String newsDeliveryDeptCode;

    @ApiModelProperty(value = "业务负责人")
    @TableField("NEWS_BUSINESS_LEADER")
    private String newsBusinessLeader;

    @ApiModelProperty(value = "附件列表")
    @TableField(exist = false)
    private List<OaAttachment> attachments;

    @ApiModelProperty(value = "1:草稿，2:待审阅，3:正常")
    @TableField("NEWS_STATUS")
    private String newsStatus;

    @ApiModelProperty(value = "删除状态，默认0，表示未删除")
    @TableField("DEL_FLAG")
    private String delFlag;

    @ApiModelProperty(value = "新闻编辑人")
    @TableField("NEWS_EDITOR")
    private String newsEditor;

    @ApiModelProperty(value = "新闻发布通过时间")
    @TableField("NEWS_PUBLICTIME")
    private LocalDateTime newsPublictime;

    @ApiModelProperty(value = "作者")
    @TableField("AUTHOR")
    private String author;

    @ApiModelProperty(value = "申请部门code")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "申请人id")
    @TableField("APPLY_USER_ID")
    private String applyUserId;

    @ApiModelProperty(value = "流程编号")
    @TableField("ORDER_NUM")
    private String orderNum;

    public static final String NEWS_ID = "NEWS_ID";

    public static final String NEWS_TYPE_CODE = "NEWS_TYPE_CODE";

    public static final String NEWS_TYPE_NAME = "NEWS_TYPE_NAME";

    public static final String NEWS_TITLE = "NEWS_TITLE";

    public static final String NEWS_THUMBNAILS_URL = "NEWS_THUMBNAILS_URL";

    public static final String ISPIC_NEWS = "ISPIC_NEWS";

    public static final String NEWS_HIT = "NEWS_HIT";

    public static final String NEWS_DATETIME = "NEWS_DATETIME";

    public static final String NEWS_UPDATETIME = "NEWS_UPDATETIME";

    public static final String NEWS_CONTRIBUTOR = "NEWS_CONTRIBUTOR";

    public static final String NEWS_CONTRIBUTOR_DEPT_CODE = "NEWS_CONTRIBUTOR_DEPT_CODE";

    public static final String PHONE = "PHONE";

    public static final String NEWS_APPLYTIME = "NEWS_APPLYTIME";

    public static final String NEWS_CONTENT = "NEWS_CONTENT";

    public static final String NEWS_IMAGE_URL = "NEWS_IMAGE_URL";

    public static final String NEWS_MOVIE_URL = "NEWS_MOVIE_URL";

    public static final String NEWS_KEYWORD = "NEWS_KEYWORD";

    public static final String NEWS_ISTOP = "NEWS_ISTOP";

    public static final String NEWS_ISAPPROVAL = "NEWS_ISAPPROVAL";

    public static final String NEWS_URGENT_LEVEL = "NEWS_URGENT_LEVEL";

    public static final String NEWS_CONTRIBUT_DEPT = "NEWS_CONTRIBUT_DEPT";

    public static final String NEWS_BUSINESS_LEADER = "NEWS_BUSINESS_LEADER";

    public static final String NEWS_CONTRIBUT_PLATFORM = "NEWS_CONTRIBUT_PLATFORM";

    public static final String NEWS_CONTRIBUT_PLATFORM_CODE = "NEWS_CONTRIBUT_PLATFORM_CODE";

    public static final String DEL_FLAG = "DEL_FLAG";

    public static final String NEWS_STATUS = "NEWS_STATUS";

    public static final String NEWS_EDITOR = "NEWS_EDITOR";

    public static final String NEWS_DELIVERY_DEPT = "NEWS_DELIVERY_DEPT";

    public static final String NEWS_DELIVERY_DEPT_CODE = "NEWS_DELIVERY_DEPT_CODE";

    public static final String NEWS_PUBLICTIME = "NEWS_PUBLICTIME";

    public static final String AUTHOR = "AUTHOR";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORDER_NUM = "ORDER_NUM";

    public static final String APPLY_USER_ID = "APPLY_USER_ID";

    @Override
    protected Serializable pkVal() {
        return this.newsId;
    }

}

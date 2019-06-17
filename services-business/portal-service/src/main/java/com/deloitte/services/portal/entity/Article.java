package com.deloitte.services.portal.entity;

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
 * 文章表
 * </p>
 *
 * @author yidaojun
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ARTICLE")
@ApiModel(value="Article对象", description="文章表")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    @TableId(value = "ARTICLE_ID", type = IdType.ID_WORKER)
    private Long articleId;

    @ApiModelProperty(value = "文章栏目ID")
    @TableField("ARTICLE_CATEGORY_ID")
    private Long articleCategoryId;

    @ApiModelProperty(value = "标题")
    @TableField("ARTICLE_TITLE")
    private String articleTitle;

    @ApiModelProperty(value = "缩略图")
    @TableField("ARTICLE_THUMBNAILS")
    private String articleThumbnails;

    @ApiModelProperty(value = "点击次数")
    @TableField("ARTICLE_HIT")
    private Long articleHit;

    @ApiModelProperty(value = " 发布时间")
    @TableField("ARTICLE_DATETIME")
    private LocalDateTime articleDatetime;

    @ApiModelProperty(value = "更新时间")
    @TableField("ARTICLE_UPDATETIME")
    private LocalDateTime articleUpdatetime;

    @ApiModelProperty(value = "文章作者")
    @TableField("ARTICLE_AUTHOR")
    private String articleAuthor;

    @ApiModelProperty(value = "文章内容")
    @TableField("ARTICLE_CONTENT")
    private String articleContent;

    @ApiModelProperty(value = "文章跳转链接地址")
    @TableField("ARTICLE_URL")
    private String articleUrl;

    @ApiModelProperty(value = "文章视频链接地址")
    @TableField("ARTICLE_MOVIE_URL")
    private String articleMovieUrl;

    @ApiModelProperty(value = "文章关键字")
    @TableField("ARTICLE_KEYWORD")
    private String articleKeyword;

    @ApiModelProperty(value = "文章关键字")
    @TableField("ISTOP")
    private String isTop;

    @ApiModelProperty(value = "删除状态")
    @TableField("DEL_FLG")
    private String delFlg;


    public static final String ARTICLE_ID = "ARTICLE_ID";

    public static final String ARTICLE_CATEGORY_ID = "ARTICLE_CATEGORY_ID";

    public static final String ARTICLE_TITLE = "ARTICLE_TITLE";

    public static final String ARTICLE_THUMBNAILS = "ARTICLE_THUMBNAILS";

    public static final String ARTICLE_HIT = "ARTICLE_HIT";

    public static final String ARTICLE_DATETIME = "ARTICLE_DATETIME";

    public static final String ARTICLE_UPDATETIME = "ARTICLE_UPDATETIME";

    public static final String ARTICLE_AUTHOR = "ARTICLE_AUTHOR";

    public static final String ARTICLE_CONTENT = "ARTICLE_CONTENT";

    public static final String ARTICLE_URL = "ARTICLE_URL";

    public static final String ARTICLE_MOVIE_URL = "ARTICLE_MOVIE_URL";

    public static final String ARTICLE_KEYWORD = "ARTICLE_KEYWORD";

    public static final String ISTOP = "ISTOP";

    public static final String DEL_FLG = "DEL_FLG";

    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

}

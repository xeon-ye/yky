package com.deloitte.platform.api.portal.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :   Article查询from对象
 * @Modified :
 */
@ApiModel("Article查询表单")
@Data
public class ArticleQueryForm extends BaseQueryForm<ArticleQueryParam>  {


    @ApiModelProperty(value = "自增长id")
    private Long articleId;

    @ApiModelProperty(value = "文章栏目ID")
    private Long articleCategoryId;

    @ApiModelProperty(value = "标题")
    private String articleTitle;

    @ApiModelProperty(value = "缩略图")
    private String articleThumbnails;

    @ApiModelProperty(value = "点击次数")
    private Long articleHit;

    @ApiModelProperty(value = " 发布时间")
    private LocalDateTime articleDatetime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime articleUpdatetime;

    @ApiModelProperty(value = "文章作者")
    private String articleAuthor;

    @ApiModelProperty(value = "文章内容")
    private String articleContent;

    @ApiModelProperty(value = "文章跳转链接地址")
    private String articleUrl;

    @ApiModelProperty(value = "文章视频链接地址")
    private String articleMovieUrl;

    @ApiModelProperty(value = "文章关键字")
    private String articleKeyword;

    @ApiModelProperty(value = "删除状态")
    private String delFlg;
}

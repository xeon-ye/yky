package com.deloitte.platform.api.portal.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :  Article查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long articleId;
    private Long articleCategoryId;
    private String articleTitle;
    private String articleThumbnails;
    private Long articleHit;
    private LocalDateTime articleDatetime;
    private LocalDateTime articleUpdatetime;
    private String articleAuthor;
    private String articleContent;
    private String articleUrl;
    private String articleMovieUrl;
    private String articleKeyword;
    private String delFlg;

}

package com.deloitte.platform.api.oaservice.news.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  News查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long newsId;
    private String newsTypeCode;
    private String newsTypeName;
    private String newsTitle;
    private String newsThumbnailsUrl;
    private String ispicNews;
    private Long newsHit;
    private LocalDateTime newsDatetime;
    private LocalDateTime newsUpdatetime;
    private LocalDateTime newsApplytime;
    private String newsContributor;
    private String phone;
    private String newsContent;
    private String newsImageUrl;
    private String newsMovieUrl;
    private String newsKeyword;
    private String newsIsapproval;
    private String newsIstop;
    private String newsUrgentLevel;
    private String newsContributDept;
    private String newsContributPlatform;
    private String newsContributPlatformCode;
    private String newsBusinessLeader;
    private String delFlag;
    private String newsStatus;
    private String newsEditor;
    private String newsDeliveryDept;
    private String newsDeliveryDeptCode;
    private LocalDateTime newsPublictime;
    private String newsContributorDeptCode;
    private String author;
    private String orgCode;
    private String applyUserId;
    private String orderNum;

}

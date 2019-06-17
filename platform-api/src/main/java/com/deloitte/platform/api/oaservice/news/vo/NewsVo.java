package com.deloitte.platform.api.oaservice.news.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description : News返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长id")
    private String newsId;

    @ApiModelProperty(value = "新闻类型，取字典表code")
    private String newsTypeCode;

    @ApiModelProperty(value = "新闻类别名称，取字典表name字段")
    private String newsTypeName;

    @ApiModelProperty(value = "新闻标题")
    private String newsTitle;

    /**
     * 首页轮播图
     */
    @ApiModelProperty(value = "新闻缩略图")
    private String newsThumbnailsUrl;

    @ApiModelProperty(value = "是否是轮播图片，1表示是，0表示否")
    private String ispicNews;

    @ApiModelProperty(value = "点击次数")
    private Long newsHit;

    @ApiModelProperty(value = "发布时间，默认系统时间")
    private LocalDateTime newsDatetime;

    @ApiModelProperty(value = "更新时间，默认系统时间")
    private LocalDateTime newsUpdatetime;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime newsApplytime;

    @ApiModelProperty(value = "新闻供稿人")
    private String newsContributor;

    @ApiModelProperty(value = "新闻供稿人所在部门code")
    private String newsContributorDeptCode;

    @ApiModelProperty(value = "供稿人电话")
    private String phone;

    @ApiModelProperty(value = "新闻内容")
    private String newsContent;

    @ApiModelProperty(value = "新闻配图url")
    private String newsImageUrl;

    @ApiModelProperty(value = "新闻视频url")
    private String newsMovieUrl;

    @ApiModelProperty(value = "新闻关键字")
    private String newsKeyword;

    @ApiModelProperty(value = "新闻是否置顶，1置顶，默认0")
    private String newsIstop;

    @ApiModelProperty(value = "是否需业务负责人审批，1是，0否")
    private String newsIsapproval;

    @ApiModelProperty(value = "紧急程度，1特急，2急，3正常")
    private String newsUrgentLevel;

    @ApiModelProperty(value = "供稿部门")
    private String newsContributDept;

    @ApiModelProperty(value = "投稿平台")
    private String newsContributPlatform;

    @ApiModelProperty(value = "投稿平台对应字典表code")
    private String newsContributPlatformCode;

    @ApiModelProperty(value = "投递部门")
    private String newsDeliveryDept;

    @ApiModelProperty(value = "投递部门对应字典表code")
    private String newsDeliveryDeptCode;

    @ApiModelProperty(value = "业务负责人")
    private String newsBusinessLeader;

    @ApiModelProperty(value = "附件列表")
    @TableField(exist = false)
    private List<OaAttachmentVo> attachments;

    @ApiModelProperty(value = "删除状态，默认0，表示未删除")
    private String delFlag;

    @ApiModelProperty(value = "1:草稿，2:待审阅，3:正常")
    private String newsStatus;

    @ApiModelProperty(value = "新闻编辑人")
    private String newsEditor;

    @ApiModelProperty(value = "新闻发布通过时间")
    private LocalDateTime newsPublictime;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "申请人id")
    private String applyUserId;

    @ApiModelProperty(value = "流程编号")
    private String orderNum;

}

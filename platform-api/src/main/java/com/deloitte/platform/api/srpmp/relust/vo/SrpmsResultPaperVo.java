package com.deloitte.platform.api.srpmp.relust.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : Apeng
 * @Date : Create in 2019-05-14
 * @Description : SrpmsResultPaper返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultPaperVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "通讯作者")
    private String correspondenceAuthor;

    @ApiModelProperty(value = "第一作者")
    private String firstAuthor;

    @ApiModelProperty(value = "其他作者")
    private String otherAuthor;

    @ApiModelProperty(value = "类别")
    private String paperCat;

    @ApiModelProperty(value = "论文题目")
    private String paperTitle;

    @ApiModelProperty(value = "期刊名称")
    private String journalTitle;

    @ApiModelProperty(value = "发表单位")
    private String publicationOrg;

    @ApiModelProperty(value = "卷")
    private String paperVolume;

    @ApiModelProperty(value = "期")
    private String stage;

    @ApiModelProperty(value = "页码")
    private String page;

    @ApiModelProperty(value = "影响因子")
    private Double influenceFactor;

    @ApiModelProperty(value = "期刊性质 10-全国 20-省内")
    private String property;

    @ApiModelProperty(value = "期刊区域 10-国内 20-国外")
    private String region;

    @ApiModelProperty(value = "等级")
    private String paperLevel;

    @ApiModelProperty(value = "项目负责人")
    private String hproInCharge;

    @ApiModelProperty(value = "发表时间")
    private LocalDateTime publishDate;

}

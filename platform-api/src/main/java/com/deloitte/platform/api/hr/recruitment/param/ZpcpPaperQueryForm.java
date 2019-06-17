package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :   ZpcpPaper查询from对象
 * @Modified :
 */
@ApiModel("ZpcpPaper查询表单")
@Data
public class ZpcpPaperQueryForm extends BaseQueryForm<ZpcpPaperQueryParam>  {


    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "查询类型(准聘长聘申报人查询.1,审核人查看详情.0)")
    private String selectType;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "是否为代表性论文（0.不是，1.是）")
    private String isRepresentative;

   /* @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publishTime;

    @ApiModelProperty(value = "论文题目")
    private String paperTitle;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "是否为通讯作者（0.不是，1.是）")
    private String isCorrespondingAuthor;

    @ApiModelProperty(value = "期刊影响因子")
    private String impactFactor;

    @ApiModelProperty(value = "发表刊物")
    private String publication;



    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;*/
}

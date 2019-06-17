package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description : PolicyNotice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyNoticeVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "政策名称")
    private String noticeName;

    @ApiModelProperty(value = "政策内容")
    private String noticeIndex;

    @ApiModelProperty(value = "政策类型")
    private String noticeType;

    @ApiModelProperty(value = "1 仅机关 2含二级学院")
    private String depScope;

    @ApiModelProperty(value = "1 仅部门负责人 2含教职工")
    private String posScope;

    @ApiModelProperty(value = "删除标识")
    private String isdelete;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "提交状态 1提交 2未提交")
    private String state;

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    @ApiModelProperty(value = "附件存储地址")
    private String fileUrl;

    @ApiModelProperty(value = "发布月份")
    private String month;

    @ApiModelProperty(value = "发布年份")
    private String year;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

}

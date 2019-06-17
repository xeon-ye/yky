package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description : HrZpcpEmployNotice新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpEmployNotice表单")
@Data
public class HrZpcpEmployNoticeForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @NotBlank
    @ApiModelProperty(value = "发布单位")
    private String unit;

    @NotNull
    @ApiModelProperty(value = "通知岗位编号数组")
    private List<ZpcpNoticeStationsForm> StationsIds;

    @NotBlank
    @Length(max = 300)
    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @Length(max = 1300)
    @NotBlank
    @ApiModelProperty(value = "通知内容")
    private String noticeContent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publicDate;

    @ApiModelProperty(value = "是否立即发布")
    private String isPublic;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "文件对象数组")
    private List<HrAttachmentForm> attachmentForms;

    @NotBlank
    @ApiModelProperty(value = "批次")
    private String batch;

    @NotBlank
    @ApiModelProperty(value = "年份")
    private String year;

}

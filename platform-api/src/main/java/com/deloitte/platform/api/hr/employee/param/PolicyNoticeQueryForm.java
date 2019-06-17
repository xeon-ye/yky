package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-04
 * @Description :   PolicyNotice查询from对象
 * @Modified :
 */
@ApiModel("PolicyNotice查询表单")
@Data
public class PolicyNoticeQueryForm extends BaseQueryForm<PolicyNoticeQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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

    @ApiModelProperty(value = "发布年份")
    private String year;

    @ApiModelProperty(value = "发布月份")
    private String month;
}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorCheckRecord查询from对象
 * @Modified :
 */
@ApiModel("PostdoctorCheckRecord查询表单")
@Data
public class PostdoctorCheckRecordQueryForm extends BaseQueryForm<PostdoctorCheckRecordQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "博士后信息ID（关联hr_postdoctor表）")
    private Long postdoctorId;

    @ApiModelProperty(value = "考核类型（1入站考核，2中期考核，3出战考核）        维护字典表")
    private Integer checkType;

    @ApiModelProperty(value = "当前状态（生成记录时复制博士后‘在站’情况的状态）")
    private String status;

    @ApiModelProperty(value = "拟出站时间")
    private LocalDateTime expectPushTime;

    @ApiModelProperty(value = "考核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "考核结果")
    private String checkResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "最后更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :   TeacherTrainGain查询from对象
 * @Modified :
 */
@ApiModel("TeacherTrainGain查询表单")
@Data
public class TeacherTrainGainQueryForm extends BaseQueryForm<TeacherTrainGainQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "教师基本信息表id")
    private Long hrTeacherTrainId;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "获得日期")
    private LocalDateTime getDate;

    @ApiModelProperty(value = "办证机构")
    private String certOrg;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}

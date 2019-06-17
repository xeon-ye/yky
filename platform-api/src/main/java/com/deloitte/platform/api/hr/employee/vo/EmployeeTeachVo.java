package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description : EmployeeTeach返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTeachVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "员工基础信息表ID")
    private Long empId;

    @ApiModelProperty(value = "入学时间")
    private LocalDateTime entranceTime;

    @ApiModelProperty(value = "毕业时间")
    private LocalDateTime graduationTime;

    @ApiModelProperty(value = "毕业学校")
    private String graduateSchool;

    @ApiModelProperty(value = "专业名称")
    private String majorName;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "学位")
    private String academicDegree;

    @ApiModelProperty(value = "详细学位")
    private String detailedDegree;

    @ApiModelProperty(value = "学习形式")
    private String learningForm;

    @ApiModelProperty(value = "学制")
    private String educSystem;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentVo> attachments;

    private String applyState;


}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : FlowStation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorApplyChargeTeacherVo extends PostdoctorApplyBaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新导师ID")
    private String newTeacherId;

    @ApiModelProperty(value = "新导师姓名")
    private String newTeacherName;

    @ApiModelProperty(value = "更换导师理由")
    private String replaceRemark;

    @ApiModelProperty(value = "更换导师附件URL  ")
    private String attachmentReplaceUrl;


}

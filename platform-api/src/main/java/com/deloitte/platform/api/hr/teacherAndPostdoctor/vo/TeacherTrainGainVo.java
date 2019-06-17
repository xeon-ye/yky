package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description : TeacherTrainGain返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTrainGainVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "教师基本信息表id")
    private Long teacherTrainId;

    @ApiModelProperty(value = "证书名称")
    private String certName;

    @ApiModelProperty(value = "获得日期")
    private LocalDate getDate;

    @ApiModelProperty(value = "办证机构")
    private String certOrg;

    @ApiModelProperty(value = "备注")
    private String remark;


}

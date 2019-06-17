package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**

 * @Author : jetvae
 * @Date : Create in 2019-04-22
 * @Description :  TeacherTrainGain查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTrainGainQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long hrTeacherTrainId;
    private String certName;
    private LocalDate getDate;
    private String certOrg;
    private String remark;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}

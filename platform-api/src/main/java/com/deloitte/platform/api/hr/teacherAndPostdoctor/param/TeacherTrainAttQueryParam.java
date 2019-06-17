package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jetvae
 * @Date : Create in 2019-04-20
 * @Description :  TeacherTrainAtt查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTrainAttQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long hrTeacherTrainId;
    private LocalDateTime attendanceDate;
    private Integer dateMorning;
    private Integer dateAfternoon;
    private Long attendanceHour;
    private String remark;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :  Teacher查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String teacherCode;
    private String name;
    private String attachUnit;
    private String attachUnitDepartment;
    private String sex;
    private String graduationSchool;
    private String maxEducation;
    private Integer status;
    private String maxDegree;
    private String currentProfession;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;
    private String orgCode;
    private String idCode;
    private String nation;
    private String workPosition;

}

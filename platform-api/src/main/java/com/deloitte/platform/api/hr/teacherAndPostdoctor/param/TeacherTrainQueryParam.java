package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jetvae
 * @Date : Create in 2019-04-19
 * @Description :  TeacherTrain查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTrainQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private String period;
    private String classSerial;
    private String sno;
    private String teacherCode;
    private String name;
    private String nation;
    private String attachUnit;
    private Integer notRepair;
    private Integer payCost;
    private String sex;
    private LocalDateTime birthDate;
    private String idCode;
    private String telPhone;
    private String maxEducation;
    private String gainTitle;
    private String majorStudied;
    private Integer isQualified;
    private Integer getQualifiy;
    private Integer status;
    private String remark;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}

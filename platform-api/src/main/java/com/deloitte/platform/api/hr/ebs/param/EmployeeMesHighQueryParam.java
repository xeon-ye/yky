package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesHigh查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesHighQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String userName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String applyState;
    private String empMesId;
    private String mesTid;
    private String includeProject;
    private String brainGain;
    private String subject1;
    private String subject2;
    private String subject3;
    private String researchDir;
    private String year;
    private String batch;
    private String situation;
    private LocalDateTime comeTime;
    private LocalDateTime transferTime;
    private LocalDateTime changeTime;
    private String remarks;
    private String innovationName;
    private String innovationArea;
    private String subjectName;
    private String directionTeam;
    private String beforeUnit;
    private String beforeUnitEs;
    private String beforePostion;
    private String beforePostionEs;
    private String employDepart;
    private String employPostion;
    private String fundingCategory;
    private String fundingYear;
    private String fundingAmout;

}

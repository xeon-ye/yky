package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description :  SrpmsProject查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectName;
    private String projectNum;
    private String aplNum;
    private String budNum;
    private String tasNum;
    private String pudNum;
    private String apdNum;
    private String applyYear;
    private String projectCategory;
    private String projectType;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private Long leadPersonId;
    private Long bothTopExpertPersonId;
    private String leadPerson;
    private String bothTopExpertPerson;
    private Double leadDeptId;
    private String leadDept;
    private String projectRole;
    private String subjectCategory;
    private String status;
    private LocalDateTime publicTime;
    private LocalDateTime approveTime;
    private String delFlg;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long applyBookFileId;
    private String applyBookFileUrl;
    private String applyBookFileName;
    private Long budgetBookFileId;
    private String budgetBookFileUrl;
    private String budgetBookFileName;
    private String budFirstOpenFlg;
    private String taskFirstOpenFlg;
    private String taskBudFirstOpenFlg;
    private Long taskBookFileId;
    private String taskBookFileName;
    private String taskBookFileUrl;
    private Long publishBookFileId;
    private String publishBookFileName;
    private String publishBookFileUrl;
    private Long approveBookFileId;
    private String approveBookFileName;
    private String approveBookFileUrl;
    private Long budgetTaskBookFileId;
    private String budgetTaskBookFileUrl;
    private String budgetTaskBookFileName;

}

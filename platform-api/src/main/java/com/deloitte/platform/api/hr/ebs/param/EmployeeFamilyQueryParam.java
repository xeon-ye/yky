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
 * @Description :  EmployeeFamily查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFamilyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userCode;
    private String name;
    private String sex;
    private String relationship;
    private LocalDateTime birthDate;
    private String company;
    private String duties;
    private String education;
    private String politicalOutlook;
    private String contactPhone;
    private LocalDateTime createTime;
    private Long careteBy;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String isResidence;
    private String remark;

}

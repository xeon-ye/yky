package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesContract查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesContractQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String conNo;
    private LocalDateTime conStartDate;
    private LocalDateTime conEndDate;
    private String conType;
    private String empType;
    private String conPer;
    private String status;
    private String conSub;
    private String souFund;
    private Double projectFound;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long careteBy;
    private Long updateBy;
    private String orgCode;
    private String userCode;

}

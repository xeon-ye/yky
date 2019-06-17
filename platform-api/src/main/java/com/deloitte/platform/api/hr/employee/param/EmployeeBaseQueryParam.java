package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :  EmployeeBase查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBaseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String photourl;
    private String empCode;
    private String name;
    private String gender;
    private String depCode;
    private String positionCode;
    private String ebsCode;
    private String idNumber;
    private String identity;
    private String isonduty;
    private LocalDateTime birth;
    private Integer age;
    private String natives;
    private String birthAddress;
    private String nationality;
    private String nation;
    private String ismarriage;
    private String companyEmail;
    private String personalEmail;
    private String companyPhone;
    private String mobilePhone;
    private LocalDateTime workingTime;
    private LocalDateTime loginTime;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String positionLevel;
    private String company;
    private String birthYear;
    private Long accountId;
    private String expertsTitles;
    private String bankNumber;
    private String openBank;
    private String unionpay;
    private String status;
    private String chiefRank;
    private String healthCondition;
}

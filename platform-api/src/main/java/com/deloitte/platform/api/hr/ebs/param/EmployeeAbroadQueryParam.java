package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeAbroad查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAbroadQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime abrTime;
    private LocalDateTime retTime;
    private String stayDur;
    private String depArea;
    private String perTas;
    private String appNo;
    private String souFund;
    private String groUnit;
    private String appUnit;
    private LocalDateTime appTime;
    private String pasNum;
    private String pasValPer;
    private String userCode;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;
    private String orgCode;

}

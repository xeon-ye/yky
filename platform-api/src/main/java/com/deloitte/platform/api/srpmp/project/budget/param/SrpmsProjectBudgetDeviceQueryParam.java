package com.deloitte.platform.api.srpmp.project.budget.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description :  SrpmsProjectBudgetDevice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetDeviceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private Long serial;
    private String joinWay;
    private String deviceName;
    private String deviceCat;
    private String deviceType;
    private String deviceNo;
    private String deviceNum;
    private String producer;
    private String techQuota;
    private String useage;
    private String content;
    private String deptName;
    private String measurementUnit;
    private Double unitPrice;
    private Double deviceCount;
    private Double amount;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}

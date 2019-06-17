package com.deloitte.platform.api.hr.vacation.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :  VacationTrain查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationTrainQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String leaveType;
    private LocalDateTime leaveStartDate;
    private LocalDateTime leaveEndDate;
    private String iscancel;
    private String approvalState;
    private String teachNum;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String processNum;
    private String employeeId;
    private Long days;
    private String fileName;
    private String fileUrl;
    private String chiefRank;
}

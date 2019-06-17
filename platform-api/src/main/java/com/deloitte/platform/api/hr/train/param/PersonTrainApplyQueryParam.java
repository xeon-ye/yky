package com.deloitte.platform.api.hr.train.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :  PersonTrainApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonTrainApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String trainProject;
    private String trainType;
    private LocalDateTime trainStartDate;
    private LocalDateTime trainEndDate;
    private String hostUnit;
    private String hostUnitLevel;
    private String trainMode;
    private String remak;
    private String state;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String orgCode;
    private String empId;
}

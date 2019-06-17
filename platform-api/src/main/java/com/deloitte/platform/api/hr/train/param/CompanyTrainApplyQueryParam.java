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
 * @Description :  CompanyTrainApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyTrainApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String trainClassName;
    private String trainContent;
    private String trainObject;
    private String stage;
    private String numbers;
    private String address;
    private String days;
    private String sponDep;
    private String assistDep;
    private Long funds;
    private String channel;
    private String isopen;
    private String remake;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String orgCoode;
    private String trainDeptname;
    private String trainType;
    private String empId;

}

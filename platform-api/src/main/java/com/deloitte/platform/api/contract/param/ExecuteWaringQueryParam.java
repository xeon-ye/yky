package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description :  ExecuteWaring查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteWaringQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String deptCode;
    private String deptName;
    private String orgCode;
    private String orgName;
    private Double waringTime;
    private Double waringFrequency;
    private String remark;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}

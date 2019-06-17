package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description :  BaseContentCommitmentUnit查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseContentCommitmentUnitQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long contentCommitmentId;
    private String orgUnitName;
    private Long orgUnitId;
    private String validFlag;
    private String orgUnitCode;
    private LocalDateTime createTime;
    private String createBy;
    private String updateBy;
    private LocalDateTime updateTime;

}

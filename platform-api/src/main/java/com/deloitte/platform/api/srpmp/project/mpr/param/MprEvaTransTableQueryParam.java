package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTransTable查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprEvaTransTableQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String outcomeName;
    private String techTrans;
    private String unionDev;
    private String techService;
    private String contractSignYear;
    private String contractAmount;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}

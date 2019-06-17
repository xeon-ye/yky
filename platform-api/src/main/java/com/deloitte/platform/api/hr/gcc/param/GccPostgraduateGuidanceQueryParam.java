package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccPostgraduateGuidance查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccPostgraduateGuidanceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String userName;
    private Long doctoralGraduatesNum;
    private Long doctoralCandidates;
    private Long graduateStudentsNum;
    private Long degreeCandidates;
    private Long enclosure;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String auditStatus;

}

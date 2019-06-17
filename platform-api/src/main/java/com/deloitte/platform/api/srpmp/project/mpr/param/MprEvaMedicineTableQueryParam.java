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
 * @Description :  MprEvaMedicineTable查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprEvaMedicineTableQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String productName;
    private String productType;
    private String registrationType;
    private String certNo;
    private String joinPerson;
    private String approvalDate;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}

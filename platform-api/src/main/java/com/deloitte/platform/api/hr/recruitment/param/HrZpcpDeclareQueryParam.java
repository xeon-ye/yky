package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpDeclare查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrZpcpDeclareQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String year;
    private String batch;
    private String name;
    private String firstClassDiscipline1;
    private String firstClassDiscipline2;
    private String declarationPost1;
    private String declarationPost2;
    private String declarationUnit;
    private LocalDateTime declarationTime;
    private String declarationDateil;
    private String status;
    private String remarks;
    private String declarationType;
    private Long enclosure;
    private String organizationCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;

}

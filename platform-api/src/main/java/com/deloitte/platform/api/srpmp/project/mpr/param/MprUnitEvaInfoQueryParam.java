package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-04-10
 * @Description :  MprUnitEvaInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MprUnitEvaInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectNo;
    private String bearerUnit;
    private String bearerUnitManager;
    private String reportDate;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String processStatus;
    private String orgManaInfo;
    private String serviceSupInfo;
    private String monitorRespInfo;
    private String resultManaInfo;
    private String implEffect;
    private String implAdvice;
    private String deptCode;
    private String deptName;
    private String year;
    private String fileInfo;
    private String auditFileInfo;

}
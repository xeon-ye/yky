package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeArchives查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeArchivesQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userCode;
    private String fileNo;
    private LocalDateTime traDate;
    private String oriUnit;
    private String oriUnitMan;
    private String traRec;
    private LocalDateTime finTime;
    private String finMan;
    private LocalDateTime traOutDate;
    private String recUnit;
    private String oriOutMan;
    private String oriOutRec;
    private String stoLoc;
    private String remark;

}

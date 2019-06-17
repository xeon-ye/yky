package com.deloitte.platform.api.hr.ebs.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesPunish查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMesPunishQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String userName;
    private String headPhoto;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String applyState;
    private String empMesId;
    private String mesTid;
    private String punishCategory;
    private String punishName;
    private LocalDateTime punishDate;
    private String punishUnit;
    private LocalDateTime unpunishDate;
    private String supervisory;
    private LocalDateTime appealDate;
    private String appealContent;
    private String appealResult;
    private String appealUnit;

}

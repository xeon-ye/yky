package com.deloitte.platform.api.hr.retire.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-04-12
 * @Description :  RehireAgaininfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RehireAgaininfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String empCode;
    private String empName;
    private String empSex;
    private Integer empAge;
    private String empDep;
    private String empOriginal;
    private String empTechnology;
    private String rehireDate;
    private String returnDep;
    private String returnPosition;
    private LocalDateTime returnStartdate;
    private LocalDateTime returnEnddate;
    private LocalDateTime createTime;
    private String careteBy;
    private String updateBy;
    private LocalDateTime updateTime;
    private String againId;
}

package com.deloitte.platform.api.hr.employ.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : ZhongJiang
 * @Date : Create in 2019-05-10
 * @Description :  Outrecruit查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutrecruitQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String postStation;
    private String orgNum;
    private String nowNum;
    private String recruitNum;
    private String salaryFormal;
    private String salaryTry;
    private String recruitSource;
    private LocalDateTime tryStartdate;
    private LocalDateTime tryEnddate;
    private LocalDateTime recruitStartdate;
    private LocalDateTime recruitStartenddate;
    private String applyReason;
    private String applyDes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String careteBy;
    private String updateBy;
    private String empId;

}

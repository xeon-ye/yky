package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpEmployerInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZpcpEmployerInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String name;
    private String unit;
    private String post;
    private String employmentStatus;
    private LocalDateTime employmentStarttime;
    private LocalDateTime employmentEndtime;
    private String salarySystem;
    private Long totalSalay;
    private Long employCode;
    private String employName;
    private String remarks;
    private String organizationCode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createBy;
    private Long updateBy;

}

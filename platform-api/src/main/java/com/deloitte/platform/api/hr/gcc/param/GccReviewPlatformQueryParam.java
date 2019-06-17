package com.deloitte.platform.api.hr.gcc.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :  GccReviewPlatform查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccReviewPlatformQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long reviewId;
    private String reviewName;
    private String projectName;
    private Long projectId;
    private String reviewConten;
    private String reviewStatus;
    private String reviewSubmit;
    private String type;
    private Long recList;
    private Long recReport;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String applyUnit;

}

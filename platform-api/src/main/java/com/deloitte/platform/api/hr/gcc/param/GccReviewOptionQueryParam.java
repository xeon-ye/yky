package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :  GccReviewOption查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GccReviewOptionQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long declareId;
    private String declareName;
    private Long reviewId;
    private String reviewName;
    private Long groupId;
    private String reviewContent;
    private Long reviewPlatformId;
    private String type;
    private String orgCode;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long createBy;
    private Long updateBy;
    private String remarks;
    private String reviewStatus;

}

package com.deloitte.platform.api.srpmp.outline.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :  SrpmsOutlineCooperation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsOutlineCooperationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long baseId;
    private String proCode;
    private String proName;
    private String cooperationCat;
    private String cooperationOrg;
    private String proInCharge;
    private Double proOutlay;
    private LocalDateTime proStartDate;
    private LocalDateTime proEndDate;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long orgId;// 单位名称

    private String year;// 年

    private String month;// 月

}

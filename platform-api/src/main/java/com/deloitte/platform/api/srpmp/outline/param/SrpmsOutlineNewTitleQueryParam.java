package com.deloitte.platform.api.srpmp.outline.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description :  SrpmsOutlineNewTitle查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsOutlineNewTitleQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long baseId;

    private String proCode;

    private String proName;

    private String proCategory;

    private String[] proCategoryArray;

    private String proSourceOrg;

    private String proEnterType;

    private String proInCharge;

    private LocalDateTime proStartDate;

    private LocalDateTime proEndDate;

    private String proState;

    private Double proTotalOutlay;

    private Double proReceiveOutlay;

    private String remarks;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private Long orgId;

    private String year;

    private String month;

}

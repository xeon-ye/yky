package com.deloitte.platform.api.srpmp.outline.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description :  SrpmsOutlineNewTitle查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsOutlineQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    private Long orgId;// 单位ID

    private String orgName;// 单位名称

    private String year;// 年

    private String month;// 月

}

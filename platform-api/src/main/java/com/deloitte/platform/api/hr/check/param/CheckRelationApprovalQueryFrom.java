package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRelation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRelationApprovalQueryFrom extends BaseQueryForm {
    @ApiModelProperty(value = "考核关系Id集合")
    private List<String> checkRelationIdList;
}

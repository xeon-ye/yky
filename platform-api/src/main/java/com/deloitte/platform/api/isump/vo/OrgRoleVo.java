package com.deloitte.platform.api.isump.vo;

import com.deloitte.platform.api.utils.JsonLongDeserializer;
import com.deloitte.platform.api.utils.JsonLongSerialize;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : OrgRole返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgRoleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "组织架构ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long roleId;

}

package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.api.utils.JsonLongDeserializer;
import com.deloitte.platform.api.utils.JsonLongSerialize;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : RoleResource返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResourceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long roleId;

    @ApiModelProperty(value = "资源ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long resourceId;

}

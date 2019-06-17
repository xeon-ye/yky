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
 * @Description : Role返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(审批，功能，数据)")
    private String type;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "服务类型，srpmp")
    private String service;

    @ApiModelProperty(value = "角色code")
    private String code;

}

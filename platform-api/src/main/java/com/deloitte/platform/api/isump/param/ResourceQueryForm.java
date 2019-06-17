package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.api.utils.JsonLongDeserializer;
import com.deloitte.platform.api.utils.JsonLongSerialize;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Resource查询from对象
 * @Modified :
 */
@ApiModel("Resource查询表单")
@Data
public class ResourceQueryForm extends BaseQueryForm<ResourceQueryParam>  {


    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "跳转链接")
    private String uri;

    @ApiModelProperty(value = "权限字符串")
    private String perms;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单层级")
    private String levels;

    @ApiModelProperty(value = "是否是叶子")
    private Integer leaf;

    @ApiModelProperty(value = "是否打开")
    private Integer open;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    private String state;

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

    @ApiModelProperty(value = "所属系统")
    private String sysCode;
}

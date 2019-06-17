package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-18
 * @Description : Organization返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrOrganizationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "上级编码")
    private String parentCode;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预留字段1")
    private String reserve;

    @ApiModelProperty(value = "预留字段2")
    private String version;

    @ApiModelProperty(value = "统一信用代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "下级部门")
    private List<HrOrganizationVo> children;

    public String getLabel(){
        return name;
    }

    public String getValue(){
        return id;
    }
}

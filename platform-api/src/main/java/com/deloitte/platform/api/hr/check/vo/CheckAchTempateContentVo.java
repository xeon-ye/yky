package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAchTempateContent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckAchTempateContentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "业绩模板id")
    private String achTempateId;

    @ApiModelProperty(value = "序号")
    private String orderNumber;

    @ApiModelProperty(value = "显示名称")
    private String displayName;

    @ApiModelProperty(value = "字段类型")
    private String fieldType;

    @ApiModelProperty(value = "表格列标题")
    private String columnTitle;

    @ApiModelProperty(value = "是否显示")
    private String isDisplay;

    @ApiModelProperty(value = "允许字数")
    private Long wordNumber;

    @ApiModelProperty(value = "列宽")
    private Long columnWidth;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}

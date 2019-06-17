package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description : SrpmsOutlineBase新增修改form对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineBaseVo extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "题录类型")
    private String type;

    @ApiModelProperty(value = "记录总条数")
    private Long total;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "新获项目列表")
    private List<SrpmsOutlineNewTitleVo> deputyAccounts;

}

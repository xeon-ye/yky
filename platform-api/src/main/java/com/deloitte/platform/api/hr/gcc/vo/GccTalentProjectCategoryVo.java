package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccTalentProjectCategory返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccTalentProjectCategoryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "所属项目")
    private String projectCode;

    @ApiModelProperty(value = "项目类别名称")
    private String projectCategoryName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否引用")
    private String quote;

}

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
 * @Description : GccIncludeProject返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccIncludeProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "专家管理编号")
    private String userId;

    @ApiModelProperty(value = "项目编号")
    private String projectId;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "入选时间")
    private LocalDateTime selectedTime;

    @ApiModelProperty(value = "项目名称")
    private String projectName;


}

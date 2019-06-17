package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : jiangString
 * @Date : Create in 2019-04-01
 * @Description : GccProDecNotice返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeForProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "项目编号")
    private String id;

    @ApiModelProperty(value = "项目编号")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;


}

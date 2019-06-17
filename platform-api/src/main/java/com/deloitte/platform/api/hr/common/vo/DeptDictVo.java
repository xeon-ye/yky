package com.deloitte.platform.api.hr.common.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-07
 * @Description : Dept返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDictVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位code")
    private String deptCode;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

}

package com.deloitte.platform.api.srpmp.common.vo.ext;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SerialNoCenter返回的VO对象
 * @Modified :
 */
@Data
public class loginInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private UserVo loginUser;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    private DeptVo loginDept;
}
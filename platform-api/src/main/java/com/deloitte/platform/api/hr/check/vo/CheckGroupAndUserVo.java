package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroup返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckGroupAndUserVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考核分组")
    private CheckGroupVo checkGroupVo;

    @ApiModelProperty(value = "分组人员列表")
    private List<CheckGroupUserVo> checkGroupUserList;

}

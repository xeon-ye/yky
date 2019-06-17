package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author woo
 * @Title: xx
 * @ProjectName platform
 * @Description: TODO
 * @date 19:40  2019/4/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPracticalRuleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private CheckRuleVo CheckRuleVo;

    @ApiModelProperty(value = "等级规则名称")
    private List<CheckRuleContentVo> list;

}


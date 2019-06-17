package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroupUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckGroupUser表单")
@Data
public class CheckGroupUserCountForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核分组id")
    private List<String> checkGroupIdList;

    @ApiModelProperty(value = "分组类别 1评价人，2被评价人")
    private String groupType;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;
}

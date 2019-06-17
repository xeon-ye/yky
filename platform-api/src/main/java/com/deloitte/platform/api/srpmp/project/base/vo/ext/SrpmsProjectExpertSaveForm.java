package com.deloitte.platform.api.srpmp.project.base.vo.ext;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectExpertVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description : SrpmsProjectExpert新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectExpert表单")
@Data
public class SrpmsProjectExpertSaveForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private List<Long> projectList;

    @ApiModelProperty(value = "type")
    private String type;

    @ApiModelProperty(value = "员工编号")
    private List<SrpmsProjectExpertVo> expertList;
}

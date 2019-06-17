package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/14 13:50
 * @Description :
 * @Modified:
 */
@Api("项目评价Form")
@Data
public class ProjectEvaluationForm extends BaseForm {

    private static final Long SerialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "附件（多个）")
    private List<FileVo> enclosureFormList;


}

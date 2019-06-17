package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/6/10 10:34
 * @Description : 取消项目申请From
 * @Modified:
 */
@Api(value = "项目取消申请from")
@Data
public class CancelProjectFrom extends BaseForm {

    private static final Long serialVersionUID = 1L;

    /*@ApiModelProperty("项目from")
    private ProjectsForm projectsForm;

    @ApiModelProperty("申报书from")
    private ApplicationForm applicationForm;

    @ApiModelProperty("业务单号from")
    private ServiceNumForm serviceNumForm;*/

    @ApiModelProperty("申报书ID")
    private String applicationId;

    @ApiModelProperty("项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty("业务单号")
    private String applyCancelNum;

    @ApiModelProperty("项目取消原因")
    private String proCancelDes;

    @ApiModelProperty("附件（List）")
    private List<FileVo> enclosureFormList;

}

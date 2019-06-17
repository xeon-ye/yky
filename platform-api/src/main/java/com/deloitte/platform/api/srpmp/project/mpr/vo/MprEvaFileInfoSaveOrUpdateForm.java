package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author:LIJUN
 * Date:01/04/2019
 * Description:
 */
@ApiModel("保存或者更新文件信息")
@Data
public class MprEvaFileInfoSaveOrUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "附件二")
    private List<MprEvaFileInfoFieldForm> annexTwo;

    @ApiModelProperty(value = "附件三")
    private MprEvaFileInfoFieldForm annexThree;

    @ApiModelProperty(value = "附件四")
    private MprEvaFileInfoFieldForm annexFour;

    @ApiModelProperty(value = "附件五")
    private MprEvaFileInfoFieldForm annexFive;

    @ApiModelProperty(value = "附件七")
    private MprEvaFileInfoFieldForm annexSeven;

    @ApiModelProperty(value = "附件八")
    private MprEvaFileInfoFieldForm annexEight;

    @ApiModelProperty(value = "附件十")
    private MprEvaFileInfoFieldForm annexTen;

    @ApiModelProperty(value = "附件十一")
    private MprEvaFileInfoFieldForm annexEleven;

    @ApiModelProperty(value = "附件十二")
    private MprEvaFileInfoFieldForm annexTwelve;

    @ApiModelProperty(value = "其他附件")
    private List<MprEvaFileInfoFieldForm> annexOther;

}

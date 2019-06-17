package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckAchNotify新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAchNotify表单")
@Data
public class CheckAchNotifyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核关系id集合")
    private List<String> checkRelationIdList;

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核期间id")
    private Long checkTimeId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "业绩考核模板id")
    private Long checkAchTempateId;

    @ApiModelProperty(value = "通知名称")
    private String achNotifyName;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

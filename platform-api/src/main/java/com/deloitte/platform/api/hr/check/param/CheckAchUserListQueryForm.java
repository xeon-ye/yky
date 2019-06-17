package com.deloitte.platform.api.hr.check.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckAchUser查询from对象
 * @Modified :
 */
@ApiModel("CheckAchUser查询表单")
@Data
public class CheckAchUserListQueryForm extends BaseQueryForm<CheckAchUserQueryParam>  {




    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核期间id")
    private LocalDateTime CheckTimeId;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}

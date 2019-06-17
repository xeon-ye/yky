package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProDecNotice查询from对象
 * @Modified :
 */
@ApiModel("申报自助查询表单")
@Data
public class GccProDecNoticeSelfForm  extends BaseQueryForm<GccProDecNoticeSelfParam> {


    @ApiModelProperty(value = "通知名称")
    private String noticeName;


    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "用户user_id")
    private String userId;

    @ApiModelProperty(value = "是否有效")
    private String status;


}

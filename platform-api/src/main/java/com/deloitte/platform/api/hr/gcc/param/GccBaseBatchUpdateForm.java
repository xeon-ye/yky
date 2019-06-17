package com.deloitte.platform.api.hr.gcc.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-11
 * @Description :   GccSpecialistGroup查询from对象
 * @Modified :
 */
@ApiModel("GccBaseBatchForm修改表单")
@Data
public class GccBaseBatchUpdateForm {


    @ApiModelProperty(value = "审核状态")
    private String auditStatus;


    @ApiModelProperty(value = "用户userId")
    private Long userId;


}

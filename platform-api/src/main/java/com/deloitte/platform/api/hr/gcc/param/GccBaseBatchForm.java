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
@ApiModel("GccBaseBatchForm删除表单")
@Data
public class GccBaseBatchForm {


    @ApiModelProperty(value = "主键")
    private Long[] ids;


}

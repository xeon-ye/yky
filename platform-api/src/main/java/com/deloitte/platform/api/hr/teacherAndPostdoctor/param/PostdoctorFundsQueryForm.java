package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorFunds查询from对象
 * @Modified :
 */
@ApiModel("博士后经费录入查询表单")
@Data
public class PostdoctorFundsQueryForm extends BaseQueryForm<PostdoctorFundsQueryParam>  {


    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private Integer status;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "流动站")
    private String stationName;

}

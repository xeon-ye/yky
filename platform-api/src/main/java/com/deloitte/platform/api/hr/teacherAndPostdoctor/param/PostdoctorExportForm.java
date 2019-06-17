package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   Postdoctor查询导出from对象
 * @Modified :
 */
@ApiModel("Postdoctor导出表单")
@Data
public class PostdoctorExportForm {


    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "身份类型（1统招统分，2在职人员，3博新计划进站，4其他）")
    private String cardType;

    @ApiModelProperty(value = "招收类型（1流动站自主招收，2与工作站联合培养）")
    private String recruitType;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private Integer status;

    @ApiModelProperty(value = "进站年份")
    private String pullYear;

    @ApiModelProperty(value = "进站单位")
    private String attachUnit;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;

//    @NotNull(message = "列表类型不能为空")
//    @ApiModelProperty(value = "查询列表类型（1在站，2出站退站，3延期，4全部）")
//    private Integer type;

}

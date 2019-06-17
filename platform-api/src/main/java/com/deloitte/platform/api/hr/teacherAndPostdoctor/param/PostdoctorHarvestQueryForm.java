package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   在站成果审核列表查询from对象
 * @Modified :
 */
@ApiModel("在站成果审核列表查询表单")
@Data
public class PostdoctorHarvestQueryForm extends BaseQueryForm<PostdoctorQueryParam>  {


    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "身份类型")
    private String cardType;

    @ApiModelProperty(value = "招收类型")
    private String recruitType;

    @ApiModelProperty(value = "博士后状态")
    private Integer status;

    @ApiModelProperty(value = "申请状态(1已申请保存，2已申报提交，3合作导师审核通过，4合作导师审核不通过，5所院审核通过，6所院审核未通过，7院校人力资源处审核通过，8院校人力资源处审核未通过)")
    private Integer applyStatus;

    @ApiModelProperty(value = "所属单位")
    private String attachUnit;

    @ApiModelProperty(value = "流动站（一级学科）")
    private String stationName;
}

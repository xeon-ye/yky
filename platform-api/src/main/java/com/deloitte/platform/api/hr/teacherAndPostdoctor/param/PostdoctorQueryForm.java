package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   Postdoctor查询from对象
 * @Modified :
 */
@ApiModel("Postdoctor查询表单")
@Data
public class PostdoctorQueryForm extends BaseQueryForm<PostdoctorQueryParam>  {


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

}

package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorFundsRecord返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorFundsRecordVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "博士后编号")
    private String postdoctorCode;

    @ApiModelProperty(value = " 姓名")
    private String name;

    @ApiModelProperty(value = "所属流动站")
    private String stationName;

    @ApiModelProperty(value = "博士后状态 （1在站，2出站，3退站，4延期）")
    private String status;

    @ApiModelProperty(value = "项目类型（1人才项目，2基金项目，3基本年薪）")
    private String projectType;

    @ApiModelProperty(value = "博士后经费总额（元）")
    private BigDecimal funds;

    @ApiModelProperty(value = "经费使用类型（1费用报销，2劳务专家咨询费支出，3固定资产新增，4其他）")
    private String type;

    @ApiModelProperty(value = "使用原因")
    private String remark;

    @ApiModelProperty(value = "使用金额（元）")
    private BigDecimal useFunds;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "使用时间")
    private LocalDate useTime;

}

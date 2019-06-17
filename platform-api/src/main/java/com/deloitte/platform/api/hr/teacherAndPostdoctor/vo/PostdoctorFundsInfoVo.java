package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorFunds返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorFundsInfoVo extends BaseVo {
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "进站时间")
    private LocalDate pullTime;

    @ApiModelProperty(value = "经费类型（1人才项目，2基金项目，3基本年薪）")
    private String fundsType;

    @ApiModelProperty(value = "博士后经费总额（元）")
    private BigDecimal funds;

    @ApiModelProperty(value = "已使用（元）")
    private BigDecimal useMoney;

    @ApiModelProperty(value = "经费剩余（元）")
    private BigDecimal surplusFunds;

    @ApiModelProperty(value = "博士后使用类型")
    private Integer type;

    @ApiModelProperty(value = "博士后使用费用（元）")
    private BigDecimal useFunds;

    @ApiModelProperty(value = "使用原因")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "使用时间")
    private LocalDate useTime;

}

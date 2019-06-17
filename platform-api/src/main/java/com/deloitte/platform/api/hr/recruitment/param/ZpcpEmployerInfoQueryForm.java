package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :   ZpcpEmployerInfo查询from对象
 * @Modified :
 */
@ApiModel("ZpcpEmployerInfo查询表单")
@Data
public class ZpcpEmployerInfoQueryForm extends BaseQueryForm<ZpcpEmployerInfoQueryParam>  {

    @ApiModelProperty(value = "教职工编号")
    private Long empCode;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "教职工姓名")
    private String name;

    @ApiModelProperty(value = "单位编号")
    private String deptCode;

    @ApiModelProperty(value = "岗位编号")
    private String stationCode;

    @ApiModelProperty(value = "聘用类型编号")
    private String employCoder;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;
    /*
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "聘用时间起")
    private LocalDateTime employmentStarttime;

    @ApiModelProperty(value = "聘用时间止")
    private LocalDateTime employmentEndtime;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long totalSalay;

    @ApiModelProperty(value = "聘用类型编码")
    private Long employCode;


    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织编码")
    private String organizationCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;
    */
}

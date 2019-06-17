package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpStation查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpStation查询表单")
@Data
public class HrZpcpStationQueryForm extends BaseQueryForm<HrZpcpStationQueryParam>  {

    @ApiModelProperty(value = "岗位编号")
    private String stationCode;

    @ApiModelProperty(value = "聘用类型编号")
    private String employTypeId;

    @ApiModelProperty(value = "聘用类型名称")
    private  String employName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "是否有效：0表示无效，1表示 有效")
    private String status;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "岗位人数")
    private Integer stationNumber;

    @ApiModelProperty(value = "备注")
    private String remaks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "主键")
    private String id;
}

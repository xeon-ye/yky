package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpEmployType查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpEmployType查询表单")
@Data
public class HrZpcpEmployTypeQueryForm extends BaseQueryForm<HrZpcpEmployTypeQueryParam>  {


    /*
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "聘用类型编码")
    private String employCoder;
    */
    @ApiModelProperty(value = "聘用类型名称")
    private String employName;

    @ApiModelProperty(value = "单位编码")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "是否有效：0表示无效，1表示 有效")
    private String status;

    /*@ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;*/
}

package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireRehiringPerson查询from对象
 * @Modified :
 */
@ApiModel("RetireRehiringPerson查询表单")
@Data
public class RehiringPersonQueryForm extends BaseQueryForm<RehiringPersonQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "返聘部门")
    private Long rehiringDept;

    @ApiModelProperty(value = "返聘岗位")
    private Long rehiringPost;

    @ApiModelProperty(value = "返聘开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "返聘结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;
}

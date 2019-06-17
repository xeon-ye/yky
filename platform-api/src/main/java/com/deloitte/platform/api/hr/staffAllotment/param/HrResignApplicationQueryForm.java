package com.deloitte.platform.api.hr.staffAllotment.param;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :   HrResignApplication查询from对象
 * @Modified :
 */
@ApiModel("HrResignApplication查询表单")
@Data
public class HrResignApplicationQueryForm extends BaseQueryForm<HrResignApplicationQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申请人编码")
    private String staffCode;

    @ApiModelProperty(value = "申请人日期")
    private LocalDateTime dat;

    @ApiModelProperty(value = "辞职原因：下拉选项")
    private String reason;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "辞职说明")
    private String exp;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "档案接收单位")
    private String fileReceivingUnit;

    @ApiModelProperty(value = "离职去向")
    private String leaveTo;
}

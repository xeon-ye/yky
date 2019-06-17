package com.deloitte.platform.api.srpmp.relust.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-27
 * @Description :   SrpmsResultTrans查询from对象
 * @Modified :
 */
@ApiModel("SrpmsResultTrans查询表单")
@Data
public class SrpmsResultTransQueryForm extends BaseQueryForm<SrpmsResultTransQueryParam>  {


    @ApiModelProperty(value = "成果转化id")
    private Long id;

    @ApiModelProperty(value = "成果id")
    private Long resultId;

    @ApiModelProperty(value = "成果名称")
    private String resultName;

    @ApiModelProperty(value = "成果转化名称")
    private String resultTransName;

    @ApiModelProperty(value = "转化方式")
    private String transWay;

    @ApiModelProperty(value = "合同号")
    private String contractNum;

    @ApiModelProperty(value = "合同金额")
    private Long contractAmount;

    @ApiModelProperty(value = "合同签订日")
    private String contractSigningDay;

    @ApiModelProperty(value = "转化费来源")
    private String transFeeSource;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}

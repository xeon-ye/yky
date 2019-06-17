package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-12
 * @Description :   SysSignSubjectInfo查询from对象
 * @Modified :
 */
@ApiModel("SysSignSubjectInfo查询表单")
@Data
public class SysSignSubjectInfoQueryForm extends BaseQueryForm<SysSignSubjectInfoQueryParam>  {


    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "签约主体编码")
    private String subjectCode;

    @ApiModelProperty(value = "签约主体")
    private String subjectName;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxNum;

    @ApiModelProperty(value = "增值税纳税人类型")
    private String taxPayType;

    @ApiModelProperty(value = "详细地址")
    private String subjectAddress;

    @ApiModelProperty(value = "状态")
    private String statue;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    @ApiModelProperty(value = "账号名称")
    private String accountName;

    @ApiModelProperty(value = "银行账号")
    private String bankName;
}

package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-12
 * @Description : SysSignSubjectInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysSignSubjectInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private String id;

    @ApiModelProperty(value = "签约主体编码")
    private String subjectCode;

    @ApiModelProperty(value = "签约主体")
    private String subjectName;

    @ApiModelProperty(value = "纳税人识别号")
    private String taxNum;

    @ApiModelProperty(value = "法人代表")
    private String deptLegalPersonName;

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

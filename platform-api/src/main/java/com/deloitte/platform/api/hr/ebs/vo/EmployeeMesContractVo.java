package com.deloitte.platform.api.hr.ebs.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : JETVAE
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesContract返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMesContractVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "合同编号")
    private String conNo;

    @ApiModelProperty(value = "合同起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate conStartDate;

    @ApiModelProperty(value = "合同终止日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate conEndDate;

    @ApiModelProperty(value = "合同类型（1聘用合同，2固定期限劳动合同，3无固定期限劳动合同，4劳务协议，5劳务派遣协议）")
    private String conType;

    @ApiModelProperty(value = "用工类型（1事业单位编制内聘用，2劳动关系，3劳务协议，4兼职/借调，4退休返聘，5人才派遣）")
    private String empType;

    @ApiModelProperty(value = "合同期限")
    private String conPer;

    @ApiModelProperty(value = "合同状态（1进行中，2已结束）")
    private String status;

    @ApiModelProperty(value = "合同主体")
    private String conSub;

    @ApiModelProperty(value = "经费来源（1财政拨款，2经费自理）")
    private String souFund;

    @ApiModelProperty(value = "项目经费金额（元）")
    private BigDecimal projectFound;

    @ApiModelProperty(value = "人员信息CODE")
    private String userCode;

}

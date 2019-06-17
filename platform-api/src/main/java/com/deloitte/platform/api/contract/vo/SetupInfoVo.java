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
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description : SetupInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetupInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "合同办结原因")
    private String setupResult;

    @ApiModelProperty(value = "合同办结时间")
    private LocalDateTime setupTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @ApiModelProperty(value = "备用字段1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段5")
    private Long spareField5;

}

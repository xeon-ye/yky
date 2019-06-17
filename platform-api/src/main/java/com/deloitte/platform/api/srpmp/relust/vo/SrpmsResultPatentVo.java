package com.deloitte.platform.api.srpmp.relust.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : Apeng
 * @Date : Create in 2019-05-14
 * @Description : SrpmsResultPatent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultPatentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "申请专利号")
    private String applicationNum;

    @ApiModelProperty(value = "授权专利号")
    private String patentNum;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利类别")
    private String patentCat;

    @ApiModelProperty(value = "专利是否授权")
    private String authorizedFlag;

    @ApiModelProperty(value = "申请人")
    private String proposer;

    @ApiModelProperty(value = "申请日期")
    private LocalDateTime applicationTime;

    @ApiModelProperty(value = "区域 10-全国 20-省内")
    private String authorizedRegion;

}

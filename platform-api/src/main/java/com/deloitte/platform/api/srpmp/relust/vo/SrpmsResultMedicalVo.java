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
 * @Description : SrpmsResultMedical返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultMedicalVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "新药/器械名称")
    private String medicalName;

    @ApiModelProperty(value = "类别")
    private String medicalCat;

    @ApiModelProperty(value = "新药/器械证书号")
    private String medicalCertificateNum;

    @ApiModelProperty(value = "批准时间")
    private LocalDateTime applyDate;

    @ApiModelProperty(value = "项目负责人")
    private String proInCharge;

    @ApiModelProperty(value = "药品分类")
    private String medicalType;

    @ApiModelProperty(value = "药品等级")
    private String medicalLevel;

}

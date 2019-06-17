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
 * @Description : SrpmsResultAcaExc返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultAcaExcVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "区域 10-国内 20-国外")
    private String region;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    private String sponsorFlag;

    @ApiModelProperty(value = "参与单位")
    private String joinOrg;

    @ApiModelProperty(value = "会议类型")
    private String joinType;

    @ApiModelProperty(value = "参与形式")
    private String joinCat;

    @ApiModelProperty(value = "举办时间")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "会议名称")
    private String teamName;

}

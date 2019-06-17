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
 * @Date : Create in 2019-05-15
 * @Description : SrpmsResultNewTitle返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultNewTitleVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位CODE")
    private Long deptCode;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "月")
    private String month;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "项目类别")
    private String proCategory;

    @ApiModelProperty(value = "参与程度")
    private String proEnterType;

    @ApiModelProperty(value = "项目状态")
    private String proState;

    @ApiModelProperty(value = "项目总经费")
    private Double proTotalOutlay;

    @ApiModelProperty(value = "项目到位经费")
    private Double proReceiveOutlay;

}

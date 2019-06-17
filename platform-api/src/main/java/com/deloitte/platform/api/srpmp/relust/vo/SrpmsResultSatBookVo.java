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
 * @Description : SrpmsResultSatBook返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsResultSatBookVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目编码")
    private String proNum;

    @ApiModelProperty(value = "项目名称")
    private String proName;

    @ApiModelProperty(value = "著作名")
    private String bookName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "作者单位")
    private String bookOrg;

    @ApiModelProperty(value = "主编/参编")
    private String bookLevel;

    @ApiModelProperty(value = "出版社")
    private String press;

    @ApiModelProperty(value = "出版时间")
    private LocalDateTime pressTime;

}

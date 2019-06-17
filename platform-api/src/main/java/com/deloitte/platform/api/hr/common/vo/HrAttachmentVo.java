package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description : Attachment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrAttachmentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "文件系统ID")
    private String fileId;

    @ApiModelProperty(value = "主表类型")
    private String masterType;

    @ApiModelProperty(value = "主表ID")
    private Long masterId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    @ApiModelProperty(value = "序号")
    private Integer sort;

    @ApiModelProperty(value = "使用标准模板 0不使用 1使用")
    private String worktemplate;

    @ApiModelProperty(value = "身份证")
    private String idNumber;

    @ApiModelProperty(value = "开始工作时间")
    private LocalDateTime startWorkTime;

    @ApiModelProperty(value = "姓名")
    private String name;

}

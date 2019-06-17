package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-18
 * @Description :   ZpcpNoticeStations查询from对象
 * @Modified :
 */
@ApiModel("ZpcpNoticeStations查询表单")
@Data
public class ZpcpNoticeStationsQueryForm extends BaseQueryForm<ZpcpNoticeStationsQueryParam>  {


    /*@ApiModelProperty(value = "主键")
    private Long id;
*/
    @ApiModelProperty(value = "通知的主键")
    private Long noticeId;

    /*@ApiModelProperty(value = "岗位ID")
    private Long stationId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人id")
    private Double createBy;

    @ApiModelProperty(value = "更新人id")
    private Double updateBy;*/
}

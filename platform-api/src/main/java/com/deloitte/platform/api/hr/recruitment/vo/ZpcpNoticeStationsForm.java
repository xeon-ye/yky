package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-18
 * @Description : ZpcpNoticeStations新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpNoticeStations表单")
@Data
public class ZpcpNoticeStationsForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "通知的主键")
    private Long noticeId;

    @ApiModelProperty(value = "岗位ID")
    private Long stationId;

}

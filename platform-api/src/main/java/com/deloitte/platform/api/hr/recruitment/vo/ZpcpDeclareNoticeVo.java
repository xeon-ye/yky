package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrDeptVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 员工自助通知列表vo
 * @Author tankui
 * @date
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZpcpDeclareNoticeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "通知主键")
    private String noticeId;

    @ApiModelProperty(value = "发布单位")
    private String unit;

    @ApiModelProperty(value = "单位详情")
    private HrDeptVo hrDeptVo;

    @ApiModelProperty(value = "岗位编号")
    private String stationId;

    @ApiModelProperty(value = "准聘长聘岗位名称")
    private String employName;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "发布日期")
    private LocalDateTime publicDate;

    @ApiModelProperty(value = "申报状态")
    private String status;

    @ApiModelProperty(value = "岗位信息列表")
    private List<HrZpcpStationVo> stationVoList;

    @ApiModelProperty(value = "年分")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

}

package com.deloitte.platform.api.hr.recruitment.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :   HrZpcpEmployNotice查询from对象
 * @Modified :
 */
@ApiModel("HrZpcpEmployNotice查询表单")
@Data
public class HrZpcpEmployNoticeQueryForm extends BaseQueryForm<HrZpcpEmployNoticeQueryParam>  {

    /*@ApiModelProperty(value = "岗位编号")
    private String stationId;
    */

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "通知名称")
    private String noticeName;

    @ApiModelProperty(value = "发布单位")
    private String unit;

    @ApiModelProperty(value = "是否立即发布,1是立即发布0是暂不发布.")
    private String isPublic;

    /*@ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "通知内容")
    private String noticeContent;

    @ApiModelProperty(value = "发布日期")
    private String publicDate;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "创建人id")
    private String createBy;

    @ApiModelProperty(value = "修改人id")
    private String updateBy;*/
}

package com.deloitte.platform.api.srpmp.outline.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-20
 * @Description : 查询统计返回对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsOutlineAwardReportVo extends BaseForm {

    @ApiModelProperty(value = "单位ID")
    private Long orgId;

    @ApiModelProperty(value = "国家级-最高奖统计数量")
    private int cBestCount;
    @ApiModelProperty(value = "国家级-国际合作奖统计数量")
    private int cCooperateCount;
    @ApiModelProperty(value = "国家级-发明奖一等奖统计数量")
    private int cNaturalFirstCount;
    @ApiModelProperty(value = "国家级-发明奖二等奖统计数量")
    private int cNaturalSecoendCount;
    @ApiModelProperty(value = "国家级-科技进步奖一等奖统计数量")
    private int cImproveFirstCount;
    @ApiModelProperty(value = "国家级-科技进步奖二等奖统计数量")
    private int cImproveSecoendCount;
    @ApiModelProperty(value = "国家级-发明奖一等奖统计数量")
    private int cInventFirstCount;
    @ApiModelProperty(value = "国家级-发明奖二等奖统计数量")
    private int cInventSecoendCount;

    @ApiModelProperty(value = "省部级-特等奖统计数量")
    private int sepcialCount;
    @ApiModelProperty(value = "省部级-自然科学类一等奖统计数量")
    private int sNaturalFirstCount;
    @ApiModelProperty(value = "省部级-自然科学类二等奖统计数量")
    private int sNaturalSecoendCount;
    @ApiModelProperty(value = "省部级-自然科学类三等奖统计数量")
    private int sNaturalThreeCount;
    @ApiModelProperty(value = "省部级-科技进步奖一等奖统计数量")
    private int sImproveFirstCount;
    @ApiModelProperty(value = "省部级-科技进步奖二等奖统计数量")
    private int sImproveSecoendCount;
    @ApiModelProperty(value = "省部级-科技进步奖三等奖统计数量")
    private int sImproveThreeCount;
    @ApiModelProperty(value = "省部级-发明奖一等奖统计数量")
    private int sInventFirstCount;
    @ApiModelProperty(value = "省部级-发明奖二等奖统计数量")
    private int sInventSecoendCount;
    @ApiModelProperty(value = "省部级-发明奖三等奖统计数量")
    private int sInventThreeCount;

    @ApiModelProperty(value = "高校科技奖励-自然科学类一等奖统计数量")
    private int gNaturalFirstCount;
    @ApiModelProperty(value = "高校科技奖励-自然科学类二等奖统计数量")
    private int gNaturalSecoendCount;
    @ApiModelProperty(value = "高校科技奖励-科技进步类一等奖统计数量")
    private int gImproveFirstCount;
    @ApiModelProperty(value = "高校科技奖励-科技进步类二等奖统计数量")
    private int gImproveSecoendCount;
    @ApiModelProperty(value = "高校科技奖励-发明奖一等奖统计数量")
    private int gInventFirstCount;
    @ApiModelProperty(value = "高校科技奖励-发明奖二等奖统计数量")
    private int gInventSecoendCount;

    @ApiModelProperty(value = "中华医学科技奖一等奖统计数量")
    private int zFirstCount;
    @ApiModelProperty(value = "中华医学科技奖二等奖统计数量")
    private int zSecoendCount;
    @ApiModelProperty(value = "中华医学科技奖三等奖统计数量")
    private int zThreeCount;

    @ApiModelProperty(value = "其他社会奖一等奖统计数量")
    private int oFirstCount;
    @ApiModelProperty(value = "其他社会奖二等奖统计数量")
    private int oSecoendCount;
    @ApiModelProperty(value = "其他社会奖三等奖统计数量")
    private int oThreeCount;

}

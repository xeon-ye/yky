package com.deloitte.platform.api.hr.check.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author woo
 * @Title: EbsReturnVo
 * @ProjectName platform
 * @Description: TODO
 * @date 17:31  2019/6/10
 */
@Data
public class EbsReturnVo {

    @ApiModelProperty(value = "status,返回状态：SUCCESS通过，FAILURE失败")
    private String status ;

    @ApiModelProperty(value = "msg,返回的消息")
    private String msg;

    @ApiModelProperty(value = "record,返回的数据，SUCCESS时为null")
    private JSONObject record;
}

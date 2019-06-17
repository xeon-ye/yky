package com.deloitte.platform.api.fssc.mq.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-29
 * @Description : SyncMsg返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncMsgVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "关联ID")
    private Long relationId;

    @ApiModelProperty(value = "消息类型")
    private String msgType;

    @ApiModelProperty(value = "消息主体")
    private Clob msgBody;

    @ApiModelProperty(value = "消息状态")
    private String msgStatus;

    @ApiModelProperty(value = "失败错误描述")
    private Clob msgErrorInfo;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}

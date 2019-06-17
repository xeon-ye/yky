package com.deloitte.platform.api.fssc.attchdef.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDefLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseFileDefLineVo extends BaseVo implements Cloneable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件类型ID")
    private String id;

    @ApiModelProperty(value = "附件类型定义ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long fileDefId;

    @ApiModelProperty(value = "附件类型名称")
    private String attachName;

    @ApiModelProperty(value = "文件ID")
    private String fileId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}

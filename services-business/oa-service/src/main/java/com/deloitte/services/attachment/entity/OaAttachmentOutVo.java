package com.deloitte.services.attachment.entity;

import com.deloitte.platform.api.oaservice.attachment.vo.OaAttachmentVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OaAttachmentOutVo extends OaAttachmentVo {

    public OaAttachmentOutVo(){
        super();
        this.url = this.getAttachUrl();
        this.title = this.getAttachName();
    }

    @ApiModelProperty(value = "输出url")
    private String url;

    @ApiModelProperty(value = "输出标题")
    private String title;

    @ApiModelProperty(value = "输出时间")
    private LocalDateTime applyDatetime;

    @Override
    public void setAttachName(String attachName) {
        super.setAttachName(attachName);
        this.title = this.getAttachName();
    }

    @Override
    public void setAttachUrl(String attachUrl) {
        super.setAttachUrl(attachUrl);
        this.url = this.getAttachUrl();
    }
}

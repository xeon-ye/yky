package com.deloitte.services.fssc.base.entity;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AccountSimple", description = "会计科目测试类")
public class AccountSimple {

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "会计科目编码")
    private String code;

    @ApiModelProperty(value = "会计科目名称")
    private String name;

    public AccountSimple(Long id,String code,String name){
        this.id = id;
        this.code = code;
        this.name = name;
    }
}

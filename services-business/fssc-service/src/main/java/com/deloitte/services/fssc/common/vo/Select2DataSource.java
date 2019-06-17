package com.deloitte.services.fssc.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 *  Select2下拉框,需要的数据格式
 *  @author jawjiang
 */
@Data
@ApiModel("下拉框数据源")
public class Select2DataSource {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "显示文本")
    private String text;

    @ApiModelProperty(value = "与ID值一致")
    private String value;

    @ApiModelProperty(value = "是否默认选中",notes = "选中：Y,不选中：其他")
    private String selected;

    @ApiModelProperty(value = "小类id")
    private String inComeSubTypeId;

    @ApiModelProperty(value = "大类id")
    private String inComeMainTypeId;

    @ApiModelProperty(value = "项目承担单位")
    private Long projectUnitId;

    @ApiModelProperty(value = "收入结转方式")
    private String paymentConfirmType;

    public Select2DataSource(){}

    public Select2DataSource(String id, String text){
        this.id = id;
        this.value = id;
        this.text = text;
    }

    public Select2DataSource(String id, String code,String text){
        this.id = id;
        this.value = id;
        this.code = code;
        this.text = text;
    }

    public Select2DataSource(Long id, String code,String text){
        this.id = id + "";
        this.value = id + "";
        this.code = code;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Select2DataSource that = (Select2DataSource) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

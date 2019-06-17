package com.deloitte.services.fssc.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 *  自定义的Long类型的属性编辑器
 *  @author jawjiang
 */
@Slf4j
public class MyCustomLongEditor extends CustomNumberEditor {

    public MyCustomLongEditor() {
        super(Long.class, true);
    }

    @Override
    public void setAsText(String text) {
        if ((text == null) || text.trim().equals("")) {
            setValue(null);
        } else {
            Long value;
            try {
                //按照标准的数字格式尝试转换
                value = Long.parseLong(text);
            } catch (NumberFormatException e) {
                //尝试去除逗号 然后再转换
                log.warn("Long类型属性,存在千分位,{}",text);
                text = text.replace(",", "");
                value = Long.parseLong(text);
            }
            //转好之后将值返给被映射的属性
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        String text = super.getAsText();
        log.info("返回前端的值,{}",text);
        return text.replace(",", "");
    }
}

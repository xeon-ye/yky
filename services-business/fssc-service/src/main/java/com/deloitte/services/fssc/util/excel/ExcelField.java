package com.deloitte.services.fssc.util.excel;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Documented
public @interface ExcelField {
     int order() default 0;
     //String dateFormat() default "yyyy-MM-dd";
}

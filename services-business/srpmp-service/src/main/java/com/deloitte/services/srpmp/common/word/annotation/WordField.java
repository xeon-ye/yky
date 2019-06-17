package com.deloitte.services.srpmp.common.word.annotation;

/**
 * Created by lixin on 28/02/2019.
 */
public @interface WordField {

    int tableIndex();
    int rowIndex() default 0;
    int cellIndex() default 0;
    String ignoreContent() default "";

}

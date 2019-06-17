package com.deloitte.services.srpmp.common.word.dto;

import com.deloitte.services.srpmp.common.word.annotation.WordField;
import com.deloitte.services.srpmp.common.word.annotation.WordImport;

/**
 * Created by lixin on 28/02/2019.
 */
@WordImport(desc = "书籍")
public class Book {

    @WordField(tableIndex = 1, rowIndex = 1, cellIndex = 2)
    private String name;


}

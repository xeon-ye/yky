package com.deloitte.services.fssc.util.excel;

public class ExcelException extends RuntimeException{

    public ExcelException(String message){
        super(message);
    }

    public ExcelException(String message,Throwable throwable){
        super(message,throwable);
    }

    public ExcelException(Throwable cause){
        super(cause);
    }
}

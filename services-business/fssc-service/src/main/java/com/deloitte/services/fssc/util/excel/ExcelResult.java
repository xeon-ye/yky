package com.deloitte.services.fssc.util.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * Excel读取结果
 *
 * @author jawjiang
 */
@Data
public class ExcelResult {

    private List<Map<String, String>> tableData;

    private List<String> errorMsgList;

    private List<ErrorMsg> errorMsgBeanList;

    public ExcelResult() {
        errorMsgList = new ArrayList<>();
        errorMsgBeanList = new ArrayList<>();
    }

    public void addErrorMsg(String errorMsg) {
        this.errorMsgList.add(errorMsg);
        ErrorMsg error = new ErrorMsg(errorMsg);
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public void addErrorMsg(int rowNum, String errorMsg) {
        StringBuilder msgBuilder = new StringBuilder()
                .append(rowNum).append("行,").append(errorMsg);
        this.errorMsgList.add(msgBuilder.toString());
        ErrorMsg error = new ErrorMsg(rowNum,errorMsg);
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public void addErrorMsg(int rowNum, String columnName, String errorMsg) {
        StringBuilder msgBuilder = new StringBuilder()
                .append(rowNum).append("行").append(",")
                .append(columnName).append("列,").append(errorMsg);
        this.errorMsgList.add(msgBuilder.toString());
        ErrorMsg error = new ErrorMsg(rowNum,columnName,errorMsg);
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public void addEmptyErrorMsg(int rowNum, String columnName) {
        StringBuilder msgBuilder = new StringBuilder()
                .append(rowNum).append("行").append(",")
                .append(columnName).append("列,").append(columnName).append("不能为空");
        this.errorMsgList.add(msgBuilder.toString());
        ErrorMsg error = new ErrorMsg(rowNum,columnName,new StringBuilder().append(columnName).append("不能为空").toString());
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public void addNotFindErrorMsg(int rowNum, String columnName) {
        StringBuilder msgBuilder = new StringBuilder()
                .append(rowNum).append("行").append(",")
                .append(columnName).append("列,").append(columnName).append("不存在");
        this.errorMsgList.add(msgBuilder.toString());
        ErrorMsg error = new ErrorMsg(rowNum,columnName,new StringBuilder().append(columnName).append("不存在").toString());
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public void addErrorMsg(int rowNum, int columnNum, String errorMsg) {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(rowNum).append("行,").append(columnNum).
                append("列").append(",").append(errorMsg);
        this.errorMsgList.add(msgBuilder.toString());
        ErrorMsg error = new ErrorMsg(rowNum,columnNum,errorMsg);
        error.setIndex(errorMsgBeanList.size() + 1);
        errorMsgBeanList.add(error);
    }

    public boolean readSuccess() {
        return errorMsgList.isEmpty();
    }

    @Override
    public String toString() {
        return "ExcelResult [tableData=" + tableData + ", errorMsgList=" + errorMsgList + "]";
    }


}

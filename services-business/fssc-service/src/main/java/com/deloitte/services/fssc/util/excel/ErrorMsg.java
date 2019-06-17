package com.deloitte.services.fssc.util.excel;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Excel导入的错误信息
 * @author jawjiang
 */
@Data
public class ErrorMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer index;

    /**
     * 行号
     */
    private Integer rowNum;

    /**
     * 列号
     */
    private Integer columnNum;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 成功标识
     */
    private String successId;

    /**
     * 失败原因
     */
    private String failCause;

    /**
     * 总行数
     */
    private Integer rowNumAll;

    /**
     * 成功数
     */
    private Integer successNum;

    /**
     * 失败数
     */
    private Integer failNum;

    private List<ErrorMsg> list;

    public ErrorMsg(){

    }

    public ErrorMsg(String failCause){
        this.failCause = failCause;
    }

    public ErrorMsg(Integer rowNum, String failCause){
        this.rowNum = rowNum;
        this.failCause = failCause;
    }

    public ErrorMsg(Integer rowNum,Integer columnNum, String columnName, String failCause){
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.columnName = columnName;
        this.failCause = failCause;
    }

    public ErrorMsg(Integer rowNum,Integer columnNum, String failCause){
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        this.failCause = failCause;
    }


    public ErrorMsg(Integer rowNum,String columnName, String failCause){
        this.rowNum = rowNum;
        this.columnName = columnName;
        this.failCause = failCause;
    }

    @Override
    public String toString() {
        return "ErrorMsg [rowNum=" + rowNum + ", successId=" + successId + ", failCause="
                + failCause + ", list=" + list
                + "]";
    }

}

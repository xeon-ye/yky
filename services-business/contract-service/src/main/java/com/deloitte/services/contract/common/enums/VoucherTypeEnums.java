package com.deloitte.services.contract.common.enums;

public enum VoucherTypeEnums {

    CONTRACT_BOOK("1","合同审批"),
    CONTRACT_SIGN_BOOK("2","合同签署"),
    CONTRACT_FINISH("3","合同办结"),
    CONTRACT_UNABLE("4","合同作废"),
    CONTRACT_OPERATOR_TRANSFER("5","经办人移交"),
    CONTRACT_EXECUTER_TRANSFER("6","履行人移交"),
    CONTRACT_SURE("7","合同定稿"),
    CONTRACT_EXECUTE("8","合同履行"),
    CONTRACT_PRINT("9","合同打印"),
    STANDARD_BOOK("10","标准文本审批"),
    STANDARD_UNABLE("11","标准文本失效"),
    TEST("12","测试"),
    CONTRACT_EXECUTER_ACCEPT("13","履行人接受"),
    CONTRACT_FILE_UPLOAD("14","合同扫描件上传"),
    STANDARD_BOOK_SUCCESS("101","标准文本发布成功"),
    CONTRACT_CONTRACT_EXECUTE("102","合同履行预警"),;

    VoucherTypeEnums(String code,String value){
        this.code = code;
        this.value = value;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

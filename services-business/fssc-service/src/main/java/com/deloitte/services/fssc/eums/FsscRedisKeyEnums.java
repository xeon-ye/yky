package com.deloitte.services.fssc.eums;

import lombok.Getter;

@Getter
public enum FsscRedisKeyEnums {

    DICTIONARY("数值字典前缀","FSSC_DIC_"),
    FSSC_BANK_ID_BEAN_MAP("银行信息ID与Bean映射","FSSC_BANK_ID_BEAN_MAP"),
    FSSC_BANK_NUM_BEAN_MAP("银行账户与Bean映射","FSSC_BANK_NUM_BEAN_MAP"),
    FSSC_BANK_ACCOUNT_VO_MAP("银行账户与Vo映射","FSSC_BANK_ACCOUNT_VO_MAP"),
    ;

    /**
     * 错误类型码
     */
    private String description;
    /**
     * 错误类型描述信息
     */
    private String value;


    FsscRedisKeyEnums(String description, String value) {
        this.description = description;
        this.value = value;
    }


    public static FsscRedisKeyEnums getByValue(String value){
        for(FsscRedisKeyEnums enumValue : FsscRedisKeyEnums.values()){
            if(enumValue.value.equals(value)){
                return enumValue;
            }
        }
        return null;
    }
}

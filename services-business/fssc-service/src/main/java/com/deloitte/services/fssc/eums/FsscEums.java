package com.deloitte.services.fssc.eums;

import lombok.Getter;

@Getter
public enum FsscEums {
    //状态类枚举
    VALID("有效","Y"),
    UN_VALID("无效","N"),

    //工作流审批状态,
    NEW("新建","NEW"),
    SUBMIT("已提交","SUBMIT"),
    REJECTED("已驳回","REJECTED"),
    APPROVALING("审批中","APPROVALING"),
    APPROVED("已审批","APPROVED"),
    IN_ACCOUNTING("入账中","IN_ACCOUNTING"),
    HAS_ACCOUTED("已入账","HAS_ACCOUTED"),
    ACCOUNT_FAILD("入账失败","ACCOUNT_FAILD"),
    CHARGE_AGAINSTING("冲销中","CHARGE_AGAINSTING"),
    HAS_CHARGE_AGAINST("已冲销","HAS_CHARGE_AGAINST"),
    RECALLED("已撤回","RECALLED"),
    REVOKE("已撤销","REVOKE"),
    CLOSED("关闭","CLOSED"),

    //逻辑相对枚举
    YES("是","Y"),
    NO("否","N"),

    //排序枚举
    ASC("升序","asc"),
    DESC("倒序","desc"),

    //审核枚举
    PASS("通过","PASS"),
    REFUSED("拒绝","REFUSED"),

    //导入文件格式
    EXCEL_2003("Excel2003","xls"),
    EXCEL_2007("Excel2007","xlsx"),

    //预算阶段
    BUDGET_PHASE_PROCESSING("流程中","PROCESSING"),
    BUDGET_PHASE_PROCESSED("已审批","PROCESSED"),

    //虚拟预算科目
    BUDGET_CASH_POOLING_ACCOUNT_CODE("资金池","9999"),
    BUDGET_EDU_ACCOUNT_CODE("小规模办学金费","9998"),

    //单据预算状态
    DOCUMENT_BUDGET_STATUS_VALID("启用","VALID"),
    DOCUMENT_BUDGET_STATUS_INVALID("失效","INVALID"),

    //项目来源
    PROJECT_SOURCE_SRPMP("科研","1"),
    PROJECT_SOURCE_GENERAL ("通用","2"),

    //交易流水-业务类型
    TRADE_BUSINESS_BATCH_PAY("批量付款","BATCH_PAY"),
    TRADE_BUSINESS_BATCH_RECEIPT("批量收款","BATCH_RECEIPT"),

    //交易流水-处理状况
    TRADE_PROCESS_STATUS_DONE("已处理","DONE"),
    TRADE_PROCESS_STATUS_TODO("未处理","TODO"),
    TRADE_PROCESS_STATUS_PART_DONE("部分处理","PART_DONE"),

    //4A 人员类型 字段KEY
    PERSON_INCOME_TYPE_KEY("人员类型","user_type"),

    //交易信息-查询类型
    TRADE_INFO_QUERY_TYPE_TODAY("当日查询","TODAY"),
    TRADE_INFO_QUERY_TYPE_OLD("历史查询","OLD"),

    MQ_MESSAGE_STATUS_BEGIN("已开始","BEGIN"),
    MQ_MESSAGE_STATUS_END("已结束","END"),
    MQ_MESSAGE_STATUS_FAILED("失败","FAILED"),
    MQ_MESSAGE_STATUS_SUCCESS("成功","SUCCESS"),

    //绩效
    PERFORMANCE_INDEX_LEVEL_1("一级","1"),
    PERFORMANCE_INDEX_LEVEL_2("二级","2"),
    PERFORMANCE_INDEX_LEVEL_3("三级","3"),

    //序列名称
    UNIT_CODE_SCHOOL("1001","UNIT_CODE_SCHOOL"),
    UNIT_CODE_CUSTOMER("5001","UNIT_CODE_CUSTOMER"),

    //假审批历史记录表需要的枚举
    BASE_UNIT_TABLE("单位信息审核历史","BASE_UNIT_INFO"),
    BASE_BANK_UNIT_TABLE("银行审核历史","BASE_BANK_UNIT_INFO"),
    BASE_BANK_TABLE("银行基础信息审核历史","BASE_BANK_INFO"),
    BM_BORROW_MONEY_INFO("借款主表","BM_BORROW_MONEY_INFO"),

    //字典值查询代码
    IS_VALID("状态","IS_VALID"),
    BASE_UNIT_INFO_UNIT_TYPE("单位类型","BASE_UNIT_INFO.UNIT_TYPE"),
    RECIEVE_MONEY_TYPE("收款方式","RECIEVE_MONEY_TYPE"),
    PAYMENT_TYPE("付款方式","PAYMENT_TYPE"),
    BANK_TYPE("账户性质","BANK_TYPE"),
    CURRENCY_CODE("币种","CURRENCY_CODE"),
    DOCUMENT_STATUS("单据状态","DOCUMENT_STATUS"),
    PAY_STATUS("付款状态","PAY_STATUS"),
    PROJECT_STATUS_Y("项目状态","PROJECT_STATUS_Y"),
    REVIEVE_STATUS("收款状态","REVIEVE_STATUS"),
    BUDGET_TYPE("预算类型","BUDGET_TYPE"),
    BUDGET_TYPE_BASIC("基本预算","BASIC"),
    BUDGET_TYPE_PROJECT("项目预算","PROJECT"),
    DOCUMENT_FUNCTION_MODULE("单据功能模块","DOCUMENT_FUNCTION_MODULE"),
    PERFORMANCE_INDEX_JUDGE_CONDITION("预算指标判断条件","PERFORMANCE_INDEX_JUDGE_CONDITION"),

    //个人借款单
    TABLE_BORROW_DOCUMENT("个人借款单主表","BM_BORROW_MONEY_INFO"),
    //对公预付款单
    ADP_ADVANCE_PAYMENT_INFO("对公预付款主表","ADP_ADVANCE_PAYMENT_INFO"),
    //合同报账单主表
    CRB_CONTRACT_REIMBURSE_BILL("合同报账单主表","CRB_CONTRACT_REIMBURSE_BILL"),

    //工作流
    START_PROCESS("发起流程","startProcess"),
    PASS_PROCESS("审批通过","passProcess"),
    REJECT_PROCESS_TO_FIRST("驳回到发起人","rejectProcessToFirst"),
    REJECT_PROCESS_TO_SOMEONE("驳回到指定人","rejectProcessToSomeone"),
    ROLLBACK_PROCESS("撤回流程","rollBackProcess"),

    CHARGE_AGAINST("冲销","CHARGE_AGAINST"),
    NORMAL_APPROVAL("正常审批","NORMAL_APPROVAL"),

    //会计引擎
    AV_ACCOUNT_ELEMENT_TYPE_COA("COA","COA"),
    AV_ACCOUNT_ELEMENT_TYPE_HEAD("凭证头","HEAD"),
    AV_ACCOUNT_ELEMENT_TYPE_LINE("凭证行","LINE"),
    //页面端不同的状态
    AV_PAGE_STATUS_NEW("新建","NEW"),
    AV_PAGE_STATUS_COPY("复制","COPY"),
    AV_PAGE_STATUS_EDIT("编辑","EDIT"),
    AV_PAGE_STATUS_VIEW("查看","VIEW"),
    AC_PAGE_STATUS_AGAINST("冲销","REVERSE"),
    AV_ACCOUNT_CAI("财","C"),
    AV_ACCOUNT_YU("预","Y"),
    AV_LOGIC_METHOD_FIELD("字段","field"),
    AV_LOGIC_METHOD_DEFALUT("默认","default"),
    AV_LOGIC_METHOD_MAP("函数","map"),


    FIRST_SUBMIT("首次提交","FIRST_SUBMIT"),
    RESUBMIT_REJECT("驳回后提交","RESUBMIT_REJECT"),
    RESUBMIT_ROLLBACK("撤回后提交","RESUBMIT_ROLLBACK"),

    //报表
    PERIOD_TYPE_ANNUAL("报表周期类型-年报","Y"),
    PERIOD_TYPE_MONTH("报表周期类型-月报","M"),
    ;

    /**
     * 错误类型码
     */
    private String description;
    /**
     * 错误类型描述信息
     */
    private String value;

    FsscEums(String description, String value) {
        this.description = description;
        this.value = value;
    }


    public static FsscEums getByValue(String value){
        for(FsscEums tableNameEums:FsscEums.values()){
            if(tableNameEums.value.equals(value)){
                return tableNameEums;
            }
        }
        return null;
    }
}

package com.deloitte.services.fssc.eums;

import lombok.Getter;

/**
 * 系统中表
 */
@Getter
public enum FsscTableNameEums {
    //银行基本信息
    BASE_BANK_INFO("银行基本信息主表","BASE_BANK_INFO"),
    BASE_DATA_AUDIT_HIS("基本假审批历史表","BASE_DATA_AUDIT_HIS"),
    //单位银行信息,
    BASE_BANK_UNIT_INFO("单位和银行关联表","BASE_BANK_UNIT_INFO"),
    //单位信息

    //借款
    BM_BORROW_BANK("工资卡公务卡表","BM_BORROW_BANK"),
    BM_BORROW_MONEY_INFO("借款单","BM_BORROW_MONEY_INFO"),
    BM_BORROW_MONEY_LINE("借款行明细表","BM_BORROW_MONEY_LINE"),

    //通用报账
    GE_EXPENSE_BORROW_PREPAY("关联预付款借款表","GE_EXPENSE_BORROW_PREPAY"),
    GE_EXPENSE_PAYMENT_LIST("对公付款清单表","GE_EXPENSE_PAYMENT_LIST"),
    GE_GENERAL_EXPENSE("通用报账单","GE_GENERAL_EXPENSE"),
    GE_GENERAL_EXPENSE_LINE("通用报账行明细表","GE_GENERAL_EXPENSE_LINE"),

    //劳务费报账单
    GE_PRIVATE_PAYMENT_LIST("对私付款清单","GE_PRIVATE_PAYMENT_LIST"),
    LC_LABOR_COST("劳务费/咨询费报账单","LC_LABOR_COST"),
    LC_LABOR_COST_LINE_CHINA("发放明细中国籍","LC_LABOR_COST_LINE_CHINA"),
    LC_LABOR_COST_LINE_FOREIGN("发放明细外国籍","LC_LABOR_COST_LINE_FOREIGN"),


    //字段类型维护表
    T_DIC("字段类型维护表","T_DIC"),
    T_DIC_VALUE("字段类型维护值表","T_DIC_VALUE"),

    //承诺书信息表
    BASE_CONTENT_COMMITMENT("承诺书维护表","BASE_CONTENT_COMMITMENT"),
    BASE_CONTENT_COMMITMENT_UNIT("承诺书单位信息表","BASE_CONTENT_COMMITMENT_UNIT"),

    //支出大小类
    BASE_EXPENSE_MAIN_TYPE("支出大类表","BASE_EXPENSE_MAIN_TYPE"),
    BASE_EXPENSE_MAIN_TYPE_UNIT("支出大类单位信息表","BASE_EXPENSE_MAIN_TYPE_UNIT"),
    BASE_EXPENSE_SUB_TYPE("支出小类表","BASE_EXPENSE_SUB_TYPE"),
    BASE_EXPENSE_SUB_TYPE_UNIT("支出小类单位信息表","BASE_EXPENSE_SUB_TYPE_UNIT"),
    //收入大小类
    BASE_INCOME_MAIN_TYPE("收入大类表","BASE_INCOME_MAIN_TYPE"),
    BASE_INCOME_SUB_TYPE("收入小类表","BASE_INCOME_SUB_TYPE"),

    //单据类型定义
    BASE_DOCUMENT_TYPE("单据类型定义主表","BASE_DOCUMENT_TYPE"),
    BASE_DOCUMENT_TYPE_EXPENSE("单据类型定义-支出大类表","BASE_DOCUMENT_TYPE_EXPENSE"),
    BASE_DOCUMENT_TYPE_INCOME("单据类型定义-款项大类表","BASE_DOCUMENT_TYPE_INCOME"),
    BASE_DOCUMENT_TYPE_PAY_WAY("单据类型定义-支付方式表","BASE_DOCUMENT_TYPE_PAY_WAY"),
    BASE_PERSON_INCOME_TYPE("单据类型定义-人员类型表","BASE_PERSON_INCOME_TYPE"),

    //预算管理
    BUDGET_ADVANCE_APPLY_FOR("预算-事前申请","BUDGET_ADVANCE_APPLY_FOR"),
    BUDGET_ADVANCE_BORROW("预算-事前借款头表","BUDGET_ADVANCE_BORROW"),
    BUDGET_ADVANCE_BORROW_LINE("预算-事前借款行表","BUDGET_ADVANCE_BORROW_LINE"),
    BUDGET_AFTER_EXPENSE("预算-事后报销头表","BUDGET_AFTER_EXPENSE"),
    BUDGET_AFTER_EXPENSE_LINE("预算-事后报销行表","BUDGET_AFTER_EXPENSE_LINE"),
    BUDGET_AFTER_REPAYMENT("预算-事后还款头表","BUDGET_AFTER_REPAYMENT"),
    BUDGET_AFTER_REPAYMENT_LINE("预算-事后还款行表","BUDGET_AFTER_REPAYMENT_LINE"),
    BUDGET_BASIC_BUDGET("基础预算表","BUDGET_BASIC_BUDGET"),
    BUDGET_PROJECT_BUDGET("项目预算表","BUDGET_PROJECT_BUDGET"),
    BUDGET_PROJECT("项目表","BUDGET_PROJECT"),
    BUDGET_PROJECT_JOIN_PERSON("项目参与人员表","BUDGET_PROJECT_JOIN_PERSON"),

    //预算调整
    BUDGET_PUBLIC_ADJUST("人员公用经费预算调整头表","BUDGET_PUBLIC_ADJUST"),
    BUDGET_PUBLIC_ADJUST_LINE("人员公用经费预算调整行表","BUDGET_PUBLIC_ADJUST_LINE"),
    BUDGET_DETAILING_ADJUST_HEAD("预算细化调整头表","BUDGET_DETAILING_ADJUST_HEAD"),
    BUDGET_DETAILING_ADJUST_LINE("预算细化调整行表","BUDGET_DETAILING_ADJUST_LINE"),
    EDU_FUNDS_APPLY_HEAD("教育经费细化申请单头表","EDU_FUNDS_APPLY_HEAD"),
    EDU_FUNDS_APPLY_LINE("教育经费细化申请单行表","EDU_FUNDS_APPLY_LINE"),

    //对公预付款
    ADP_ADVANCE_PAYMENT_INFO("合同预付款","ADP_ADVANCE_PAYMENT_INFO"),
    ADP_ADVANCE_PAYMENT_LINE("对公预付款行明细表","ADP_ADVANCE_PAYMENT_LINE"),
    ADP_CONTACT_DETAIL("对公预付款关联合同报账表","ADP_CONTACT_DETAIL"),
    ADP_VERIFICATION_DETAIL("对公预付款核销明细表","ADP_VERIFICATION_DETAIL"),

    //合同报账单
    CRB_CONTRACT_REIMBURSE_BILL("合同报账单","CRB_CONTRACT_REIMBURSE_BILL"),

    //差旅申请单
    TAS_TRAVLE_APPLY_INFO("差旅申请单","TAS_TRAVLE_APPLY_INFO"),
    TAS_COST_INFORMATION_LINE("差旅申请费用明细表","TAS_COST_INFORMATION_LINE"),
    TAS_LEAVEA_BJ_INFORMATION("差旅申请离京信息表","TAS_LEAVEA_BJ_INFORMATION"),

    //差旅报账
    TAS_TRAVEL_REIMBURSE("差旅报账单","TAS_TRAVEL_REIMBURSE"),
    TAS_TRAVEL_LINE("差旅报账单明细表","TAS_TRAVEL_LINE"),

    //收入上缴单
    ITO_INCOME_TURNED_OVER("收入上缴单","ITO_INCOME_TURNED_OVER"),
    ITO_DETAILS_OF_FUNDS("收入上缴单款项明细表","ITO_DETAILS_OF_FUNDS"),

    //付款单
    PY_PAMENT_ORDER_INFO("付款单","PY_PAMENT_ORDER_INFO"),
    PUBLIC("对公付款","PUBLIC"),
    PRIVATE("对私付款","PRIVATE"),
    BUSINESS_CARD("公务卡","BUSINESS_CARD"),

   //工作流
    BPM_PROCESS("流程实例与单据关联表","BPM_PROCESS"),
    BPM_PROCESS_DEF("流程定义与单据类型关联表","BPM_PROCESS_DEF"),
    BPM_EVENT_DEF("事件定义表","BPM_EVENT_DEF"),


    //项目款项 非项目款项
    PPC_CONTRACT_INFORMATION("合同信息表","PPC_CONTRACT_INFORMATION"),
    PPC_NO_PROJECT_CONFIRMATION("非项目款项头表","PPC_NO_PROJECT_CONFIRMATION"),
    PPC_PROJECT_BILING_INFO("开票信息","PPC_PROJECT_BILING_INFO"),
    PPC_PROJECT_CONFIRMATION("款项确认单","PPC_PROJECT_CONFIRMATION"),
    PPC_PROJECT_PAYMENT_LINE_DETAI("项目款项确认单款项信息行表","PPC_PROJECT_PAYMENT_LINE_DETAI"),
    PPC_PROJECT_RECIEVE_DETAIL("项目款项收款明细","PPC_PROJECT_RECIEVE_DETAIL"),


    //收款单
    REP_RECIEVE_CLAIM_AREA("收款单认领范围","REP_RECIEVE_CLAIM_AREA"),
    REP_RECIEVE_INCOME_MSG("收款单收入信息","REP_RECIEVE_INCOME_MSG"),
    REP_RECIEVE_LINE_MSG("收款单收款信息行表","REP_RECIEVE_LINE_MSG"),
    REP_RECIEVE_PAYMENT("收款单","REP_RECIEVE_PAYMENT"),


    //附件类型定义
    BASE_FILE_DEF("附件类型定义头","BASE_FILE_DEF"),
    BASE_FILE_DEF_LINE("附件类型定义行","BASE_FILE_DEF_LINE"),


    //手工录入凭证
    AV_MANUAL_VOUCHER_HEAD("手工录入凭证","AV_MANUAL_VOUCHER_HEAD"),


    //绩效自评
    PER_SELF_ASSESSMENT("项目绩效自评","PER_SELF_ASSESSMENT"),

    //还款单
    POI_PEPAYMENT_ORDER_INFO("还款单","POI_PEPAYMENT_ORDER_INFO"),

    //报表
    REPORT_INCOME_EXPENSES_SUMMARY("部门收支总表","REPORT_INCOME_EXPENSES_SUMMARY"),
    REPORT_DEPT_EXPENSES_SUMMARY("部门支出总表","REPORT_DEPT_EXPENSES_SUMMARY"),
    REPORT_DEPT_BUDGET_DO_STATIS("院校部门预算执行进度统计表","REPORT_DEPT_BUDGET_DO_STATIS"),
    REPORT_EDU_DO_STATIS("教育经费预算执行情况统计表","REPORT_EDU_DO_STATIS"),
    REPORT_FIN_ALLOC_IE_SUM("财政拨款收支总表","REPORT_FIN_ALLOC_IE_SUM"),

    REPORT_TOTAL_QUERY("报表查询","REPORT_TOTAL_QUERY"),
    REPORT_MERGE_RELATION("报表合并查询","REPORT_MERGE_RELATION"),
    ;


    /**
     * 错误类型码
     */
    private String name;
    /**
     * 错误类型描述信息
     */
    private String value;

    FsscTableNameEums(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static FsscTableNameEums getByValue(String value){
        for(FsscTableNameEums tableNameEums:FsscTableNameEums.values()){
            if(tableNameEums.value.equals(value)){
                return tableNameEums;
            }
        }
        return null;
    }
}
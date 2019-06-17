package com.deloitte.services.fssc.eums;

import com.deloitte.platform.common.core.common.IErrorType;
import lombok.Getter;

@Getter
public enum FsscErrorType implements IErrorType {
    // 基础异常
    SAVE_FAIL("8403001","保存失败!"),
    ADD_FAIL("8403002","新增失败!"),
    NOT_FIND_DATA("8403003","数据不存在！"),
    CODE_REPEAT("8403004","编码已经被使用,请使用其他编码!"),
    IN_PARAM_CAN_NOT_EMPTY("8403005","存在必输参数未输入!"),
    REFERENCES_BY_OTHER_FOR_DELETE("8403006","该数据已被占用，不可删除!"),
    REFERENCES_BY_OTHER_FOR_EDIT("8403007","该数据已被占用，不可编辑!"),
    REFERENCES_BY_OTHER_FOR_INVALID("8403008","该数据被占用，不可操作!"),
    REFERENCES_BY_OTHER_FOR_PARENT("8403009","该数据被子值占用，不可删除!"),
    REFERENCES_BY_FOR_INVALID("8403019","该数据被子值占用，不可失效!"),
    CREATE_TIME_NOT_EXIST("8403010","创建时间不存在!"),
    GET_USER_NOT_EXIST("8403011","获取用户信息失败!"),
    GET_DEPT_NOT_EXIST("8403012","获取单位信息失败!"),
    GET_ORG_NOT_EXIST("8403013","获取部门信息失败!"),
    CANNOT_INVALID("8403014","该状态的行数据不可失效!"),
    CANNOT_REPEAT_VALID("8403015","该状态的数据不可重新启用!"),
    PARAM_CANT_BE_NULL("8403016","参数不能为空!"),
    PARAM_ERROR("8403017","参数错误!"),
    QRCODE_ERROR("8403018","二维码生成失败!"),
    GET_ROLE_NOT_EXIST("8403019","获取角色信息失败!"),
    GET_ALL_ORG_NOT_FAIL("8403020","获取全量部门信息失败!"),
    USER_NO_AUTHORIZATION_FOR_FSSC("8403021","没有财务资源管理平台的功能权限"),
    USER_NO_ROLE_FOR_FSSC("8403022","没有财务资源管理平台的角色信息!"),
    DEPT_CODE_CANTBE_NUMM("8403023","部门CODE不能为空!"),
    LEADER_EMP_NO_NOT_NULL("8403024","领导人工号为空!"),
    LEADER_EMP_NO_NOT_NULL_2("8403025","查询领导人失败,请配置各部门领导人!"),
    CHUNA_BUCUNZAI("8403026","查询出纳失败!"),
    CHUNA_BUCUNZAI_CAIWU("8403027","查询财务失败!"),
    PROJECT_LEADER_IS_NULL("8403028","项目负责人不能为空!"),
    DATA_EXISTS_ADD_FAIL("8403029","新增数据失败,数据已存在!"),

    //单位信息维护的异常
    ONLY_SUBMIT_NEW("8403500","只能提交新建状态的记录!"),
    ONLY_SUBMIT_UNVALID("8403501","只能失效有效状态的记录!"),
    ONLY_RETURN_NEW("8403502","只能撤回提交状态的记录!"),
    ONLY_RESET_UNVALID("8403503","只能重新启用失效状态的记录!"),
    ON_SUBMIT_REGIN("8403503","不能新增值代码相同的数据!"),

    //审核修改状态的异常
    NO_SUCH_METHOD_EXCEPTION("8403504","审核时没有此方法！"),
    INVOCATION_TARGET_EXCEPTION("8403505","执行方法报错！"),
    ILLEGAL_ACCESS_EXCEPTION("8403506","执行方法时类型错误！"),

    //收入、支出大类定义的异常
    PARENT_CODE_NOT_EMPTY("8403600","父类编码不能为空!"),
    CANNOT_ALLOCATION_NEW_UNIT("8403601","所选单位都已分配,不能重复分配!"),
    PARENT_NOT_FIND("8403605","大类不存在!"),
    REFERENCES_BY_SUB_TYPE("8403606","已被小类关联，不可删除!"),
    INCOME_MAIN_TYPE_INVALID("8403607","收入大类未启用,,请选择已启用的收入大类!"),
    EXPENSE_MAIN_TYPE_INVALID("8403608","支出大类未启用,,请选择已启用的支出大类!"),
    EXPENSE_MAIN_TYPE_VALID("8403609","已被小类关联，不可失效!"),
    INCOME_MAIN_TYPE_VALID("8403609","已被启用状态的小类关联，不可失效!"),
    BORROW_MONERY_INFO("8403610","已被借款单关联，不可删除!"),
    ADVANCE_PAYMENT_INFO("8403611","已被对公预付款单关联，不可删除!"),
    CONTRACT_REIMBURSE_BILL("8403612","已被合同报账单关联，不可删除!"),
    GENERAL_EXPENSE_INFO("8403619","已被通用报账单关联，不可删除!"),
    LABOR_COST_INFO("8403622","已被劳务费报账单关联，不可删除!"),
    TRAVLE_APPLY_INFO("8403627","已被差旅申请单关联，不可删除!"),
    TRAVEL_REIMBURSE("8403632","已被差旅报账单关联，不可删除!"),
    EXISTS_INCOME_SAME_CODE_FOR_VALID("8403633","已存在相同编码且启用状态的款项大类,不能启用!"),
    EXISTS_INCOME_SUB_SAME_CODE_FOR_VALID("8403634","已存在相同编码且启用状态的款项小类,不能启用!"),
    EXISTS_INCOME_SAME_CODE_FOR_ADD("8403633","已存在相同编码且启用状态的款项大类!"),
    EXISTS_INCOME_SUB_SAME_CODE_FOR_ADD("8403634","已存在相同编码且启用状态的款项小类!"),

    BORROW_MONERY_INFO_VALID("8403613","已被借款单关联，不可失效!"),
    ADVANCE_PAYMENT_INFO_VALID("8403614","已被对公预付款单关联，不可失效!"),
    CONTRACT_REIMBURSE_BILL_VALID("8403615","已被合同报账单关联，不可失效!"),
    GENERAL_EXPENSE_VALID("8403623","已被通用报账单关联，不可失效!"),
    LABOR_COST_VALID("8403624","已被劳务费报账单关联，不可失效!"),
    TRAVLE_APPLY_VALID("8403629","已被差旅申请单关联，不可失效!"),
    TRAVEL_REIMBURSE_VALID("8403633","已被差旅报账单关联，不可失效!"),

    BORROW_MONERY_INFO_UPDATE("8403616","已被借款单关联，不可修改!"),
    ADVANCE_PAYMENT_INFO_UPDATE("8403617","已被对公预付款单关联，不可修改!"),
    CONTRACT_REIMBURSE_BILL_UPDATE("8403618","已被合同报账单关联，不可修改!"),
    GENERAL_EXPENSE_UPDATE("8403625","已被通用报账单关联，不可修改!"),
    LABOR_COST_UPDATE("8403626","已被劳务费报账单关联，不可修改!"),
    TRAVLE_APPLY_UPDATE("8403628","已被差旅申请单关联，不可修改!"),
    TRAVEL_REIMBURSE_UPDATE("8403634","已被差旅报账单关联，不可修改!"),

    //上传下载定义的异常
    EXPORT_DATA_SOURCE_EMPTY("8403620","数据源为空,不能导出!"),
    IMPORT_DATA_SOURCE_EMPTY("8403621","数据源为空,不能导入!"),
    YOU_NOT_UPLOAD_FILE("8403621111","未查询到附件!"),

    //预算科目定义的异常
    EXPENSE_MAIN_TYPE_RELATED("8403630","支出大类已关联预算科目,不能再被新的预算科目关联!"),
    REFERENCES_BY_EXPENSE_MAIN_TYPE("8403631","已关联支出大类,不可直接删除!"),
    REFERENCES_BY_PROJECT_BUDGET("8403632","已关联项目预算,不可直接删除!"),
    REFERENCES_BY_BASIC_BUDGET("8403633","已关联基础预算,不可直接删除!"),
    REFERENCES_BY_PUBLIC_ADJUST("8403634","已关联公用经费预算调整申请,不可直接删除!"),
    REFERENCES_BY_DETAILING_ADJUST("8403635","已关联公用经费预算调整申请,不可直接删除!"),

    //预算
    BUDGET_ACCOUNT_NOT_FIND("8403640","预算科目不存在"),
    EXPENSE_MAIN_TYPE_NOT_FIND("8403641","支出大类不存在!"),
    INCOME_MAIN_TYPE_NOT_FIND("8403642","收入大类不存在!"),
    BUDGET_ACCOUNT_NOT_FIND_OR_INVALID("8403643","预算科目不存在,或失效"),
    BUDGET_MORE_THAN_80_PERCENT("8403644","您当前的预算超过可用预算金额的80%，是否进行提交"),
    BUDGET_MORE_THAN_100_PERCENT("8403645","您当前的预算已超过可用预算金额的100%，请联系预算管理员"),
    DOCUMENT_ID_CANNOT_EMPTY("8403646","单据ID不能为空!"),
    DOCUMENT_TABLE_CANNOT_EMPTY("8403647","单据主表表名不能为空!"),
    DOCUMENT_DATA_IS_EMPTY("8403647","单据数据不存在,或已被删除!"),
    DOCUMENT_DATA_LINE_IS_EMPTY("84036499","单据关联数据行不存在,或已被删除!"),
    BASIC_BUDGET_NOT_FIND("8403648","基础预算不存在"),
    PROJECT_BUDGET_NOT_FIND("8403649","项目预算不存在"),
    DOCUMENT_APPLY_FOR_IS_EMPTY("8403650","申请单不存在"),
    BUDGET_APPLY_FOR_IS_EMPTY("8403651","申请单预算信息不存在,请检查是否关联未审批的申请单"),
    BUDGET_BORROW_MORE_THAN_APPLY("8403652","借款金额不能超过事前申请金额"),
    BUDGET_ADVANCE_PAY_MORE_THAN_APPLY("8403653","预付款金额不能超过事前申请金额"),
    BUDGET_EXPENSE_MORE_THAN_APPLY("8403655","报销金额不能超过事前申请金额"),
    BORROW_MAIN_TYPE_NOT_MATCHING_APPLY("8403656","借款与事前申请支出大类不一致"),
    ADVANCE_PAY_MAIN_TYPE_NOT_MATCHING_APPLY("8403657","预付款与事前申请支出大类不一致"),
    EXPENSE_MAIN_TYPE_NOT_MATCHING_APPLY("8403658","报销单与事前申请支出大类不一致"),
    BORROW_PROJECT_NOT_MATCHING_APPLY("8403659","借款与事前申请关联项目不一致"),
    ADVANCE_PAY_PROJECT_NOT_MATCHING_APPLY("8403660","预付款与事前申请关联项目不一致"),
    EXPENSE_PROJECT_NOT_MATCHING_APPLY("8403661","报销单与事前申请关联项目不一致"),
    BORROW_LINE_DATA_NOT_FOUND("8403662","借款单行数据不存在"),
    ADVANCE_PAY_LINE_DATA_NOT_FOUND("8403663","预付款单行数据不存在"),
    BUDGET_TOTAL_SUM_NOT_FOUND("8403664","预算/年合计数据不存在"),
    BUDGET_BORROW_MORE_THAN_APPLY2("8403665","借款金额不能超过事前申请预算额度"),
    BUDGET_ADVANCE_PAY_MORE_THAN_APPLY2("8403666","预付款金额不能超过事前申请预算额度"),
    BUDGET_APPLY_FOR_IS_INVALID("8403667","申请单预算信息已失效,请检查是否关联已撤销的申请单"),
    BUDGET_BORROW_NOT_FOUND("8403668","借款单预算数据不存在"),
    BUDGET_PREPAY_NOT_FOUND("8403669","预付单预算数据不存在"),
    BUDGET_AFTER_EXPENSE_NOT_FOUND("8403670","报账单预算数据不存在"),
    LINE_VER_AMOUNT_MORE_THAN_BUDGET("8403671","行核销金额超过预算金额"),
    BUDGET_ACCOUNT_ONLY_RELATE_ONE("8403672","一个预算科目只能关联一个支出大类!"),
    BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR("8403673","报账金额不能超过事前申请金额!"),
    BUDGET_IS_EXISTS("8403674","已扣除预算,单据状态异常,或已提交过!"),
    BUDGET_AMOUNT_MORE_THAN_10000000000000("8403675","预算金额不能超过千万亿!"),
    GET_ALL_PROJECT_BUDGET_FAIL("8403676","获取全量项目预算科目失败"),
    GET_ALL_PROJECT_BUDGET_NO_DATA("8403677","获取全量项目预算科目，没有查询到数据"),
    ACTUAL_PLAY_AMOUNT_IS_EMPTY("8403678","预付款行实际支付金额不能为空"),
    LINE_NUMBER_IS_EMPTY("8403679","行号不能为空"),
    BORROW_LINE_AMOUNT_IS_EMPTY("8403680","借款行金额不能为空"),
    BUDGET_EXPENSE_AMOUNT_MORE_THAN_APPLY_FOR_USABLE("8403681","报账金额不能超过事前申请可用金额,如果关联同一差旅申请的借款单,请检查是否全部核销!"),
    VERIFICATION_AMOUNT_IS_EMPTY("8403682","本次核销金额不能为空"),
    REPAY_LINE_NOT_FIND("8403683","没有查询到还款单行信息"),
    REPAY_AMOUNT_IS_EMPTY("8403684","本次还款金额不能为空"),

    //预算调整
    ADJUSTED_USABLE_AMOUNT_LESS_THAN_ZERO("8403670001","调整后可用预算金额不能小于0"),
    ADJUSTED_EXISTS_COMMIT_DOCUMENT("8403670002","已有调整单据在流程中,不能继续提交"),
    ADJUSTED_LINE_NOT_FIND("8403670003","预算科目的预算调整数据不能为空"),
    GET_FICTION_ORG_FAIL("8403670004","获取虚拟部门失败"),
    CASH_POOLING_NOT_FIND("8403670005","没有查询资金池信息"),
    CASH_POOLING_AMOUNT_NO_ENOUGH("8403670006","资金池金额不足"),
    ADJUSTED_ALLOCATION_AMOUNT_LESS_THAN_ZERO("8403670007","分配金额不能小于0"),
    ADJUSTED_ALLOCATION_TOTAL_LESS_THAN_ZERO("8403670008","分配总额不能小于0"),
    ADJUSTED_ALLOCATION_TOTAL_NOT_MATCH_APPLY("8403670009","分配总额与各个科目的分配金额总和不一致"),
    ADJUSTED_EDU_APPLY_LINE_NOT_FIND("8403670010","教育经费细化调整行数据不存在"),
    ADJUSTED_EDU_APPLY_LINE_NOT_MATCH("8403670011","分配总额与教育经费细化调整申请单中行申请金额不一致"),
    ADJUSTED_EDU_APPLY_HEAD_NOT_APPROVED("8403670012","教育经费细化调整申请单不是已审批状态,不能关联"),

    //单据类型定义
    FUNCTION_MODULE_RELATED("8403680","功能模块已被关联,请选择其他功能模块!"),
    CANNOT_ALLOCATION_NEW_EXPENSE("8403681","所选支出大类都已分配,不能重复分配!"),
    CANNOT_ALLOCATION_NEW_INCOME("8403682","所选收入大类都已分配,不能重复分配!"),
    DOCUMENT_NOT_FIND_OR_INVALID("8403683","单据类型未定义,或已失效!"),
    BUDGET_CONTROL_FLAG_INVALID("8403684","预算控制未开启!"),
    BUDGET_REMAIN_FLAG_INVALID("8403685","预算保留未开启!"),
    BUDGET_REMAIN_MUST_VALID("8403686","如果需要预算控制,必须选择预算保留!"),
    RELATED_EXPENSE_FOR_DELETE("8403687","已经关联支出大类,不能被删除!"),
    RELATED_INCOME_FOR_DELETE("8403688","已经关联收入大类,不能被删除!"),
    PAY_WAY_CODE_IS_EMPTY("8403689","付款方式编码不能为空!"),
    PAY_WAY_NAME_IS_EMPTY("8403690","付款方式名称不能为空!"),
    EXISTS_SAME_CODE_AND_VALID("8403691","已存在相同编码且启用状态的单据类型定义,不能启用!"),
    EXISTS_SAME_FUNCTION_AND_VALID("8403692","已存在相同功能模块且启用状态的单据类型定义,不能启用!"),
    REFERENCES_EXPENSE_MAIN_TYPE("8403693","已关联支出大类,不能分配收入大类"),
    REFERENCES_INCOME_MAIN_TYPE("8403694","已关联收入大类,不能分配支出大类"),
    RELATED_PERSON_TYPE_FOR_DELETE("8403695","已经关联收款人员类型,不能被删除!"),
    CANNOT_ALLOCATION_NEW_PERSON_TYPE("8403682","所选人员类型都已分配,不能重复分配!"),

    //绩效
    PERFORMANCE_DATA_REPEAT("8403700","绩效指标重复"),

    //数据库异常
    SQL_EXCEPTION("840310000","数据库异常!"),
    DATA_IS_NOT_LATEST("840310001","保存失败,数据可能已经被修改,请重试!"),
    ONLY_DELETE_NEW("840310002","只能删除新建或失效的数据!"),
    NOT_SAVE_NOT_DELETE("840310003","数据未保存不能删除!"),
    SEQUENCE_ERROR("840310004","序列生成错误!"),
    DATA_DUPLICATE("840310005","数据存在重复字段,请检查！"),

    //文件类异常
    FILE_IS_NULL("840310004","上传文件不存在"),
    FILE_NOT_ALL_SUCCESS("840310005","上传文件部分成功"),
    FILE_UPLOAD_FIAL("840310006","上传文件失败,请稍后再试!"),
    FILE_FORMAT_NOT_EXCEL("840310007","文件格式错误,只支持Excel2003、2007及以后版本的文件"),
    FILE_DELETE_FIAL("840310008","文件删除失败!"),
    MAIN_TYPE_CANT_ALL_BE_NULL("840310009","支出大类，收入大类不能都为空!"),
    ONLY_DELETE_UNVALID_DATA("8403100010","只能删除失效的数据!"),
    FILE_DEF_NOT_FINED("8403100011","未定义文件类型定义,或已失效!"),

    //通用报账单
    GE_LINE_IS_NOT_EMPTY("84036001","行明细不能为空!"),
    VERFICATION_AMOUNT_CANT_GE_LINE("840360012","相同小类的行,本次核销金额不能大于发票金额"),
    VERFICATION_AMOUNT_CANT_GE_BPMAMOUNT("840360013","本次核销金额不能大于借款或预付款金额"),
    ONLY_CHARGE_IN_ACCOUNT("840360014","只能冲销已入账的单据!"),
    MUST_ORDER_CHARGE("","该单据关联的其他报账单未冲销!请按照顺序冲销!"),
    JIAOYI_TIME_MUST_BE_BEFORE_NOW("840333014","交易时间应在单据创建时间之前!"),
    YOU_HAVE_MAINTYPE_BORROW_IS("8403330142910","所选支出大类存在未核销的借款单，请确认本报账单是否关联借款单!"),
    YOU_HAVE_MAINTYPE_BORROW_IS_CONTRACT("8403330142911","所选支出大类存在未核销的借款单，请确认本报账单是否关联借款单!"),
    //对公预付款单
    ADVANCE_PLAYMENT_NO_EMPTY("84036002","请先保存数据"),
    SUBMIT_NEW_REJECTED("84036003","只能提交新建、已驳回、已撤回状态的数据"),
    LINE_DETAIL_SUBTYPE_CANT_REPEAT("84036004","行明细支出小类不能重复!"),

    //差旅申请单
    ONLY_REVOKE_IN_ACCOUNT("84036005","只能撤销已审批的申请单!"),
    TRAVLE_APPLY_NO_EMPTY("84036006","请先保存数据"),
    TRAVLE_APPLY_TIME("84036007","不允许跨越淡季和旺季"),
    TRAVLE_STRANDS_TIME("84036008","不能选择多个月份的差旅标准"),

    // 劳务费
    HAS_DUDETED_TAX("8403220001","该单据已经计税,不要重复计税!"),
    SAMEPERSON_CANT_SAMESUBTYPE("84032200022","同一人不能选择相同支出小类!"),
    //工作流异常
    DOCUMENT_INFO_IS_NULL("840390001","单据ID,单据类型,流程类型不能为空!"),
    PROCESS_DEF_IS_NULL("840390002","流程定义不存在,请联系管理员配置流程定义!"),
    PROCESS_KEY_IS_NULL("840390003","流程定义KEY不能为空!"),
    PROCESS_START_FAIL("840390004","调用流程引擎失败,请稍后再试!"),
    NEXT_APPROVER_FIND_FIAL("840390005","调用流程引擎失败,寻找下一节点审批人失败,请稍后再试!"),
    NEXT_APPROVER_IS_NULL("840390006","下一节点审批人为空,请先配置下一节点审批人!"),
    APPROVE_PASS_FIALD("840390007","调用流程引擎失败,请稍后再试!"),
    GET_TASK_FAILD("840390008","调用流程引擎失败!请稍后再试!"),
    FIND_HISTORY_FAILD("840390009","调用流程引擎失败!查询审批历史失败!请稍后再试!"),
    THIS_PROCESS_IS_NOT_FIND("840390010","流程不存在,该单据可能还没提交流程!"),
    DOCUMENT_NOT_FIND("840390011","单据不存在!"),
    DOCUMENT_IS_ALREADLY_SUBMIT("840390012","该单据已经提交了流程!"),
    ONLY_DELETE_NEW_DOCUMENT("840390013","只能删除新建，已撤回，已驳回状态的单据!"),
    DELETE_NEW_REJECTED_DOCUMENT("840390015","只能删除新建或已驳回状态的单据!"),
    ID_CANT_BE_NULL("840390014","ID不能为空!"),
    DOCUMENT_TYPE_CODE_CANTBE_NULL("840390016","单据类型编码不能为空!"),
    REMARK_IS_TO_LONG("84039001622","备注不能超过255个字符!"),
    TAKS_ID_CANT_BE_NULL("8403900162233","任务ID不能为空！"),
    FIND_NEXT_TASK_FAILED("8403900162234","查询下一节点失败!"),
    FIND_NEXT_TASK_FAILED_NULL("8403900162234","未查询到下一审批节点!"),
    FIND_NEXT_TASK_FAILED_IS_NULL("8403900162299","查询下一节点失败,请确认流程配置是否正确!"),
    FIND_NODE_FIAILD("84039001622991","未查询到匹配节点,请检查流程图配置!"),
    //会计引擎
    AV_MANUALVOUCHERhEAD_IS_NULL("84028011","凭证行信息不能为空！"),
    AV_MANUALVOUCHERhEAD_IS_ZERO("84028012","凭证行信息不能借贷为都0！"),
    AV_MANUALVOUCHERhEAD_IS_GT_ZERO("84028013","凭证行信息不能借贷为都大于0！"),
    AV_MANUALVOUCHERhEAD_IS_JD_NO_EQUAL("84028014","凭证行信息借贷要相等！"),
    AV_AUTO_LOGIC_HEAD_ERROR("84028015","请确认逻辑凭证头配置，没有配置完全"),
    AV_AUTO_LOGIC_LINE_ERROR("84028016","请确认逻辑凭证行配置，没有配置完全"),
    AV_AUTO_LOGIC_NOVALUE_ERROR("84028016","请确认逻辑凭证行配置，没有取到值"),
    AV_AUTO_LOGIC_OVERVALUE_ERROR("84028022","请确认逻辑凭证行配置，通过函数取值超过1条数据"),
    AV_AUTO_LOGIC_NOLEDGER_ERROR("84028018","请确认同步单位和账薄关联，没有取到值"),
    AV_VOUCHERHEADER_IS_ERROR("84028017","会计凭证入账失败"),
    AV_VOUCHERHEADER_PARSE_IS_ERROR("84028019","请确认逻辑配置"),
    AV_VOUCHERHEADER_PARSE_NOT_IS_ERROR("84028020","请确认逻辑配置,没有选择行映射，这种情况配置字段信息需要和头表信息一直"),
    AV_SEND_TO_EBS_ERROR("84028036","发送给ebs会计凭证出错"),
    AV_MANUAL_NOT_CARE("84028039","没有预制凭证数据"),
    AV_MANUAL_HAS_CARRAY("84028040","该单据已经被结转不能被冲销了！"),
    AV_MANUALVOUCHER_IS_ERROR("84028000","凭证出错！"),

    //借款
    REPAYMENT_TIME_MUST_BE_AFTER_NOW("840333014","预计还款时间应在单据创建时间之后!"),
    SALARY_BUSINESS_MUST_BE_EQ("840333015","报账金额和工资卡或工务卡金额不相等!"),
    BUSINESS_BORROW_AMOUNT_MUST_TH_TRAMOUNT("840333016","公务卡交易金额必须大于等于还款金额!"),
    FILEIDS_MUSTBE_LE_ATTCHFILES("8403330162","支持性附件数量不能小于真实附件清单数量!"),

    SALARY_AMOUNT_NOT_EQ("84033301633","工资卡金额不相等!"),
    BUSINESS_AMOUNT_NOT_EQ("840333016244","公务卡金额不相等!"),
    PUBLIC_AMOUNT_NOT_EQ("840333016211","对公付款金额不相等!"),


    VER_SALARY_AMOUNT_NOT_EQ("840333016331","核销工资卡金额不相等!"),
    VER_BUSINESS_AMOUNT_NOT_EQ("8403330162441","核销公务卡金额不相等!"),
    VER_PUBLIC_AMOUNT_NOT_EQ("8403330162111","核销对公付款金额不相等!"),


    //对公预付款
    ADVANCE_PAYMENT_INFO_MUST_EQ_GEEXPENSE("840333017","付款金额必须等于预付金额!"),
    ADVANCE_PAYMENT_INFO_MUST_EQ__CONTRACT("840333018","付款金额必须等于约定付款金额!"),
    ADVANCE_PAYMENT_INFO_BMBORROW_BANK("8403330204","公务卡合计金额等于合同详情付款方式为公务卡的金额!"),
    ADVANCE_PAYMENT_INFO_GEEXPENSE("8403330205","对公付款单合计金额等于合同详情付款方式为付款方式为对公支付，限额支票的金额!"),


    //差旅报账不同支付类型的验证
    FIX_DOCUMENT_MONERY_RIGHT ("840333019","请确认单据各明细页面中金额合计是否准确！"),
    PUBLIC_PAYMENT_DOCUMENT_MONERY_RIGHT("840333020","请确认对公单据各明细页面中金额合计是否准确！"),
    CHECK_DOCUMENT_MONERY_RIGHT("8403330201","请确认支票单据各明细页面中金额合计是否准确！"),
    TRANSFER_DOCUMENT_MONERY_RIGHT("8403330202","请确认转账各明细页面中金额合计是否准确！"),

    //不满足生成付款信息
    NOT_GENERATE_BILL_PAYMENT("8403330203","只能生成已入账和未支付金额大于0的单据"),
    DOCUENT_NUM_NOT_EQ("84033302033","待支付单据编号和行上的单据编号必须一致"),


    //项目
    FSSC_CODE_CANT_MODIFY("8403333221111","财务编码保存之后不能修改!"),
    PROJECT_NOT_FIND("840333322111122","项目不存在,无法查询支出大类"),
    HAD_CONNECTED_CAN_ROLLBACK("840333322111123","该收款单已被款项确认单关联，不能冲销、撤回或驳回!"),
    //付款单重复提醒
    DOCUMENT_READY_PAYMENT("8403333221112","该单据已生成付款单!"),
    DOCUMENT_ALL_SELECT("8403333221114","请选择所有相关单据!"),

    //差旅报账重复数据提醒
    TRAVEL_READY_PAYMENT("8403333221113","该数据地点，区域代码，旺季月份重复!"),
    TRAVEL_UNCONTAIN_DICVALUE("8403333221115","该地点编码在数据字典值集不存在!"),


    RECIEVE_LINE_MUST_BE_NOT_NULL("840333322111311","收款单行ID不能为空!"),
    PLEASE_CHARGE_AGAINST_PAYORREP("840333322111312","请先冲销关联的还款单或付款单!"),
    ;

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    FsscErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}

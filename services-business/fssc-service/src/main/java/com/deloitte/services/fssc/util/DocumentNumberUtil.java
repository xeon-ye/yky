package com.deloitte.services.fssc.util;

import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.unit.mapper.UnitInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentNumberUtil {

    public static final String NUMBER = "_N";

    private static UnitInfoMapper unitInfoMapper;

    @Autowired
    public void setUnitInfoMapper(UnitInfoMapper unitInfoMapper) {
        this.unitInfoMapper = unitInfoMapper;
    }


    public static Map<String, String> KEYS = new HashMap<>();

    public static Map<String, String> BUDGETCODES = new HashMap<>();

    static {
        KEYS.put(FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue(), "JK");
        KEYS.put(FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue(), "YF");
        KEYS.put(FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue(), "SQ");
        KEYS.put(FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue(), "CL");
        KEYS.put(FsscTableNameEums.GE_GENERAL_EXPENSE.getValue(), "TY");
        KEYS.put(FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue(), "HT");
        KEYS.put(FsscTableNameEums.LC_LABOR_COST.getValue(), "LW");
        KEYS.put(FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue(), "SK");
        KEYS.put(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue(), "FK");
        KEYS.put(FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue(), "KX");
        KEYS.put(FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue(), "SJ");
        KEYS.put(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue(), "ZZ");
        KEYS.put(FsscTableNameEums.EDU_FUNDS_APPLY_HEAD.getValue(), "ED");
        KEYS.put(FsscTableNameEums.BUDGET_PUBLIC_ADJUST.getValue(), "PA");
        KEYS.put(FsscTableNameEums.BUDGET_DETAILING_ADJUST_HEAD.getValue(), "DA");
        KEYS.put(FsscTableNameEums.PER_SELF_ASSESSMENT.getValue(), "PER");
        KEYS.put(FsscTableNameEums.POI_PEPAYMENT_ORDER_INFO.getValue(), "HK");
        BUDGETCODES.put("1001", "801");
        BUDGETCODES.put("1002", "802");
        BUDGETCODES.put("1003", "803");
        BUDGETCODES.put("1004", "804");
        BUDGETCODES.put("1005", "805");
        BUDGETCODES.put("1006", "806");
        BUDGETCODES.put("1007", "807");
        BUDGETCODES.put("1008", "808");
        BUDGETCODES.put("1009", "809");

    }


    /**
     * 生成单据编号
     *
     * @param documentType
     * @return
     */
    public static String generateDocumentNum(String budgetCode, String documentType) {
        AssertUtils.asserts(StringUtil.isNotEmpty(documentType) &&
                StringUtil.isNotEmpty(budgetCode), FsscErrorType.DOCUMENT_TYPE_CODE_CANTBE_NULL);
        StringBuilder builder = new StringBuilder(budgetCode);
        String yearMonth = DateUtil.getCurrentMonthStr();
        builder.append(KEYS.get(documentType)).append(yearMonth);
        String name = documentType + NUMBER;
        //流水号
        Long sequence = getSequenceNum(name);
        String next = StringUtil.objectToString(sequence);
        int totalLength = 5;
        int zeroLength = totalLength - next.length();
        for (int i = 0; i < zeroLength; i++) {
            builder.append("0");
        }
        builder.append(sequence);

        return builder.toString();
    }


    /**
     * 生成bankCode
     *
     * @return
     */
    public static String generateBankCode() {
        StringBuilder builder = new StringBuilder();
        String name = "SEQUENCE_BANK_CODE";
        Long sequenceNum = getSequenceNum(name);
        String bankCode = StringUtil.objectToString(sequenceNum);
        int zero = 5 - bankCode.length();
        for (int i = 0; i < zero; i++) {
            builder.append("0");
        }
        builder.append(bankCode);
        return builder.toString();
    }


    private static Long getSequenceNum(String name) {
        Long sequence = null;
        try {
            sequence = unitInfoMapper.getSequence(name);
        } catch (BadSqlGrammarException e) {
            String message = e.getSQLException().getMessage();
            if (StringUtil.isNotEmpty(message) && message.contains("ORA-02289")) {
                unitInfoMapper.createSequenceName(name);
                sequence = unitInfoMapper.getSequence(name);
            } else {
                throw new FSSCException(FsscErrorType.SEQUENCE_ERROR);
            }
        }
        return sequence;
    }
}

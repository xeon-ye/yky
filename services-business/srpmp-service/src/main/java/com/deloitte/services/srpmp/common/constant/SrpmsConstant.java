package com.deloitte.services.srpmp.common.constant;

import java.io.File;

public interface SrpmsConstant {

    /**
     * 题录类型：1.	新获项目
     */
    public final String SRPMS_OUTLINE_TYPE_01 = "01";

    /**
     * 题录类型：2.	奖励
     */
    public final String SRPMS_OUTLINE_TYPE_02 = "02";

    /**
     * 题录类型：3.	成果鉴定
     */
    public final String SRPMS_OUTLINE_TYPE_03 = "03";

    /**
     * 题录类型：4.	成果转化
     */
    public final String SRPMS_OUTLINE_TYPE_04 = "04";

    /**
     * 题录类型：5.	专利
     */
    public final String SRPMS_OUTLINE_TYPE_05 = "05";

    /**
     * 题录类型：6.	新药器械
     */
    public final String SRPMS_OUTLINE_TYPE_06 = "06";

    /**
     * 题录类型：7.	国合项目
     */
    public final String SRPMS_OUTLINE_TYPE_07 = "07";

    /**
     * 题录类型：8.	论文
     */
    public final String SRPMS_OUTLINE_TYPE_08 = "08";

    /**
     * 题录类型：9.	科技著作
     */
    public final String SRPMS_OUTLINE_TYPE_09 = "09";

    /**
     * 题录类型：10.	学术交流
     */
    public final String SRPMS_OUTLINE_TYPE_10 = "10";

    /**
     * 题录类型：11.	其他
     */
    public final String SRPMS_OUTLINE_TYPE_11 = "11";

    /**
     * 题录报表模板
     */
    public final String TEMPLATE_NEW_TITLE_COUNT = "template_outline_nt_count";
    public final String TEMPLATE_NEW_TITLE_TOTAL_OUTLAY = "template_outline_nt_total_outlay";
    public final String TEMPLATE_NEW_TITLE_REC_OUTLAY = "template_outline_nt_rec_outlay";
    public final String TEMPLATE_NEW_TITLE_STATE = "template_outline_nt_state";
    public final String TEMPLATE_AWARD_COUNT = "template_outline_award";
    public final String TEMPLATE_PAPER_BOOK_COUNT = "template_outline_paper_book";
    public final String TEMPLATE_PATENT_MEDICAL_COUNT = "template_outline_patent_medical";

    /**
     * 报表类型
     */
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST = 1;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND = 2;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_THREE = 3;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_FOUR = 4;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_FIVE = 5;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_SIX = 6;
    public final int SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN = 7;

    public static final String SEPARATOR = File.separator;

    public static final String EXCEL = "excel";

    public final static String EXT_EXCEL_XLS = ".xls";

    public final static String EXT_EXCEL_XLSX = ".xlsx";

    public final static String EXT_PDF = ".pdf";

    public final static String EXT_WORD_DOCX = ".docx";


    public final static String PRO_CATEGORY = "PRO_CATEGORY";// 项目类别
    public final static String BE_CURRENT = "BE_CURRENT";// 创新工程通用指标类型
    public final static String SUBJECT_OPTIONS = "SUBJECT_OPTIONS";// 学科分类
    public final static String PRO_ENTER_TYPE = "PRO_ENTER_TYPE";// 参与程度
    public final static String OUTLINE_PRO_STAT = "OUTLINE_PRO_STAT";// 新获项目状态
    public final static String AWARD_CAT = "AWARD_CAT";// 奖项类别
    public final static String AWARD_NAME = "AWARD_NAME";// 奖项名称
    public final static String AWARD_LEVEL = "AWARD_LEVEL";// 奖项等级
    public final static String IDENTIFY_WAY = "IDENTIFY_WAY";// 鉴定/验收形式
    public final static String TRANS_WAY = "TRANS_WAY";// 技术成果转换形式
    public final static String TRANS_FEE_SOURCE = "TRANS_FEE_SOURCE";// 转化费来源
    public final static String PATENT_CAT = "PATENT_CAT";// 专利类别
    public final static String AUTHORIZED_FLAG = "AUTHORIZED_FLAG";// 专利是否授权
    public final static String REGION = "REGION";// 区域
    public final static String MEDICAL_CAT = "MEDICAL_CAT";// 类别
    public final static String MEDICAL_TYPE = "MEDICAL_TYPE";// 药品分类
    public final static String MEDICAL_LEVEL = "MEDICAL_LEVEL";// 药品等级
    public final static String COOPERATION_CAT = "COOPERATION_CAT";// 合作类别
    public final static String JOURNAL_PROPERTY = "JOURNAL_PROPERTY";// 期刊性质
    public final static String BOOK_LEVEL = "BOOK_LEVEL";// 科技著作级别
    public final static String PATENT_REGION = "PATENT_REGION";// 学术交流区域
    public final static String SPONSOR_FLAG = "SPONSOR_FLAG";// 主办参加
    public final static String SRPMS_BASE_CAT = "SRPMS_BASE_CAT";// 基地类别
    public final static String SRPMS_BASE_LEVEL = "SRPMS_BASE_LEVEL";// 基地级别
    public final static String PAPER_CAT = "PAPER_CAT";// 论文类别
    public final static String PRO_UPADATE_STATE = "PRO_UPADATE_STATE";// 项目变更状态
    public final static String PRO_UPADATE_TYPE = "PRO_UPADATE_TYPE";// 项目变更类型状态
    public final static String STATE_UPDATE_STATE = "STATE_UPDATE_STATE";// 状态变更状态
    public final static String RESULT_MANAGER_TYPE = "RESULT_MANAGER_TYPE";// 成果管理类型


    /**
     * 题录导出excel文件名
     */
    public final static String SRPMS_OUTLINE_NEW_TITLE_FILE_NAME = "题录-新获项目.xls";
    public final static String SRPMS_OUTLINE_NEW_TITLE_SHEET_NAME = "题录-新获项目";
    public final static String SRPMS_OUTLINE_AWARD_FILE_NAME = "题录-奖励.xls";
    public final static String SRPMS_OUTLINE_AWARD_SHEET_NAME = "题录-奖励";
    public final static String SRPMS_OUTLINE_IDENTIFY_FILE_NAME = "题录-成果鉴定.xls";
    public final static String SRPMS_OUTLINE_IDENTIFY_SHEET_NAME = "题录-成果鉴定";
    public final static String SRPMS_OUTLINE_TRANS_FILE_NAME = "题录-成果转化.xls";
    public final static String SRPMS_OUTLINE_TRANS_SHEET_NAME = "题录-成果转化";
    public final static String SRPMS_OUTLINE_PATENT_FILE_NAME = "题录-专利.xls";
    public final static String SRPMS_OUTLINE_PATENT_SHEET_NAME = "题录-专利";
    public final static String SRPMS_OUTLINE_MEDICAL_FILE_NAME = "题录-新药器械.xls";
    public final static String SRPMS_OUTLINE_MEDICAL_SHEET_NAME = "题录-新药器械";
    public final static String SRPMS_OUTLINE_COOPERATION_FILE_NAME = "题录-国合项目.xls";
    public final static String SRPMS_OUTLINE_COOPERATION_SHEET_NAME = "题录-国合项目";
    public final static String SRPMS_OUTLINE_PAPER_FILE_NAME = "题录-论文.xls";
    public final static String SRPMS_OUTLINE_PAPER_SHEET_NAME = "题录-论文";
    public final static String SRPMS_OUTLINE_BOOK_FILE_NAME = "题录-科技著作.xls";
    public final static String SRPMS_OUTLINE_BOOK_SHEET_NAME = "题录-科技著作";
    public final static String SRPMS_OUTLINE_ACA_EXC_FILE_NAME = "题录-学术交流.xls";
    public final static String SRPMS_OUTLINE_ACA_EXC_SHEET_NAME = "题录-学术交流";
    public final static String SRPMS_OUTLINE_OTHER_FILE_NAME = "题录-基地.xls";
    public final static String SRPMS_OUTLINE_OTHER_SHEET_NAME = "题录-基地";

    /**
     * 题录类型文件表头：1.	新获项目
     */
    public final String[] SRPMS_OUTLINE_NEW_TITLE_FILE_HEADER = {"单位名称","年","月","项目编码","项目名称","项目类别","来源机构","参与程度",
            "项目负责人","项目开始时间","项目结束时间","项目状态","项目总经费","项目到位经费","备注"};

    /**
     * 题录类型文件表头：2.	奖励
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_AWARD = {"单位名称","年","月","获奖成果","获奖类别","完成单位","主要完成人","奖项名称",
            "奖项等级","项目编码","项目名称"};

    /**
     * 题录类型文件表头：3.	成果转化
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_RESULT_TRANS = {"单位名称","年","月","成果名称","负责人","技术转化形式","合同签订年份","合同金额(万元)",
            "本年到位金额(万元)","转化费来源","项目编码","项目名称","备注"};

    /**
     * 题录类型文件表头：4.	成果鉴定
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_RESULT_IDENTIFY = {"单位名称","年","月","成果名称","第一完成单位","完成人","任务来源","鉴定/验收单位",
            "鉴定/验收形式","鉴定/验收时间","项目编码","项目名称"};

    /**
     * 题录类型文件表头：5.	专利
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_PATENT = {"单位名称","年","月","申请专利号","授权专利号","专利名称","专利类别","专利是否授权","申请人","申请时间",
            "授权时间","区域","项目编码","项目名称"};

    /**
     * 题录类型文件表头：6.	新药器械
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_MEDICAL = {"单位名称","年","月","新药/器械名称","类别","种类","等级","新药/器械证书号","批准时间",
            "项目负责人","项目编码","项目名称"};

    /**
     * 题录类型文件表头：7.	国合项目
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_COOPERATION = {"单位名称","年","月","项目编码","项目名称","合作类别","合作单位","项目负责人","项目经费",
            "项目开始时间","项目结束时间"};

    /**
     * 题录类型文件表头：8.	论文
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_PAPER = {"单位名称","年","月","通信作者","第一作者","其他作者","类别","论文题目","期刊名称","发表单位",
            "卷","期","页码","影响因子","期刊性质","期刊区域","项目编码","项目名称","项目负责人"};

    public final String[] SRPMS_OUTLINE_FILE_TITLE_SAT_BOOK = {"单位名称","年","月","著作名","作者","作者单位","主编/参编","出版社","出版单位","项目编码","项目名称"};

    /**
     * 题录类型文件表头：9.	学术交流
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_ACA_EXC = {"单位名称","年","月","区域","主办/参办","参与单位","会议类型","参与形式","举办时间","会议名称"};

    /**
     * 题录类型文件表头：10.	其他
     */
    public final String[] SRPMS_OUTLINE_FILE_TITLE_OTHER = {"单位名称","年","月","科研基地类型","基地级别","实验室中心名称","实验室中心名称","依托单位",
            "批准时间","批准文号","成立时间","详细地址","备注"};

    public final static String DATA = "data";

    public final static String AUTHORIZED_FLAG_YES = "10";

    public final static String SRPMS_ADMIN = "admin";
    public final static String SRPMS_LEADER = "leader";
    public final static String SRPMS_FIRST_LEVEL_DEPT_CODE = "1001";// 一级单位CODE

    /**
     * tableFlag常量
     */
    public final static int TABLE_FLAG_0 = 0;
    public final static int TABLE_FLAG_1 = 1;
    public final static int TABLE_FLAG_2 = 2;

    /**
     * 项目变更类型
     */
    public final static String MODIFY_PRO_TYPE_10010101 = "10010101";// 创新工程-重大协同创新
    public final static String MODIFY_PRO_TYPE_10010102 = "10010102";// 创新工程-协同创新团队
    public final static String MODIFY_PRO_TYPE_10010103 = "10010103";// 创新工程-先导专项
    public final static String MODIFY_PRO_TYPE_10010201 = "10010201";// 院基科费
    public final static String MODIFY_PRO_TYPE_10010301 = "10010301";// 校基科费-青年教师
    public final static String MODIFY_PRO_TYPE_10010302 = "10010302";// 校基科费-学生项目
    public final static String MODIFY_PRO_TYPE_10010401 = "10010401";// 改革经费
    public final static String PRO_TYPE_100201 = "100201";// 国家科技重大专项
    public final static String PRO_TYPE_100202 = "100202";// 国家重点研发计划项目
    public final static String PRO_TYPE_100203 = "100203";// 技术创新引导专项
    public final static String PRO_TYPE_100204 = "100204";// 重大科学计划
    public final static String PRO_TYPE_100205 = "100205";// 科技支撑计划
    public final static String PRO_TYPE_100206 = "100206";// 973计划
    public final static String PRO_TYPE_100207 = "100207";// 863计划
    public final static String PRO_TYPE_100208 = "100208";// 基地和人才专项
    public final static String PRO_TYPE_100209 = "100209";// 基础条件平台
    public final static String PRO_TYPE_100210 = "100210";// 国际合作项目(科技部)
    public final static String PRO_TYPE_100211 = "100211";// 其他计划项目
    public final static String PRO_TYPE_100212 = "100212";// 国家自然科学基金项目
    public final static String PRO_TYPE_100213 = "100213";// 国家社会科学基金项目
    public final static String PRO_TYPE_100301 = "100301";// 国家卫生健康委项目
    public final static String PRO_TYPE_100302 = "100302";// 教育部项目
    public final static String PRO_TYPE_100303 = "100303";// 国家发改委项目
    public final static String PRO_TYPE_100304 = "100304";// 国家药监局项目
    public final static String PRO_TYPE_100305 = "100305";// 国家中医药局项目
    public final static String PRO_TYPE_100306 = "100306";// 中国科协
    public final static String PRO_TYPE_100307 = "100307";// 北京市自然科学基金
    public final static String PRO_TYPE_100308 = "100308";// 北京市教委
    public final static String PRO_TYPE_100309 = "100309";// 北京市科协
    public final static String PRO_TYPE_100310 = "100310";// 其他项目
    public final static String PRO_TYPE_100501 = "100501";// 与企业联合项目

    public final String TRAN_LONG_PRO_TYPE = "10020101,10020102,100202,100203,100204,100205,100206,100207,100208,100209,100210," +
            "100211,10021201,10021202,10021203,10021204,10021205,10021206,10021207,10021208,10021209,10021210,10021211," +
            "10021212,10021213,10021214,10021215,10021301,10021302,10021303,10030101,10030102,10030201,10030202,100303,100304," +
            "100305,100306,100307,100308,100309,100310,100501";

    public final String PRO_TYPE_CATEGORY = "10010101,10010102,10010103,10010201,10010301,10010302,10010401";

    /**
     * 项目变更类型
     */
    public final static String MODIFY_MODIFY_TYPE_10 = "10";// 参与人员变更
    public final static String MODIFY_MODIFY_TYPE_20 = "20";// 负责人变更
    public final static String MODIFY_MODIFY_TYPE_30 = "30";// 内容变更
    public final static String MODIFY_MODIFY_TYPE_40 = "40";// 预算变更
    public final static String MODIFY_MODIFY_TYPE_50 = "50";// 状态变更

    public final static String RECORD_NOT_DELETED = "0";// 数据没有删除
    public final static String RECORD_DELETED = "1";// 数据已经被删除
    
    public final static String PROJECT_TYPE_1 = "1";// 项目变更类型-横纵项目
    public final static String PROJECT_TYPE_2 = "2";// 项目变更类型

    public final static String PROJECT_TABLE_FLAG_SELF = "1";// 我的申请
    public final static String PROJECT_TABLE_FLAG_ADMIN = "2";// 管理员

    public final static String CATEGORY_PARENT_CHILDREN = "CATEGORY_PARENT_CHILDREN";// 项目类别下拉包含二级
    public final static String CATEGORY_CHILDREN = "CATEGORY_CHILDREN";// 项目类别只有三级
    public final static String CATEGORY_TRAN_LONG = "CATEGORY_TRAN_LONG";// 项目类别-横纵向
    
	public final static String NOT_SUBMIT = "0"; // "未提交"
	public final static String HAS_SUBMIT = "10"; // "已提交"
	public final static String APPROVE_PASS = "20"; // "审批通过"
	public final static String APPROVE_NO = "30"; // "审批拒绝"
	public final static String APPROVE_REDO = "40"; // "审批拒绝"

    /**
     * 成果类型
     */
	public final static String RESULT_PAPER = "10";
	public final static String RESULT_PATENT_FIRST = "20";
	public final static String RESULT_PATENT_SECOND = "30";
	public final static String RESULT_PATENT_THREE = "40";
	public final static String RESULT_BOOK_FIRST = "50";
	public final static String RESULT_BOOK_SECOND = "60";
	public final static String RESULT_BOOK_THREE = "70";
	public final static String RESULT_BOOK_FOUR = "80";
	public final static String RESULT_BOOK_FIVE = "90";
	public final static String RESULT_ACA_EXC_FIRST = "100";
	public final static String RESULT_ACA_EXC_SECOND = "110";
	public final static String RESULT_AWARD_FIRST = "120";
	public final static String RESULT_AWARD_SECOND = "130";
	public final static String RESULT_AWARD_THREE = "140";
	public final static String RESULT_AWARD_FOUR = "150";
	public final static String RESULT_AWARD_FIVE = "160";
	public final static String RESULT_MEDICAL_FIRST = "170";
	public final static String RESULT_MEDICAL_SECOND = "180";
	public final static String RESULT_MEDICAL_THREE = "190";
	public final static String RESULT_APPLIANCE = "200";
	public final static String RESULT_SOFTWARE = "210";


	public final static String REGION_10 = "10";// 区域-国内
	public final static String REGION_20 = "20";// 区域-国际

    public final static String RESULT_AWARD_5001 = "5001";// 奖励-国家级奖项
    public final static String RESULT_AWARD_5002 = "5002";// 奖励-省部级奖项
    public final static String RESULT_AWARD_5003 = "5003";// 奖励-高校科技奖项
    public final static String RESULT_AWARD_5004 = "5004";// 奖励-中华医学奖项
    public final static String RESULT_AWARD_5005 = "5005";// 奖励-其他社会奖项

    public final static String MEDICAL_CAT_10 = "10";// 新药器械
    public final static String MEDICAL_CAT_20 = "20";// 医疗器械
    public final static String MEDICAL_TYPE_10 = "10";// 西药
    public final static String MEDICAL_TYPE_20 = "20";// 中药
    public final static String MEDICAL_TYPE_30 = "30";// 生物药剂


    public final static String OUTLINE_PRO_STAT_10 = "10";// 新中标
    public final static String OUTLINE_PRO_STAT_20 = "20";// 在研
    public final static String OUTLINE_PRO_STAT_30 = "30";// 结题
    public final static String OUTLINE_PRO_STAT_40 = "40";// 终止
    public final static String OUTLINE_PRO_STAT_50 = "50";// 取消
    public final static String OUTLINE_PRO_STAT_60 = "60";// 延期


    public final static String APPROVE_APPLY_BOOK = "申请书审批";
    public final static String APPROVE_TASK_BOOK = "任务书审批";
    public final static String APPROVE_UPDATE_PERSON_BOOK = "项目负责人变更审批";
    public final static String APPROVE_UPDATE_JOIN_BOOK = "项目参与人员变更审批";
    public final static String APPROVE_UPDATE_CONTENT_BOOK = "项目内容变更审批";
    public final static String APPROVE_UPDATE_BUDGET_BOOK = "项目预算变更审批";
    public final static String APPROVE_UPDATE_STATE_BOOK = "项目状态变更审批";
    public final static String APPROVE_BUDGET_APPLY_BOOK = "预算申请审批";
    public final static String APPROVE_TRAN_LONG_TASK_BOOK = "横纵向项目审批";
    public final static String APPROVE_MPR_EVA_A = "中期绩效报告审批A";
    public final static String APPROVE_MPR_EVA_B = "中期绩效报告审批B";
    public final static String APPROVE_RESULT_PAPER = "论文成果审批";
    public final static String APPROVE_RESULT_PATENT = "专利成果审批";
    public final static String APPROVE_RESULT_BOOK = "著作成果审批";
    public final static String APPROVE_RESULT_TRANS_BOOK = "成果转化审批";
    public final static String APPROVE_RESULT_ACA_EXC = "学术交流成果审批";
    public final static String APPROVE_RESULT_AWARD = "奖励成果审批";
    public final static String APPROVE_RESULT_MEDICAL = "新药证书成果审批";
    public final static String APPROVE_RESULT_APPLIANCE = "医疗器械成果审批";
    public final static String APPROVE_RESULT_SOFTWARE = "软件成果审批";
    public final static String APPROVE_ACCEPT_BOOK = "项目验收审批";

}

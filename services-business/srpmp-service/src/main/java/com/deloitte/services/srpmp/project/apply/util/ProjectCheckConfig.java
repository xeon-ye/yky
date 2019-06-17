package com.deloitte.services.srpmp.project.apply.util;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-08
 * @Description : CHECK共用配置类
 * @Modified :
 */
public interface ProjectCheckConfig {

	public static String[] apply_Bcoo_not_empty = new String[] {
			"projectName,项目名称", 
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"activeType,项目活动类型",
			"achievementType,预期成果类型",
			"subjectCategory,学科分类",
			
			"projectAbstract,项目摘要",
			"approvalNecessay,立项必要性",
			"achievementForm,项目成果的呈现形式及描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"researchContentMain,主要研究内容",
			"researchContentMethod,拟采取的研究方法、技术路线及其可行性分析",
			"researchContentInterflow,国际合作与交流方案",
			"researchContentUsePlan,成果转化应用和科普方案",
			"researchContentInnovate,项目预期的主要创新点",
			"projectTarget,项目总体目标",
			"taskDiagram,项目任务分解情况和各任务之间的逻辑关系图示",
			"superiorityDeptChoose,项目参与单位的选择原因及其优势",
			"superiorityDeptBasic,项目牵头单位和参加单位与课题实施相关的实力和基础，近5年在本领域承担相关科研项目、取得的业绩和成就等",
			"superiorityDeptSuport,申报单位相关科研条件支撑状况",
			"superiorityDeptAbroad,国际合作单位及团队情况",
			"mainstayMemberNote,项目首席专家及骨干成员的情况",
			"manageGuarantee,项目组织管理、协同机制和保障措施",
			"manageKnowledgeRight,知识产权对策、成果管理及合作权益分配",
			"manageRisk,风险分析及对策",
			
			"mainMemberList,主要参与人员", 
			"jointApplicantUnit,联合申请单位",
			"coopDeptList,国际合作单位",
			"taskPreYearList,年度任务",
			"taskDecompositionList,项目任务分解情况和各任务之间的逻辑关系图示", 
			
			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",
			
			"jointApplicantUnit.deptName,联合申请单位的单位名称",
			"jointApplicantUnit.taskLeaderName,联合申请单位的任务负责人",
			"jointApplicantUnit.phone,联合申请单位的联系电话",
			"jointApplicantUnit.email,联合申请单位的联系邮箱",
			
			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",
			
			"taskPreYearList.taskYear,年度任务的年度",
			"taskPreYearList.taskName,年度任务集合的主要内容",
			"taskPreYearList.taskTargetYear,年度任务集合的年度考核指标",
			"taskPreYearList.importantPoint,年度任务集合的重要时间节点",
			
			"taskDecompositionList.taskName,项目任务分解情况和各任务之间的任务名称",
			"taskDecompositionList.taskContent,项目任务分解情况和各任务之间的逻辑关系图示的主要内容",
			"taskDecompositionList.taskTargetYear,项目任务分解情况和各任务之间的逻辑关系图示的总体考核目标",
			"taskDecompositionList.mediumTarget,项目任务分解情况和各任务之间的逻辑关系图示的当年考核指标",
			"taskDecompositionList.targetPerYear,项目任务分解情况和各任务之间的逻辑关系图示的来年考核指标",
			"taskDecompositionList.leadPerson,项目任务分解情况和各任务之间的逻辑关系图示的负责人",
			"taskDecompositionList.joinPerson,项目任务分解情况和各任务之间的逻辑关系图示的参与人",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的经费分配比例" };

	public static String[] apply_Coo_not_empty = new String[] { "projectName,项目名称",
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"direction,研究方向",
			"belongDomain,所在领域",
			"subjectCategory,学科分类",
			"teamDirection,团队组成情况的研究方向",
			"teamEnName,团队组成情况的英文名称",
			
			"teamConstitute,团队的组成情况",
			"topExpertPersonIntro,首席专家简介",
			"teamMemberIntro,团队主要成员简介",
			"performanceLately,近五年取得的主要学术成绩、创新点及其科学意义",
			"projectTarget,研究目标",
			"researchPlan,工作方案及可行性",
			"researchContentInterflow,国际合作与交流",
			"taskDiagram,项目任务分解情况和各任务之间的逻辑关系图示",
			"researchContentInnovate,预期主要创新点",
			"achievementForm,项目成果的呈现形式及描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"manageGuarantee,管理和保障",
			"manageKnowledgeRight,知识产权",
			"manageRisk,风险分析及对策",
			"guaranteePlan,保障条件",

			"mainMemberList,主要参与人员", 
			"coopDeptList,国际合作单位",
			"taskPreYearList,年度任务",
			"taskDecompositionList,项目任务分解情况和各任务之间的逻辑关系图示", 

			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",

			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",

			"taskPreYearList.taskYear,年度任务的年度",
			"taskPreYearList.taskName,年度任务集合的主要内容",
			"taskPreYearList.taskTargetYear,年度任务集合的年度考核指标",
			"taskPreYearList.importantPoint,年度任务集合的重要时间节点",

			"taskDecompositionList.taskName,项目任务分解情况和各任务之间的任务名称",
			"taskDecompositionList.taskContent,项目任务分解情况和各任务之间的逻辑关系图示的主要内容",
			"taskDecompositionList.mediumTarget,项目任务分解情况和各任务之间的逻辑关系图示的中期考核指标",
			"taskDecompositionList.taskTargetYear,项目任务分解情况和各任务之间的逻辑关系图示的总体考核目标",
			"taskDecompositionList.targetPerYear,项目任务分解情况和各任务之间的逻辑关系图示的分年度考核指标",
			"taskDecompositionList.leadPerson,项目任务分解情况和各任务之间的逻辑关系图示的负责人",
			"taskDecompositionList.joinPerson,项目任务分解情况和各任务之间的逻辑关系图示的参与人",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的经费分配比例"
			};

	public static String[] apply_pre_not_empty = new String[] {
			"projectName,项目名称", 
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"applyPostNumber,拟申请岗位数",
			"achievementType,预期成果类型",
			"subjectCategory,学科分类",
			
			"projectAbstract,项目摘要",
			"approvalNecessay,立项必要性",
			"researchContentMain,主要研究内容",
			"researchContentMethod,拟采取的研究方法、技术路线及其可行性分析",
			"achievementForm,项目成果的呈现形式及描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"projectTarget,任务总体目标、考核指标及测评方式",
			"superiorityDeptBasic,基础条件和优势",
			
			"mainMemberList,主要参与人员", 
			"coopDeptList,国际合作单位",
			"taskPreYearList,年度任务",
			
			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",
			
			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",
			
			"taskPreYearList.taskYear,年度任务的年度",
			"taskPreYearList.taskName,年度任务集合的主要内容",
			"taskPreYearList.taskTargetYear,年度任务集合的年度考核指标",
			"taskPreYearList.importantPoint,年度任务集合的重要时间节点",
			};

	public static String[] task_Bcoo_not_empty = new String[] { 
			"projectName,项目名称", 
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"activeType,项目活动类型",
			"achievementType,预期成果类型",
			"subjectCategory,学科分类",
			"belongDomain,所属领域",
			
			"projectAbstract,项目摘要",
			"approvalNecessay,立项依据",
			"researchTarget,研究目标",
			"researchContentMain,研究内容",
			"taskMasterMethod,项目总体考核指标及测评方式/方法",
			"mainContents,主要示范或产业化内容",
			"researchContentMethod,拟采取的研究方法、技术路线及其可行性分析",
			"researchContentInterflow,国际合作与交流方案",
			"achievementForm,项目成果的呈现形式及描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"taskOrgManageMode,项目组织方式和管理机制、产学研结合、创新人才队伍的凝聚和培养等",
			"knowledgeResultManage,知识产权对策、成果管理及合作权益分配",
			"riskAnalyzeManage,风险分析及对策",

			"mainMemberList,主要参与人员", 
			"jointApplicantUnit,参与单位信息",
			"coopDeptList,国际合作单位",
			"taskPreYearList,年度任务",
			"taskDecompositionList,项目任务分解情况和各任务之间的逻辑关系图示", 

			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",
			
			"jointApplicantUnit.deptName,参与单位的单位名称",
			"jointApplicantUnit.peopleCount,参与单位的参与人数",
			"jointApplicantUnit.taskLeaderName,参与单位的任务负责人",
			"jointApplicantUnit.phone,参与单位的联系电话",
			"jointApplicantUnit.email,参与单位的联系邮箱",
			
			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",

			"taskPreYearList.taskTargetYear,预期目标及考核指标",
			"taskPreYearList.examMode,考核方式",
			
			"taskDecompositionList.taskName,项目任务分解情况和各任务之间的任务名称",
			"taskDecompositionList.taskContent,项目任务分解情况和各任务之间的逻辑关系图示的主要内容",
			"taskDecompositionList.taskTargetYear,项目任务分解情况和各任务之间的逻辑关系图示的总体考核目标",
			"taskDecompositionList.mediumTarget,项目任务分解情况和各任务之间的逻辑关系图示的当年考核指标",
			"taskDecompositionList.targetPerYear,项目任务分解情况和各任务之间的逻辑关系图示的来年考核指标",
			"taskDecompositionList.leadPerson,项目任务分解情况和各任务之间的逻辑关系图示的负责人",
			"taskDecompositionList.joinPerson,项目任务分解情况和各任务之间的逻辑关系图示的参与人",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的经费分配比例" 
			};

	public static String[] task_Coo_not_empty = new String[] {
			"projectName,项目名称", 
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"belongDomain,所在领域",
			"subjectCategory,学科分类",

			"leadPersonNote,项目首席专家简介",
			"approvalNecessay,立项依据",
			"researchTarget,研究目标",
			"taskMasterMethod,项目总体考核指标及测评方式/方法",
			"mainContents,主要示范或产业化内容",
			"researchContentMethod,拟采取的研究方法、技术路线及其可行性分析",
			"exchangeProgramme,国际合作与交流方案",
			"achievementForm,项目成果的呈现形式与描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"taskOrgManageMode,项目组织方式和管理机制、产学研结合、创新人才队伍的凝聚和培养等",
			"knowledgeResultManage,知识产权对策、成果管理及合作权益分配",
			"riskAnalyzeManage,风险分析及对策",

			"mainMemberList,主要参与人员", 
			"coopDeptList,国际合作单位",
			"jointApplicantUnit,联合申请单位信息",
			"taskPreYearList,年度任务",
			"taskDecompositionList,项目任务分解情况和各任务之间的逻辑关系图示", 

			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",

			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",

			"jointApplicantUnit.deptName,联合申请单位的单位名称",
			"jointApplicantUnit.peopleCount,联合申请单位的参与人数",
			"jointApplicantUnit.taskLeaderName,联合申请单位的任务负责人",
			"jointApplicantUnit.phone,联合申请单位的联系电话",
			"jointApplicantUnit.email,联合申请单位的联系邮箱",

			"taskPreYearList.taskTargetYear,预期目标及考核指标",
			"taskPreYearList.examMode,考核方式",

			"taskDecompositionList.taskName,项目任务分解情况和各任务之间的任务名称",
			"taskDecompositionList.taskContent,项目任务分解情况和各任务之间的逻辑关系图示的主要内容",
			"taskDecompositionList.mediumTarget,项目任务分解情况和各任务之间的逻辑关系图示的中期考核指标",
			"taskDecompositionList.taskTargetYear,项目任务分解情况和各任务之间的逻辑关系图示的总体考核目标",
			"taskDecompositionList.targetPerYear,项目任务分解情况和各任务之间的逻辑关系图示的分年度考核指标",
			"taskDecompositionList.leadPerson,项目任务分解情况和各任务之间的逻辑关系图示的负责人",
			"taskDecompositionList.joinPerson,项目任务分解情况和各任务之间的逻辑关系图示的参与人",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的经费分配比例"
			};

	public static String[] task_pre_not_empty = new String[] { "projectName,项目名称",
			"projectName,项目名称", 
			"projectActionDateStart,项目执行期限开始",
			"projectActionDateEnd,项目执行期限结束", 
			"achievementType,预期成果类型",
			"subjectCategory,学科分类",

			"approvalNecessay,立项依据",
			"researchTarget,研究目标",
			"researchContentMain,研究内容",
			"taskMasterMethod,项目总体考核指标及测评方式/方法",
			"achievementForm,项目成果的呈现形式与描述",
			"achievementBenefit,项目成果的预期经济、社会效益",
			"researchContentMethod,拟采取的研究方法、技术路线及其可行性分析",
			"taskOrgManageMode,任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养",
			"knowledgeResultManage,知识产权对策、成果管理及合作权益分配",
			"riskAnalyzeManage,风险分析及对策",
			"leadPersonNote,项目首席专家简介",

			"mainMemberList,主要参与人员", 
			"coopDeptList,国际合作单位",
			"taskPreYearList,年度任务",

			"mainMemberList.personName,主要参与人员的姓名",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",

			"coopDeptList.deptName,国际合作单位的单位名称",
			"coopDeptList.taskLeaderName,国际合作单位的负责人",
			"coopDeptList.country,国际合作单位的国别",
			"coopDeptList.email,国际合作单位的联系邮箱",

			"taskPreYearList.firstYearTarget,预期目标及考核指标",
			"taskPreYearList.examMode,考核方式",
			};

	public static String[] apply_Bcoo_not_empty_number = new String[] {
			"applyFunds,总经费",
			"leadPersonWorkTime,项目负责人每年工作时间",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的分配比例" };

	public static String[] apply_Coo_not_empty_number = new String[] {
			"leadPersonWorkTime,项目负责人每年工作时间",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间",
			"taskDecompositionList.allocatedAmount,项目任务分解情况和各任务之间的逻辑关系图示的分配比例" };

	public static String[] apply_pre_not_empty_number = new String[] {
			"applyFunds,拟申请经费",
			"mainMemberList.workPerYear,主要参与人员的每年工作时间"
			};

	public static String[] task_Bcoo_not_empty_number = new String[] {
			"applyFunds,经费预算",
			"leadPersonWorkTime,项目负责人每年工作时间"};

	public static String[] task_Coo_not_empty_number = new String[] {
			"applyFunds,经费预算",
			"leadPersonWorkTime,项目负责人每年工作时间" };

	public static String[] task_pre_not_empty_number = new String[] {
			"applyFunds,经费预算",
			"leadPersonWorkTime,项目负责人每年工作时间"};
}

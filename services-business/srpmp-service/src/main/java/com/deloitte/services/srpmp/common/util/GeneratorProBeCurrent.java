package com.deloitte.services.srpmp.common.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-08
 * @Description :
 * @Modified :
 */
public class GeneratorProBeCurrent {

    static String countC1 = "00";
    static String countC2 = "00";
    static String countC3 = "00";

    public static void main(String[] args) {
        String tempStr = "产出指标,数量指标,申请专利/发表论文/新品种、新产品/国内学术会议报告/编写专著/申请软件著作权/制定指南、规范、标准/举办前沿研究性讲座/学术交流人次/引进人才数量/培养硕士/培养博士/培养博士后/高层次研发团队/参加国际学术会议/组织专业技术培训/在国外开展培训班/在国外培训人次/承办学术会议/开放数据库及信息平台/技术推广/技术服务/科学普及人数/将成果推广至**国家或地区---" +
                ",质量指标,专利进入实审阶段/专利授权/发明专利授权/实用新型专利授权/国内专利申请/国际专利申请/发表SCI论文/中文核心期刊论文篇数/国外期刊论文发表/在**学科领域影响因子大于**分的论文/JCR或中科院分区一区或二区论文/论文、专利等成果他引次数/发表论文入选领跑者5000/出版专著（教材、理论基础型、数据汇总型）/获得软件著作权/主编专著/参编专著/发布国家标准/发布行业标准/发布团体标准/组织国际专业培训/组织国际学术会议/优秀人才、学生获奖人次/培养中或高级职称人次/培养高层次人才/项目负责人获**荣誉/项目组成员获**荣誉/承担学会副主委或主委/项目获得**奖励/在国际学术会议上做邀请报告或发表摘要/获批省部级以上课题获批国家级课题/承担、参与**科研项目/研究政策报告被党中央或国务院办公厅采用或领导人批示的/研究政策报告被省部级采用/应用考核指标或指南被行业用户使用/信息平台建设访问人流量/为企事业单位提供社会服务次数/新增合作单位及服务单位数量/对外技术服务（项目、协议）数量/国际组织任职/精准扶贫/技术推广服务面积/新品种、新产品推广面积/获取**基地与称号/产品成品率提高---" +
                ",时效指标,样本分析时间/耗材采购时间/提交成果报告和获得的知识产权/提交申报书、任务书、结题报告等材料/在相关时间节点完成所列研究内容/预算执行计划完成率---" +
                ",成本指标,项目成本/完成**成果所需成本/人均培训成本/人均会议成本---" +
                "效益指标,经济效益指标,降低患者医疗成本/预期降低患者医疗成本/预期增加企业/农户收入/降低检测试剂成本/横向合作收入/技术服务收入/降低生产成本/转让收益---" +
                ",社会效益指标,提高患者就诊率、增加患者门诊量/减轻患者疾病痛苦/提高患者的诊断率和治愈率/提高**疾病的诊治水平/减少患者及社会负担/促进**疾病研发关键技术完善/提高**质量，降低**风险/提高对**的重视程度/提高对**的认识水平/提高对**科普知识的认识水平/提升项目承担课题组组内科研水平/提升课题组内科研人员科研水平和技术人员技术能力/促进专利转化/促进行业发展/缩短相关实验周期/促进学科建设/提高临床前药效学准确率/促进对**疾病机制的理解/为**疾病防控提供关键信息/服务对象人才培养/服务对象人均收入增加/投入产出比/成本产值率提高/劳动生产率提高/增加就业人数/脱贫人数/资源利用率提高---" +
                ",生态效益指标,技术使用降低生态环境破坏率/保护物种多样性/污染物（水、气）排放达到**标准---" +
                ",可持续影响指标,促进**学科发展/为**研究提供信息平台和技术平台/为**提供技术指导/为**疾病诊疗提供理论依据和支撑/为政府制定相关政策提供理论依据和支撑/带动**研究领域发展水平达到国内一流水平/为国内多地方**领域提供参考/为**研究领域培养青年科技人才/为培养专业人才提供数据支持/提高国内外范围内学术影响力/促进**研究与应用/提高人民健康水平/为后期**研究提供参照，为临床应用提供支持/对**疾病的防治有重要的医学和社会意义/大力推进和我们项目相关的转化研究/聚集人才，推进院校医-药转化/提高**学科受重视程度、地位/提高**研究及临床治疗的国际影响力/促进**国际合作交流/推动**产业发展（如：外源污染物快检产品、疫苗）/为政府提供政策规划撰写服务/为行业企业或地方政府提供发展建议/促进普及科普教育---" +
                "满意度指标,服务对象满意度,患者满意度/科研人员满意度/临床人员满意度/上级单位对本项目满意度/参与单位积极反馈满意度/研究生满意度/项目发包方满意度/学生工作单位满意度/科普宣传对象满意度/政府满意度/企业满意度/技术培训对象满意度/科普服务对象满意度/同行专家对本项目满意度/产品使用单位的满意度,---";

        String[] strArr = tempStr.split("---");

        long rootKey = IdWorker.getId();
        System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + rootKey + "','" + "BE_CURRENT" + "','" + "创新工程通用指标类型" + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");

        long c1key = IdWorker.getId();
        long c2key = IdWorker.getId();
        long c3key = IdWorker.getId();

        String c1 = null;
        String c2 = null;
        String c3 = null;

        for (int i = 0; i < strArr.length; i++) {

            String[] tempArr = strArr[i].split(",");

            if (tempArr.length == 3) {
                countC3 = "00";
                if (!"".equals(tempArr[0])) {
                    c1key = IdWorker.getId();
                    countC2 = "00";
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + getNextCount1() + "','" + tempArr[0] + "','" + rootKey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }
                c2key = IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ("" + getCount1() + getNextCount2()) + "','" + tempArr[1] + "','" + c1key + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                String[] tempArr2 = tempArr[2].split("/");
                for (int j = 0; j < tempArr2.length; j++) {
                    c3key = IdWorker.getId();
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c3key + "','" + ("" + getCount1() + countC2 + getNextCount3()) + "','" + tempArr2[j] + "','" + c2key + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }
            } else {
                if (!"".equals(tempArr[0])) {
                    c1key = IdWorker.getId();
                    countC2 = "00";
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + getNextCount1() + "','" + tempArr[0] + "','" + rootKey + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }
                c2key = IdWorker.getId();
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ("" + getCount1() + getNextCount2()) + "','" + tempArr[1] + "','" + c1key + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");

            }
        }
    }

    private static String getCount1() {

        return "BC" + countC1;
    }

    private static String getNextCount1() {
        int i = Integer.parseInt(countC1);
        i++;
        if (i < 10) {
            countC1 = "0" + i;
        } else {
            countC1 = "" + i;
        }

        return "BC" + countC1;
    }

    private static String getNextCount2() {
        int i = Integer.parseInt(countC2);
        i++;
        if (i < 10) {
            countC2 = "0" + i;
        } else {
            countC2 = "" + i;
        }
        return countC2;
    }

    private static String getNextCount3() {
        int i = Integer.parseInt(countC3);
        i++;
        if (i < 10) {
            countC3 = "0" + i;
        } else {
            countC3 = "" + i;
        }
        return countC3;
    }
}

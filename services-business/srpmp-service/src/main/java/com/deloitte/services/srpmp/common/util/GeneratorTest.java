package com.deloitte.services.srpmp.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class GeneratorTest {

    public static void main(String[] args) {
        String  temp = "[\n" +
                "        {value: '0101',\n" +
                "          label: '哲学',\n" +
                "          children: [\n" +
                "            {value: '010108', label: '科学技术哲学',}\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '0710',\n" +
                "          label: '生物学',\n" +
                "          children: [\n" +
                "            {value:'071002', label: '动物学',},\n" +
                "            {value:'071003',label: '生理学',},\n" +
                "            {value:'071005',label: '微生物学',},\n" +
                "            {value:'071007',label: '遗传学',},\n" +
                "            {value:'071009',label: '细胞生物学',},\n" +
                "            {value:'071010',label: '生物化学与分子生物学',},\n" +
                "            {value:'071011',label: '生物物理学',}\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '0777',\n" +
                "          label: '生物医学工程（理）',\n" +
                "          children: [\n" +
                "            {value:'077700',label: '生物医学工程',},\n" +
                "            {value:'077706',label: '放射医学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '0778',\n" +
                "          label: '基础医学（理）',\n" +
                "          children: [\n" +
                "            {value:'077801',label: '流行病与卫生统计学',},\n" +
                "            {value:'077802',label: '免疫学',},\n" +
                "            {value:'077803',label: '病原微生物学',},\n" +
                "            {value:'077806',label: '放射医学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '0831',\n" +
                "          label: '生物医学工程（工）',\n" +
                "          children: [\n" +
                "            {value:'083100',label: '生物医学工程',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '0852',\n" +
                "          label: '生物医学工程',\n" +
                "          children: [\n" +
                "            {value:'085230',label: '生物医学工程',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1001',\n" +
                "          label: '基础医学',\n" +
                "          children: [\n" +
                "            {value:'100101',label: '人体解剖与组织胚胎学',},\n" +
                "            {value:'100102',label: '免疫学',},\n" +
                "            {value:'100103',label: '病原生物学',},\n" +
                "            {value:'100104',label: '病理学与病理生理学',},\n" +
                "            {value:'100106',label: '放射医学',},\n" +
                "            {value:'100121',label: '★比较医学',},\n" +
                "            {value:'1001Z1',label: '★比较医学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1002',\n" +
                "          label: '临床医学',\n" +
                "          children: [\n" +
                "            {value:'100201',label: '内科学',},\n" +
                "            {value:'100202',label: '儿科学',},\n" +
                "            {value:'100203',label: '老年医学',},\n" +
                "            {value:'100204',label: '神经病学',},\n" +
                "            {value:'100206',label: '皮肤病与性病学',},\n" +
                "            {value:'100207',label: '影像医学与核医学',},\n" +
                "            {value:'100208',label: '临床检验诊断学',},\n" +
                "            {value:'100209',label: '护理学',},\n" +
                "            {value:'100210',label: '外科学',},\n" +
                "            {value:'100211',label: '妇产科学',},\n" +
                "            {value:'100212',label: '眼科学',},\n" +
                "            {value:'100213',label: '耳鼻咽喉科学',},\n" +
                "            {value:'100214',label: '肿瘤学',},\n" +
                "            {value:'100215',label: '康复医学与理疗学',},\n" +
                "            {value:'100217',label: '麻醉学',},\n" +
                "            {value:'100218',label: '急诊医学',},\n" +
                "            {value:'100221',label: '★围术期医学',},\n" +
                "            {value:'100222',label: '★变态反应学',},\n" +
                "            {value:'100223',label: '★输血医学',},\n" +
                "            {value:'100224',label: '心理医学',},\n" +
                "            {value:'1002Z1',label: '★围术期医学',},\n" +
                "            {value:'1002Z2',label: '★变态反应学',},\n" +
                "            {value:'1002Z4',label: '★心理医学',},\n" +
                "            {value:'1002Z5',label: '★干细胞与再生医学',},\n" +
                "            {value:'1002Z7',label: '★输血医学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1003',\n" +
                "          label: '口腔医学',\n" +
                "          children: [\n" +
                "            {value:'100301',label: '口腔基础医学',},\n" +
                "            {value:'100302',label: '口腔临床医学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1004',\n" +
                "          label: '公共卫生与预防医学',\n" +
                "          children: [\n" +
                "            {value:'100401',label: '流行病与卫生统计学',},\n" +
                "            {value:'100403',label: '营养与食品卫生学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1005',\n" +
                "          label: '中医学',\n" +
                "          children: [\n" +
                "            {value:'100506',label: '中医内科学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1006',\n" +
                "          label: '中西医结合',\n" +
                "          children: [\n" +
                "            {value:'100601',label: '中西医结合基础',},\n" +
                "            {value:'100602',label: '中西医结合临床',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1007',\n" +
                "          label: '药学',\n" +
                "          children: [\n" +
                "            {value:'100700',label: '药学',},\n" +
                "            {value:'100701',label: '药物化学',},\n" +
                "            {value:'100702',label: '药剂学',},\n" +
                "            {value:'100703',label: '生物学',},\n" +
                "            {value:'100704',label: '药物分析学',},\n" +
                "            {value:'100705',label: '微生物与生化药学',},\n" +
                "            {value:'100706',label: '药理学',},\n" +
                "            {value:'1007Z1',label: '★生物制品学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1008',\n" +
                "          label: '中药学',\n" +
                "          children: [\n" +
                "            {value:'100800',label: '中药学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1011',\n" +
                "          label: '护理',\n" +
                "          children: [\n" +
                "            {value:'101100',label: '护理学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1051',\n" +
                "          label: '临床医学（专业学位）',\n" +
                "          children: [\n" +
                "            {value:'105101',label: '内科学',},\n" +
                "            {value:'105102',label: '儿科学',},\n" +
                "            {value:'105103',label: '老年医学',},\n" +
                "            {value:'105104',label: '精神病学',},\n" +
                "            {value:'105105',label: '精神病与精神卫生学',},\n" +
                "            {value:'105106',label: '皮肤病与性病学',},\n" +
                "            {value:'105107',label: '影像医学与核医学',},\n" +
                "            {value:'105108',label: '临床检验诊断学',},\n" +
                "            {value:'105109',label: '外科学',},\n" +
                "            {value:'105110',label: '妇产科学',},\n" +
                "            {value:'105111',label: '眼科学',},\n" +
                "            {value:'105112',label: '耳鼻咽喉科学',},\n" +
                "            {value:'105113',label: '肿瘤学',},\n" +
                "            {value:'105114',label: '康复医学与理疗学',},\n" +
                "            {value:'105116',label: '麻醉学',},\n" +
                "            {value:'105117',label: '急诊医学',},\n" +
                "            {value:'105125',label: '中西医结合基础',},\n" +
                "            {value:'105126',label: '中西医结合临床',},\n" +
                "            {value:'105127',label: '全科医学',},\n" +
                "            {value:'105128',label: '临床病理学',},\n" +
                "            {value:'105200',label: '口腔医学',},\n" +
                "            {value:'105300',label: '公共卫生',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1054',\n" +
                "          label: '护理（专业学位）',\n" +
                "          children: [\n" +
                "            {value:'105400',label: '护理硕士',},\n" +
                "            {value:'105500',label: '药学',},\n" +
                "          ]\n" +
                "        },\n" +
                "        {value: '1072',\n" +
                "          label: '生物医学工程',\n" +
                "          children: [\n" +
                "            {value:'107200',label: '生物医学工程',},\n" +
                "          ]\n" +
                "        },\n" +
                "      ]";

        JSONArray  array  = JSONArray.parseArray(temp);
        Long  idstart = 1102913621822419292L;
        for (Object  obj: array) {
            JSONObject ss = (JSONObject) obj;
            Long id = idstart++;
            System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + id + "','" + ss.get("value") + "','"  + ss.get("label") + "','" +  1102913621822419291L + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
            JSONArray children = ss.getJSONArray("children");
            for (Object xx: children) {
                JSONObject  child = (JSONObject)xx;
                System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + (idstart++) + "','" + child.get("value") + "','"  + child.get("label") + "','" +  id + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
            }
        }
        System.out.println(array);

    }
}

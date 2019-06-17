package com.deloitte.services.srpmp.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.common.enums.ProjectCategoryEnums;
import com.deloitte.services.srpmp.common.word.dto.Person;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.velocity.runtime.parser.node.MathUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lixin on 21/03/2019.
 */
@Slf4j
public class BudgetDetailUtil {

    public static final String BUDGET_DETAIL_INN = "[{\"amount\":\"\",\"serial\":1,\"name\":\"设备费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":2,\"name\":\"材料费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":3,\"name\":\"测试化验加工费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":4,\"name\":\"燃料动力费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":5,\"name\":\"差旅费、会议费、国际合作与交流费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":6,\"name\":\"出版/文献/信息传播/知识产权事务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":7,\"name\":\"劳务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":8,\"name\":\"专家咨询费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":9,\"name\":\"其他\",\"remark\":\"\"}]";
    public static final String BUDGET_DETAIL_ACA = "[{\"amount\":\"\",\"serial\":1,\"name\":\"设备费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":2,\"name\":\"材料费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":3,\"name\":\"测试化验加工费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":4,\"name\":\"燃料动力费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":5,\"name\":\"差旅费、会议费、国际合作与交流费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":6,\"name\":\"出版/文献/信息传播/知识产权事务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":7,\"name\":\"劳务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":8,\"name\":\"专家咨询费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":9,\"name\":\"其他\",\"remark\":\"\"}]";

    public static List<SrpmsProjectBudgetDetailVo> emptyBudgetListByCategory(String projectType){
        List<SrpmsProjectBudgetDetailVo> list = new ArrayList<>();
        SrpmsProjectBudgetDetailVo vo = new SrpmsProjectBudgetDetailVo();
        list.add(vo);
        vo.setBudgetYear(Integer.parseInt(DateFormatUtils.format(new Date(),"yyyy")));
        vo.setBudgetAmount(null);
        if (ProjectCategoryEnums.INNOVATE_PRE.getHeader().equals(projectType) || ProjectCategoryEnums.INNOVATE_BCOO.getHeader().equals(projectType) ||ProjectCategoryEnums.INNOVATE_COO.getHeader().equals(projectType)){
            vo.setBudgetDetail(JSONObject.parseArray(BUDGET_DETAIL_INN));
        }else{
            vo.setBudgetDetail(JSONObject.parseArray(BUDGET_DETAIL_ACA));
        }
        return list;
    }

    public static JSONArray emptyBudgetDetailInn(){

		JSONArray detail =  emptyBudgetDetailByCategroy(ProjectCategoryEnums.INNOVATE_PRE.getCode());
		for (int j = 0; j < detail.size(); j++) {
			JSONObject item = detail.getJSONObject(j);
			item.put("amount", 0);
			detail.set(j, item);
		}
        return detail;
    }

    public static JSONArray emptyBudgetDetailByCategroy(String projectType){
        List<SrpmsProjectBudgetDetailVo> list = emptyBudgetListByCategory(projectType);
        return list.get(0).getBudgetDetail();
    }

    /**
     * 合并预算明细
     */
    public static SrpmsProjectTaskVo mergeBudgetDetail(List<JSONArray> budgetDetailList){
        SrpmsProjectTaskVo detailVo = new SrpmsProjectTaskVo();
        JSONArray arr = new JSONArray();
        Double acmountAll = new Double(0);
        for(int i = 0; i < budgetDetailList.size(); i++){
            JSONArray array = budgetDetailList.get(i);
            for (int j = 0; j < array.size(); j++){
                JSONObject obj = array.getJSONObject(j);
                if (i == 0){
                    JSONObject newObj = new JSONObject();
                    newObj.putAll(obj);
                    arr.add(newObj);
                }else{
                    JSONObject originObj = arr.getJSONObject(j);
                    Double amounta = getDoubleSafe(originObj, "amount");
                    Double amountb = getDoubleSafe(obj,"amount");
                    if (amounta != null){
                        originObj.put("amount", MathUtils.add(amounta,amountb));
                    }
                }
                acmountAll = (Double) MathUtils.add(acmountAll, getDoubleSafe(obj,"amount"));
            }
        }
        detailVo.setBudgetDetail(arr);
        detailVo.setBudgetAmount(String.valueOf(acmountAll));
        return detailVo;
    }

    /**
     * 合并预算明细
     */
    public static JSONArray mergeBudgetDetailReturnDetail(List<JSONArray> budgetDetailList){
        JSONArray arr = new JSONArray();
        for(int i = 0; i < budgetDetailList.size(); i++){
            JSONArray array = budgetDetailList.get(i);
            for (int j = 0; j < array.size(); j++){
                JSONObject obj = array.getJSONObject(j);
                if (i == 0){
                    JSONObject newObj = new JSONObject();
                    newObj.putAll(obj);
                    arr.add(newObj);
                }else{
                    JSONObject originObj = arr.getJSONObject(j);
                    Double amounta = getDoubleSafe(originObj, "amount");
                    Double amountb = getDoubleSafe(obj,"amount");
                    if (amounta != null){
                        originObj.put("amount", MathUtils.add(amounta,amountb));
                    }
                }
            }
        }
        return arr;
    }


    private static Double getDoubleSafe(JSONObject obj, String key){
        Double value = obj.getDouble(key);
        if (value == null){
            return new Double(0);
        }
        return value;
    }

    public static void main(String[] args){
//        String s1 = "[[{\"amount\":\"1\",\"serial\":1,\"name\":\"设备费\",\"remark\":\"c\"},{\"amount\":\"1\",\"serial\":2,\"name\":\"材料费\",\"remark\":\"c\"},{\"amount\":\"\",\"serial\":3,\"name\":\"测试化验加工费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":4,\"name\":\"燃料动力费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":5,\"name\":\"差旅费、会议费、国际合作与交流费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":6,\"name\":\"出版/文献/信息传播/只是产权事务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":7,\"name\":\"劳务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":8,\"name\":\"专家咨询费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":9,\"name\":\"其他\",\"remark\":\"\"}],[{\"amount\":\"1\",\"serial\":1,\"name\":\"设备费\",\"remark\":\"c\"},{\"amount\":\"1\",\"serial\":2,\"name\":\"材料费\",\"remark\":\"c\"},{\"amount\":\"\",\"serial\":3,\"name\":\"测试化验加工费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":4,\"name\":\"燃料动力费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":5,\"name\":\"差旅费、会议费、国际合作与交流费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":6,\"name\":\"出版/文献/信息传播/只是产权事务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":7,\"name\":\"劳务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":8,\"name\":\"专家咨询费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":9,\"name\":\"其他\",\"remark\":\"\"}]]";
//        String s2 = "[[{\"amount\":\"1\",\"serial\":1,\"name\":\"设备费\",\"remark\":\"c\"},{\"amount\":\"1\",\"serial\":2,\"name\":\"材料费\",\"remark\":\"c\"},{\"amount\":\"\",\"serial\":3,\"name\":\"测试化验加工费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":4,\"name\":\"燃料动力费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":5,\"name\":\"差旅费、会议费、国际合作与交流费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":6,\"name\":\"出版/文献/信息传播/只是产权事务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":7,\"name\":\"劳务费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":8,\"name\":\"专家咨询费\",\"remark\":\"\"},{\"amount\":\"\",\"serial\":9,\"name\":\"其他\",\"remark\":\"\"}]]";
//        List<JSONArray> arrList =JSONObject.parseArray(s2, JSONArray.class);
//
////        JSONArray arr = mergeBudgetDetail(arrList);
//
//        System.out.println(JSONObject.toJSONString(arr));


//        Person p1 = new Person();
//        p1.setName("a");
//        p1.setGender("男");
//        Person p2 = new Person();
//        p2.setName("b");
//        p2.setGender("");
//        Person p3 = new Person();
//        p3.setName("c");
//        p3.setGender("女");
//
//        List<Person> plist = new ArrayList<>();
//        plist.add(p1);
//        plist.add(p2);
//        plist.add(p3);

//        ImmutableListMultimap<String, Person> map = Multimaps.index(plist, new Function<Person, String>() {
//            @Override
//            public String apply(Person person) {
//                if (person.getGender() == null){
//                    return "非人类";
//                }
//                return person.getGender();
//            }
//        });
//
//        for (String key: map.keySet()){
//            ImmutableList<Person> sublist = map.get(key);
//            System.out.println("size:"+sublist.size());
//        }
//
//
//        System.out.println(StringUtils.isNumeric("2.0"));
//        System.out.println(NumberUtils.toDouble(null, 0));
//
//
//        List<String> list1 = new ArrayList<>();
//        list1.add("a");
//        list1.add(0,"c");
//        list1.add(0, "d");
//        list1.set(0, "e");
//        System.out.println(list1);

        String s = "[\"张三\",\"李四\"]";
        System.out.println(s.replaceAll("[\\[|\\]|\"]", ""));
    }





}

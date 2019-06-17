package com.deloitte.services.dss.scientific.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.dss.scientific.mapper.DeptFundMapper;
import com.deloitte.services.dss.scientific.mapper.ResearchResultMapper;
import com.deloitte.services.dss.scientific.service.IDeptFundService;
import com.deloitte.services.dss.util.MathUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptFundServiceImpl extends ServiceImpl implements IDeptFundService {

    @Autowired
    private DeptFundMapper deptFundMapper;

    @Autowired
    private ResearchResultMapper researchResultMapper;


    @Override
    public List<DeptFundVo> queryDeptProAmountPay(Map map) {
        return deptFundMapper.queryDeptProAmountPay(map);
    }

    @Override
    public Map queryDeptProFundDetail(Map map) {
        //查询明细
        List<ProjectExcutePropVo> projectExcutePropVos = deptFundMapper.queryFundDetail(map);
        Double  avg = NumberUtils.DOUBLE_ZERO;
        if (!CollectionUtils.isEmpty(projectExcutePropVos)){
            Double totalPropprtion  =  NumberUtils.DOUBLE_ZERO;
            for (ProjectExcutePropVo  vo : projectExcutePropVos){
                Double budgetFunds = vo.getBudgetFunds();
                if (null == budgetFunds || budgetFunds == NumberUtils.DOUBLE_ZERO){
                    continue;
                }
                Double divValue = MathUtils.div(vo.getPayFunds(), budgetFunds,4);
                totalPropprtion +=divValue;
                vo.setPropprtion(divValue);
            }

            avg = MathUtils.div(totalPropprtion,projectExcutePropVos.size());
        }

        // map.get("projectNum").toString() 页面 projectNum  实为任务编码
        // 获取根据 任务编码  获取对应得 项目编码来查询成果
        String taskCode = map.get("projectNum").toString();
        String proCode = researchResultMapper.queryProCode(taskCode);
        //查询项目成果
        List<ResultVo> paper = researchResultMapper.queryResearch(proCode);

        //项目PI
        ProjectLeaderVo leader = deptFundMapper.queryLeader(map.get("projectNum").toString());

        //项目人员学位统计
        ProjectPersonJoinVo joinPerson = deptFundMapper.queryJoinPerson(map.get("projectNum").toString());
       Map resutlMap = new HashMap();
        resutlMap.put("detail",projectExcutePropVos);
        resutlMap.put("avg",avg);
        resutlMap.put("paper",paper);
        resutlMap.put("leader", leader);
        resutlMap.put("joinPerson", joinPerson);
        return resutlMap;
    }


    /**
     * 处理经费明细JSON
     *
     * @param JSONString
     * @return
     */
    public Map JSONHandler(String JSONString) {
        Map cmap = new HashMap();
        if (JSON.isValid(JSONString)) {//判断是否是Json字符串
            JSONArray objects = JSON.parseArray(JSONString);//转换成jsonarray

            //把jsonarray里的数据整合，按name以及对应的amount合并成一个map
            for (int i = 0; i < objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                String name = jsonObject.get("name").toString();
                if (cmap.containsKey(name)) {
                    Object amount = jsonObject.get("amount");
                    if (null != amount && !"".equals(amount)) {
                        if (null != cmap.get(name) && !"".equals(cmap.get(name))) {
                            cmap.put(name, Long.parseLong(amount.toString()) + Long.parseLong(cmap.get(name).toString()));
                        } else {
                            cmap.put(name, amount);
                        }
                    }
                } else {
                    cmap.put(name, jsonObject.get("amount"));
                }
            }
        }else {
            throw new PlatFormException(PlatformErrorType.TYPE_CAST_ERROE);
        }
        return cmap;
    }


}

package com.deloitte.services.dss.scientific.mapper;


import com.deloitte.platform.api.dss.scientific.vo.DeptFundVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectExcutePropVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectLeaderVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectPersonJoinVo;

import java.util.List;
import java.util.Map;

/**
 * 单位预算执行情况接口
 */
public interface DeptFundMapper {


    /**
     * 单位-项目年度预算与支出
     * @param map
     * @return
     */
    List<DeptFundVo> queryDeptProAmountPay(Map map);


    /**
     * 单位-项目预算与支出明细
     * @param map
     * @return
     */
    Map queryDeptProFundDetail(Map map);


    /**
     * 单位-项目 负责人，状态，学科方向
     * @param projectNum
     * @return
     */
    ProjectLeaderVo queryLeader(String projectNum);


    /**
     * 单位-项目 参与人员学位统计
     * @param projectNum
     * @return
     */
    ProjectPersonJoinVo queryJoinPerson(String projectNum);


    /**
     * 单位-项目预算与支出明细
     * @param map
     * @return
     */
    List<ProjectExcutePropVo> queryFundDetail(Map map);
}

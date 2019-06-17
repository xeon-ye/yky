package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectDeptJoinQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectDeptJoin;

import java.util.List;

/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description : SrpmsProjectDeptJoin服务类接口
 * @Modified :
 */
public interface ISrpmsProjectDeptJoinService extends IService<SrpmsProjectDeptJoin> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectDeptJoin>
     */
    IPage<SrpmsProjectDeptJoin> selectPage(SrpmsProjectDeptJoinQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectDeptJoin>
     */
    List<SrpmsProjectDeptJoin> selectList(SrpmsProjectDeptJoinQueryForm queryForm);


    void cleanAndSaveDeptJoin(List<SrpmsProjectDeptJoinVo> deptJoinList, DeptJoinWayEnums joinWay, Long projectId);

    void saveDeptJoin(List<SrpmsProjectDeptJoinVo> deptJoinList, DeptJoinWayEnums joinWay, Long projectId);

    List<SrpmsProjectDeptJoinVo> queryDeptJoinListByJoinWay(DeptJoinWayEnums joinWay, Long projectId);

}

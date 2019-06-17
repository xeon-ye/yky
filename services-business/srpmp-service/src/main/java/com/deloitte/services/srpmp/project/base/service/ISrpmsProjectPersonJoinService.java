package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectPersonJoinQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPersonJoin;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectPersonJoin服务类接口
 * @Modified :
 */
public interface ISrpmsProjectPersonJoinService extends IService<SrpmsProjectPersonJoin> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsProjectPersonJoin>
     */
    IPage<SrpmsProjectPersonJoin> selectPage(SrpmsProjectPersonJoinQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsProjectPersonJoin>
     */
    List<SrpmsProjectPersonJoin> selectList(SrpmsProjectPersonJoinQueryForm queryForm);

    void cleanAndSavePersonJoin(List<SrpmsProjectPersonJoinVo> personJoinVoList, PersonJoinWayEnums joinWayEnum, Long projectId);

    void savePersonJoin(List<SrpmsProjectPersonJoinVo> personJoinVoList, PersonJoinWayEnums joinWayEnum, Long projectId);

    List<SrpmsProjectPersonJoinVo> queryPersonJoinListByJoinWay(PersonJoinWayEnums joinWay, Long projectId);

    void deleteByJoinWayAndProjectId(PersonJoinWayEnums joinWayEnum, Long projectId);

    /**
     * 复制预算表项目参与人员
     * @param budgetTaskId
     */
    void copyPersonJoin (Long budgetTaskId,Long projectId);
}

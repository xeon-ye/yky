package com.deloitte.services.srpmp.project.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectDeptJoinQueryForm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.services.srpmp.common.enums.DeptJoinWayEnums;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectDeptJoin;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectDeptJoinMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectDeptJoinService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : wangyanyun
 * @Date : Create in 2019-02-16
 * @Description :  SrpmsProjectDeptJoin服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectDeptJoinServiceImpl extends ServiceImpl<SrpmsProjectDeptJoinMapper, SrpmsProjectDeptJoin> implements ISrpmsProjectDeptJoinService {


    @Autowired
    private SrpmsProjectDeptJoinMapper srpmsProjectDeptJoinMapper;

    @Override
    public IPage<SrpmsProjectDeptJoin> selectPage(SrpmsProjectDeptJoinQueryForm queryForm) {
        QueryWrapper<SrpmsProjectDeptJoin> queryWrapper = new QueryWrapper <SrpmsProjectDeptJoin>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectDeptJoinMapper.selectPage(new Page<SrpmsProjectDeptJoin>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectDeptJoin> selectList(SrpmsProjectDeptJoinQueryForm queryForm) {
        QueryWrapper<SrpmsProjectDeptJoin> queryWrapper = new QueryWrapper <SrpmsProjectDeptJoin>();
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectDeptJoinMapper.selectList(queryWrapper);
    }

    @Override
    public void cleanAndSaveDeptJoin(List<SrpmsProjectDeptJoinVo> deptJoinVoList, DeptJoinWayEnums joinWay, Long projectId) {
        if (joinWay==null) return;
        UpdateWrapper<SrpmsProjectDeptJoin> deptJoinWrapper = new UpdateWrapper<SrpmsProjectDeptJoin>() ;
        deptJoinWrapper.eq(SrpmsProjectDeptJoin.JOIN_WAY, joinWay.getCode());
        deptJoinWrapper.eq(SrpmsProjectDeptJoin.PROJECT_ID, projectId);
        this.remove(deptJoinWrapper);

        saveDeptJoin(deptJoinVoList, joinWay, projectId);
    }

    @Override
    public void saveDeptJoin(List<SrpmsProjectDeptJoinVo> deptJoinVoList, DeptJoinWayEnums joinWay, Long projectId) {
        if (deptJoinVoList == null){
            return;
        }
        List<SrpmsProjectDeptJoin> deptJoinList = new ArrayList<>();
        for (SrpmsProjectDeptJoinVo joinDeptVo: deptJoinVoList){
            SrpmsProjectDeptJoin deptJoinEntity = new SrpmsProjectDeptJoin();
            BeanUtils.copyProperties(joinDeptVo, deptJoinEntity);
            deptJoinEntity.setJoinWay(joinWay.getCode());
            deptJoinEntity.setProjectId(projectId);
            deptJoinEntity.setId(null);
            deptJoinList.add(deptJoinEntity);
        }
        this.saveBatch(deptJoinList);
    }

    @Override
    public List<SrpmsProjectDeptJoinVo> queryDeptJoinListByJoinWay(DeptJoinWayEnums joinWay, Long projectId) {
        List<SrpmsProjectDeptJoinVo> voList = new ArrayList<>();
        if (joinWay == null){
            return voList;
        }
        QueryWrapper<SrpmsProjectDeptJoin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectDeptJoin.JOIN_WAY, joinWay.getCode());
        queryWrapper.eq(SrpmsProjectDeptJoin.PROJECT_ID, projectId);
        List<SrpmsProjectDeptJoin> entityList = this.list(queryWrapper);
        if(entityList==null){
            return null;
        }

        for (SrpmsProjectDeptJoin deptJoinEntity: entityList){
            SrpmsProjectDeptJoinVo deptJoinVo = new SrpmsProjectDeptJoinVo();
            BeanUtils.copyProperties(deptJoinEntity, deptJoinVo);
            voList.add(deptJoinVo);
        }
        return voList;
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectDeptJoin> getQueryWrapper(QueryWrapper<SrpmsProjectDeptJoin> queryWrapper,BaseQueryForm<SrpmsProjectDeptJoinQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getProjectId())){
            queryWrapper.like(SrpmsProjectDeptJoin.PROJECT_ID,srpmsProjectDeptJoin.getProjectId());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getSort())){
            queryWrapper.like(SrpmsProjectDeptJoin.SORT,srpmsProjectDeptJoin.getSort());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getProjectNum())){
            queryWrapper.like(SrpmsProjectDeptJoin.PROJECT_NUM,srpmsProjectDeptJoin.getProjectNum());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getJoinWay())){
            queryWrapper.like(SrpmsProjectDeptJoin.JOIN_WAY,srpmsProjectDeptJoin.getJoinWay());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getDeptName())){
            queryWrapper.like(SrpmsProjectDeptJoin.DEPT_NAME,srpmsProjectDeptJoin.getDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getTaskLeaderName())){
            queryWrapper.like(SrpmsProjectDeptJoin.TASK_LEADER_NAME,srpmsProjectDeptJoin.getTaskLeaderName());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getEmail())){
            queryWrapper.like(SrpmsProjectDeptJoin.EMAIL,srpmsProjectDeptJoin.getEmail());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getPhone())){
            queryWrapper.like(SrpmsProjectDeptJoin.PHONE,srpmsProjectDeptJoin.getPhone());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getPhoneOrEmail())){
            queryWrapper.like(SrpmsProjectDeptJoin.PHONE_OR_EMAIL,srpmsProjectDeptJoin.getPhoneOrEmail());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getCountry())){
            queryWrapper.like(SrpmsProjectDeptJoin.COUNTRY,srpmsProjectDeptJoin.getCountry());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getCreateTime())){
            queryWrapper.like(SrpmsProjectDeptJoin.CREATE_TIME,srpmsProjectDeptJoin.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getCreateBy())){
            queryWrapper.like(SrpmsProjectDeptJoin.CREATE_BY,srpmsProjectDeptJoin.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getUpdateTime())){
            queryWrapper.like(SrpmsProjectDeptJoin.UPDATE_TIME,srpmsProjectDeptJoin.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectDeptJoin.getUpdateBy())){
            queryWrapper.like(SrpmsProjectDeptJoin.UPDATE_BY,srpmsProjectDeptJoin.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}


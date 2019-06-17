package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectExpertVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.SrpmsProjectExpertSaveForm;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectExpert;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectExpertMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectExpertService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-02-26
 * @Description :  SrpmsProjectExpert服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectExpertServiceImpl extends ServiceImpl<SrpmsProjectExpertMapper, SrpmsProjectExpert> implements ISrpmsProjectExpertService {

    @Override
    public List<SrpmsProjectExpert> queryListByProjectId(Long projectId, String type) {
        QueryWrapper<SrpmsProjectExpert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectExpert.PROJECT_ID, projectId);
        queryWrapper.eq(SrpmsProjectExpert.TYPE, type);
        return this.list(queryWrapper);
    }

    @Override
    public void cleanAndSave(SrpmsProjectExpertSaveForm vo) {
        if (vo == null) {
            return;
        }
        List<Long> projectList =  vo.getProjectList();
        List<SrpmsProjectExpertVo> expertList = vo.getExpertList();
        for (int i = 0; i < projectList.size(); i ++) {
            long projectId = projectList.get(i);
            List<SrpmsProjectExpert> list = new ArrayList<>();
            for (int j = 0; j < expertList.size(); j ++) {
                SrpmsProjectExpertVo tempVo = expertList.get(j);
                SrpmsProjectExpert expertEntity = JSONObject.parseObject(JSON.toJSONString(tempVo), SrpmsProjectExpert.class);
                expertEntity.setProjectId(projectId);
                expertEntity.setType(vo.getType());
                expertEntity.setId(null);
                list.add(expertEntity);
            }
            UpdateWrapper<SrpmsProjectExpert> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(SrpmsProjectExpert.PROJECT_ID, projectId);
            updateWrapper.eq(SrpmsProjectExpert.TYPE, vo.getType());
            this.remove(updateWrapper);
            this.saveBatch(list);
        }
    }

    /**
     * 删除评审专家数据service接口实现
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.removeById(id);
    }
}


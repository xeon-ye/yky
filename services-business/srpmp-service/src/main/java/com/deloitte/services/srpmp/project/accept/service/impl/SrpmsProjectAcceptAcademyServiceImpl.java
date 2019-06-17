package com.deloitte.services.srpmp.project.accept.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptAcademy;
import com.deloitte.services.srpmp.project.accept.mapper.SrpmsProjectAcceptAcademyMapper;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptAcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAcceptAcademy服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectAcceptAcademyServiceImpl extends ServiceImpl<SrpmsProjectAcceptAcademyMapper, SrpmsProjectAcceptAcademy> implements ISrpmsProjectAcceptAcademyService {


    @Autowired
    private SrpmsProjectAcceptAcademyMapper srpmsProjectAcceptAcademyMapper;

    /**
     * 根据验收ID查询校基科费验收数据service接口
     *
     * @param acceptId
     * @return
     */
    @Override
    public JSONObject queryByAcceptId(Long acceptId) {
        SrpmsProjectAcceptAcademy academy = selectList(acceptId);
        if(academy == null) {
            throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("assessmentIndicators", academy.getAssessmentIndicators());// 项目考核指标
        jsonObject.put("projectCompletionCase", academy.getProjectCompletionCase());// 研究内容考核指标完成情况
        jsonObject.put("projectUnfinishReason", academy.getProjectUnfinishReason());// 分析未完成原因
        jsonObject.put("projectImplementExperience", academy.getProjectImplementExperience());// 项目实施经验
        return jsonObject;
    }

    @Override
    public void cleanAndSaveAcceptAcademy(SrpmsProjectAcceptAcademy academy, Long acceptId) {
        deleteAcceptAcademyAcceptId(acceptId);
        saveAcceptAcademy(academy);
    }

    public void deleteAcceptAcademyAcceptId(Long acceptId) {
        UpdateWrapper<SrpmsProjectAcceptAcademy> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(SrpmsProjectAcceptAcademy.ACCEPT_ID, acceptId);
        this.remove(updateWrapper);
    }


    public void saveAcceptAcademy(SrpmsProjectAcceptAcademy academy) {
        this.save(academy);
    }
    /**
     * 根据验收ID查询院基科费信息
     *
     * @param id
     * @return
     */
    public SrpmsProjectAcceptAcademy selectList(Long id) {
        QueryWrapper<SrpmsProjectAcceptAcademy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAcceptAcademy.ACCEPT_ID, id);
        return srpmsProjectAcceptAcademyMapper.selectOne(queryWrapper);
    }
}


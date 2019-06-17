package com.deloitte.services.srpmp.project.accept.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAcceptSchool;
import com.deloitte.services.srpmp.project.accept.mapper.SrpmsProjectAcceptSchoolMapper;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :  SrpmsProjectAcceptSchool服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectAcceptSchoolServiceImpl extends ServiceImpl<SrpmsProjectAcceptSchoolMapper, SrpmsProjectAcceptSchool> implements ISrpmsProjectAcceptSchoolService {


    @Autowired
    private SrpmsProjectAcceptSchoolMapper srpmsProjectAcceptSchoolMapper;

    /**
     * 根据验收ID查询校基科费验收数据service接口
     *
     * @param acceptId
     * @return
     */
    @Override
    public JSONObject queryByAcceptId(Long acceptId) {
        SrpmsProjectAcceptSchool school = selectList(acceptId);
        if(school == null) {
            throw new BaseException(SrpmsErrorType.ACCEPT_NO_DATA);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("projectAbstract", school.getProjectAbstract());// 项目摘要
        jsonObject.put("projectContentIndicators", school.getProjectContentIndicators());// 研究内容及考核指标执行情况描述
        jsonObject.put("projectGainResult", school.getProjectGainResult());// 研究工作取得的重要进展与成果
        jsonObject.put("projectTeamConstruction", school.getProjectTeamConstruction());// 团队建设，人才培养
        jsonObject.put("projectQuestionAdvice", school.getProjectQuestionAdvice());// 存在问题及建议
        jsonObject.put("projectResultDirectory", JSONArray.parseArray(school.getProjectResultDirectory()));// 项目成果目录
        jsonObject.put("applyFunds", school.getApplyFunds());// 资助金额
        return jsonObject;
    }

    @Override
    public void cleanAndSaveAcceptSchool(SrpmsProjectAcceptSchool school, Long acceptId) {
        deleteAcceptSchoolAcceptId(acceptId);
        saveAcceptSchool(school);
    }

    public void deleteAcceptSchoolAcceptId(Long acceptId) {
        UpdateWrapper<SrpmsProjectAcceptSchool> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(SrpmsProjectAcceptSchool.ACCEPT_ID, acceptId);
        this.remove(updateWrapper);
    }


    public void saveAcceptSchool(SrpmsProjectAcceptSchool school) {
        this.save(school);
    }
    /**
     * 根据验收ID查询校基科费信息
     *
     * @param id
     * @return
     */
    public SrpmsProjectAcceptSchool selectList(Long id) {
        QueryWrapper<SrpmsProjectAcceptSchool> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectAcceptSchool.ACCEPT_ID, id);
        return srpmsProjectAcceptSchoolMapper.selectOne(queryWrapper);
    }
}


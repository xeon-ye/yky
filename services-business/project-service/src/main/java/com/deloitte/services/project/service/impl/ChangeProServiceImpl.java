package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ChangeProQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.mapper.ChangeProMapper;
import com.deloitte.services.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ChangePro服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ChangeProServiceImpl extends ServiceImpl<ChangeProMapper, ChangePro> implements IChangeProService {


    @Autowired
    private ChangeProMapper changeProMapper;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    public IReplyService replyService;

    @Autowired
    public IChangeNoteService changeNoteService;

    @Autowired
    public IActService actService;
    @Autowired
    public ISubactService subactService;
    @Autowired
    public IActBakService actBakService;
    @Autowired
    public ISubactBakService subactBakService;

    @Autowired
    public IBudgetService budgetService;
    @Autowired
    public IBudgetBakService budgetBakService;
    @Autowired
    public IEconomicService economicService;
    @Autowired
    public IChangeBudgetBakService changeBudgetBakService;

    @Autowired
    public IPerformanceService performanceService;
    @Autowired
    public IPerformanceBakService performanceBakService;

    @Autowired
    public IPersonService personService;
    @Autowired
    public IPersonBakService personBakService;
    @Autowired
    public IMaintBudgetService maintBudgetService;
    @Autowired
    public IMaintReplyService maintReplyService;

    @Override
    public IPage<ChangePro> selectPage(ChangeProQueryForm queryForm ) {
        QueryWrapper<ChangePro> queryWrapper = new QueryWrapper <ChangePro>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeProMapper.selectPage(new Page<ChangePro>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<ChangePro> selectList(ChangeProQueryForm queryForm) {
        QueryWrapper<ChangePro> queryWrapper = new QueryWrapper <ChangePro>();
        //getQueryWrapper(queryWrapper,queryForm);
        return changeProMapper.selectList(queryWrapper);
    }

    @Override
    public List<ChangePro> getChangeByProjectId(String projectId) {
        QueryWrapper<ChangePro> queryWrapper = new QueryWrapper <ChangePro>();
        queryWrapper.eq(ChangePro.PROJECT_ID,projectId);
        return changeProMapper.selectList(queryWrapper);
    }

    @Override
    public List<ChangePro> getChangeByReplyId(String replyId) {
        QueryWrapper<ChangePro> queryWrapper = new QueryWrapper <ChangePro>();
        queryWrapper.eq(ChangePro.REPLY_ID,replyId);
        return changeProMapper.selectList(queryWrapper);
    }

    @Override
    public List<ChangePro> getChangeByMaintenceId(String maintenceId) {
        QueryWrapper<ChangePro> queryWrapper = new QueryWrapper <ChangePro>();
        queryWrapper.eq(ChangePro.MAINTENCEID,maintenceId);
        return changeProMapper.selectList(queryWrapper);
    }

    @Override
    public ProjectChangeVo toSaveBak(ProjectChangeVo projectChangeVo) {
        ProjectChangeVo vo = new ProjectChangeVo();
        String mainId ="";
        String replyId = "";
        String projectId = projectChangeVo.getProjectId();
        String mainMark = projectChangeVo.getMainMark();
        //项目维护
        if("80001".equals(mainMark)) {
            mainId = projectChangeVo.getMainId();
            List<ChangeBudgetBak> changeBudgetBaks = changeBudgetBakService.getList(mainId);
            if(changeBudgetBaks.size() > 0) {
                changeBudgetBakService.deleteList(mainId);
            }
            List<MaintBudgetVo> maintBudgetVos = projectChangeVo.getMaintBudgetVos();
            if(maintBudgetVos.size()>0){
                for (MaintBudgetVo maintBudgetVo : maintBudgetVos) {
                    ChangeBudgetBak changeBudgetBak = new BeanUtils<ChangeBudgetBak>().copyObj(maintBudgetVo,ChangeBudgetBak.class);
                    changeBudgetBak.setMaintId(mainId);
                    changeBudgetBakService.save(changeBudgetBak);
                }
            }

            List<PersonBak> personBaks = personBakService.getAllByProId(projectId);
            if(personBaks.size() > 0) {
                personBakService.deleteAllByProId(projectId);
            }

            List<PersonVo> personList = projectChangeVo.getPersonVoList();
            for (PersonVo personVo : personList) {
                PersonBak personBak = new BeanUtils<PersonBak>().copyObj(personVo,PersonBak.class);
                personBak.setProjectId(projectId);
                personBakService.save(personBak);
            }

        } else {
            replyId = projectChangeVo.getReplyId();
            //支出 保存到备份表
            List<ActBak>  actBaks = actBakService.getListByRepId(replyId);
            if(actBaks.size() > 0) {
                actBakService.deleteListByRepId(replyId);
                for (ActBak actBak : actBaks) {
                    subactBakService.deleteALLList(String.valueOf(actBak.getId()));
                }
            }

            List<ActVo> actList = projectChangeVo.getActVoList();
            List<SubactVo> subactVoList = projectChangeVo.getSubactsVoList();
            if(actList.size() > 0) {
                for (ActVo actVo : actList) {
                    ActBak actBak = new BeanUtils<ActBak>().copyObj(actVo,ActBak.class);
                    actBak.setReplyId(replyId);
                    actBakService.save(actBak);
                    actBak.setActId(String.valueOf(actBak.getId()));
                    actBakService.updateById(actBak);

                    for (SubactVo subactVo : subactVoList) {
                        if(subactVo.getIsRelated().equals(actVo.getIsRelated())) {
                            SubactBak subactBak = new BeanUtils<SubactBak>().copyObj(subactVo,SubactBak.class);
                            subactBak.setActId(actBak.getActId());
                            subactBakService.save(subactBak);
                        }
                    }
                }
            }

            //预算 删除 只获取当年的数据
            List<BudgetBak> budgetBaks = budgetBakService.selectByRepId(replyId);
            if(budgetBaks.size() > 0) {
                changeBudgetBakService.deleteList(replyId);
            }

            JSONObject jsonObject =  projectChangeVo.getBudgetJsonObject();
            String year = String.valueOf(DateUtil.getCurrentYear());
            if(jsonObject.containsKey(year)) {
                JSONArray jsonArray = jsonObject.getJSONArray(year);
                for(int j=0;j<jsonArray.size();j++){
                    BudgetVo budgetVo = JSONObject.parseObject(JSON.toJSONString(jsonArray.getJSONObject(j)), BudgetVo.class);
                    BudgetBak budgetBak = new BeanUtils<BudgetBak>().copyObj(budgetVo,BudgetBak.class);
                    budgetBak.setReplayId(replyId);
                    budgetBakService.save(budgetBak);
                }
            }

            //绩效
            List<PerformanceBak> performanceBaks = performanceBakService.getAllByRepId(replyId);
            if(performanceBaks.size() > 0){
                performanceBakService.deleteAllList(replyId);
            }

            List<PerformanceVo> performanceList = projectChangeVo.getPerformanceVoList();
            for (PerformanceVo performanceVo : performanceList) {
                PerformanceBak performanceBak = new BeanUtils<PerformanceBak>().copyObj(performanceVo,PerformanceBak.class);
                performanceBak.setReplayId(replyId);
                performanceBakService.save(performanceBak);
            }

            //人员
            List<PersonBak> personBaks = personBakService.getAllByRepId(replyId);
            if(personBaks.size() > 0) {
                personBakService.deleteAllByRepId(replyId);
            }

            List<PersonVo> personList = projectChangeVo.getPersonVoList();
            for (PersonVo personVo : personList) {
                PersonBak personBak = new BeanUtils<PersonBak>().copyObj(personVo,PersonBak.class);
                personBak.setReplyId(replyId);
                personBakService.save(personBak);
            }
        }
        return  vo;
    }

}


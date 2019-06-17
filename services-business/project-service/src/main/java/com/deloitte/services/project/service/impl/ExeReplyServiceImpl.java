package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ExeReplyQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.mapper.ExeReplyMapper;
import com.deloitte.services.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  ExeReply服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExeReplyServiceImpl extends ServiceImpl<ExeReplyMapper, ExeReply> implements IExeReplyService {


    @Autowired
    private ExeReplyMapper exeReplyMapper;
    @Autowired
    public IChangeProService changeProService;
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
    public IBudgetService budgetService;
    @Autowired
    public IBudgetBakService budgetBakService;
    @Autowired
    public ISubactBakService subactBakService;
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
    public IExeRepHisService exeRepHisService;
    @Autowired
    public IMaintBudgetService maintBudgetService;
    @Autowired
    public IMaintReplyService maintReplyService;

    @Override
    public IPage<ExeReply> selectPage(ExeReplyQueryForm queryForm ) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeReplyMapper.selectPage(new Page<ExeReply>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExeReply> selectList(ExeReplyQueryForm queryForm) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeReplyMapper.selectList(queryWrapper);
    }

    @Override
    public List<ExeReply> getAllByMainId(String mainId) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        queryWrapper.eq(ExeReply.MAINTENANCE_ID,mainId);
        return exeReplyMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteAllByMainId(String mainId) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        queryWrapper.eq(ExeReply.MAINTENANCE_ID,mainId);
        exeReplyMapper.delete(queryWrapper);
    }

    @Override
    public List<ExeReply> getAllByRepId(String replyId) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        queryWrapper.eq(ExeReply.REPLY_ID,replyId);
        return exeReplyMapper.selectList(queryWrapper);
}

    @Override
    public void deleteAllByRepId(String replyId) {
        QueryWrapper<ExeReply> queryWrapper = new QueryWrapper <ExeReply>();
        queryWrapper.eq(ExeReply.REPLY_ID,replyId);
        exeReplyMapper.delete(queryWrapper);
    }

    @Override
    public ProjectChangeVo toSubmit(ProjectChangeVo projectChangeVo) {
        ProjectChangeVo vo = new ProjectChangeVo();
        String projectId = projectChangeVo.getProjectId();
        String mainId = projectChangeVo.getMainId();
        String replyId = projectChangeVo.getReplyId();
        String mainMark = projectChangeVo.getMainMark();
        //项目维护 80001
        ExeReplyVo exeReplyVo = projectChangeVo.getExeReplyVos().get(0);
        if("80001".equals(mainMark)) {
            List<ExeReply> exeReplies = this.getAllByMainId(mainId);
            if(exeReplies.size() > 0) {
                for (ExeReply exeReply : exeReplies) {
                    exeReply.setExeReplyCode(exeReplyVo.getExeReplyCode());
                    exeReply.setExeReplyName(exeReplyVo.getExeReplyName());
                    exeReply.setExeReplyAdv(exeReplyVo.getExeReplyAdv());
                    exeReply.setMaintenanceId(mainId);
                    this.updateById(exeReply);
                }
                ExeRepHis exeRepHis = new ExeRepHis();
                exeRepHis.setReplyEndCode(exeReplyVo.getExeReplyCode());
                exeRepHis.setReplyEndName(exeReplyVo.getExeReplyName());
                exeRepHis.setReplyAdv(exeReplyVo.getExeReplyAdv());
                exeRepHisService.save(exeRepHis);
            }
        } else {
            List<ExeReply> exeReplies = this.getAllByRepId(replyId);
            if (exeReplies.size() > 0) {
                for (ExeReply exeReply : exeReplies) {
                    exeReply.setExeReplyCode(exeReplyVo.getExeReplyCode());
                    exeReply.setExeReplyName(exeReplyVo.getExeReplyName());
                    exeReply.setExeReplyAdv(exeReplyVo.getExeReplyAdv());
                    exeReply.setReplyId(replyId);
                    this.updateById(exeReply);
                }
                ExeRepHis exeRepHis = new ExeRepHis();
                exeRepHis.setReplyEndCode(exeReplyVo.getExeReplyCode());
                exeRepHis.setReplyEndName(exeReplyVo.getExeReplyName());
                exeRepHis.setReplyAdv(exeReplyVo.getExeReplyAdv());
                exeRepHisService.save(exeRepHis);
            }
        }
        //同意/不同意
        if("10001".equals(exeReplyVo.getExeReplyCode())) {
            if("80002".equals(mainMark)) {
                //支出
                List<ActVo> actVos = projectChangeVo.getActVoList();
                List<SubactVo> subactVoList = projectChangeVo.getSubactsVoList();
                List<ActBakVo> actBakVos = projectChangeVo.getActBakVoList();
                List<SubactBakVo> subactBakVos = projectChangeVo.getSubBakLists();

                List<ActBak> actBaks = actBakService.getListByRepId(replyId);
                if(actBaks.size() > 0) {
                    List<Act> actList = actService.getRepActList(replyId);
                    for (Act act : actList) {
                        subactService.deleteSubActList(String.valueOf(act.getId()));
                    }
                    for (ActBak actBak : actBaks) {
                        subactBakService.deleteALLList(String.valueOf(actBak.getId()));
                    }
                    actService.deleteRepList(replyId);
                    actBakService.deleteListByRepId(replyId);

                    for (ActBakVo actBakVo : actBakVos) {
                        Act act = new BeanUtils<Act>().copyObj(actBakVo,Act.class);
                        act.setReplyId(replyId);
                        actService.save(act);
                        act.setActId(String.valueOf(act.getId()));
                        actService.updateById(act);
                        for (SubactBakVo subactBakVo : subactBakVos) {
                            if(subactBakVo.getIsRelated().equals(actBakVo.getIsRelated())) {
                                Subact subact = new BeanUtils<Subact>().copyObj(subactBakVo,Subact.class);
                                subact.setActId(act.getActId());
                                subactService.save(subact);
                            }
                        }
                    }

                    for (ActVo actVo : actVos) {
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

                //预算
                JSONObject budgetObj = projectChangeVo.getBudgetJsonObject();
                JSONObject budgetBakObj = projectChangeVo.getBudgetBakJsonObject();
                String year = String.valueOf(DateUtil.getCurrentYear());
                Map map = new HashMap();
                map.put("budgetYear",year);
                map.put("replyId",replyId);
                if(budgetBakObj.containsKey(year)) {
                    budgetService.deleteByRepIdAndYear(map);
                    budgetBakService.deleteByRepIdAndYear(map);
                    JSONArray budgetBakArray = budgetBakObj.getJSONArray(year);
                    for(int j=0;j<budgetBakArray.size();j++){
                        BudgetBakVo budgetBakVo = JSONObject.parseObject(JSON.toJSONString(budgetBakArray.getJSONObject(j)), BudgetBakVo.class);
                        Budget budget = new BeanUtils<Budget>().copyObj(budgetBakVo,Budget.class);
                        budget.setReplayId(replyId);
                        budgetService.save(budget);
                    }

                    JSONArray budgetArray = budgetObj.getJSONArray(year);
                    for(int j=0;j<budgetArray.size();j++){
                        BudgetVo budgetVo = JSONObject.parseObject(JSON.toJSONString(budgetArray.getJSONObject(j)), BudgetVo.class);
                        BudgetBak budgetBak = new BeanUtils<BudgetBak>().copyObj(budgetVo,BudgetBak.class);
                        budgetBak.setReplayId(replyId);
                        budgetBakService.save(budgetBak);
                    }
                }

                //绩效
                List<Performance> performanceList = performanceService.getRepPerformanceList(replyId);
                List<PerformanceBak> performanceBaks = performanceBakService.getAllByRepId(replyId);
                if(performanceBaks.size() > 0) {
                    performanceService.deleteRepList(replyId);
                    performanceBakService.deleteAllByRepId(replyId);
                    for (PerformanceBak performanceBak : performanceBaks) {
                        Performance performance = new BeanUtils<Performance>().copyObj(performanceBak,Performance.class);
                        performanceService.save(performance);
                    }
                    for (Performance performance : performanceList) {
                        PerformanceBak performanceBak = new BeanUtils<PerformanceBak>().copyObj(performance,PerformanceBak.class);
                        performanceBakService.save(performanceBak);
                    }
                }

                //人员
                List<Person> personList = personService.getReplyList(replyId);
                List<PersonBak> personBaks = personBakService.getAllByRepId(replyId);
                if(personBaks.size() > 0) {
                    personService.deleteReplyList(replyId);
                    personBakService.deleteAllByRepId(replyId);
                    for (PersonBak personBak : personBaks) {
                        Person person = new BeanUtils<Person>().copyObj(personBak,Person.class);
                        personService.save(person);
                    }
                    for (Person person : personList) {
                        PersonBak personBak = new BeanUtils<PersonBak>().copyObj(person,PersonBak.class);
                        personBakService.save(personBak);
                    }
                }
            } else {
                //预算
                List<MaintBudget> maintBudgetList = maintBudgetService.getAllList(mainId);
                List<ChangeBudgetBak> changeBudgetBaks = changeBudgetBakService.getList(mainId);
                if(changeBudgetBaks.size() > 0) {
                    maintBudgetService.deleteAllList(mainId);
                    for (ChangeBudgetBak changeBudgetBak : changeBudgetBaks) {
                        MaintBudget maintBudget = new BeanUtils<MaintBudget>().copyObj(changeBudgetBak,MaintBudget.class);
                        maintBudget.setMaintId(mainId);
                        maintBudgetService.save(maintBudget);
                    }

                    changeBudgetBakService.deleteList(mainId);
                    for (MaintBudget maintBudget : maintBudgetList) {
                        ChangeBudgetBak changeBudgetBak = new BeanUtils<ChangeBudgetBak>().copyObj(maintBudget,ChangeBudgetBak.class);
                        changeBudgetBak.setMaintBudgetId(mainId);
                        changeBudgetBakService.save(changeBudgetBak);
                    }
                }
                //人员
                List<Person> personList = personService.getMainList(projectId);
                List<PersonBak> personBaks = personBakService.getAllByProId(projectId);
                if(personBaks.size() > 0) {
                    personService.deleteReplyList(mainId);
                    personBakService.deleteAllByProId(projectId);
                    for (PersonBak personBak : personBaks) {
                        Person person = new BeanUtils<Person>().copyObj(personBak,Person.class);
                        personService.save(person);
                    }
                    for (Person person : personList) {
                        PersonBak personBak = new BeanUtils<PersonBak>().copyObj(person,PersonBak.class);
                        personBakService.save(personBak);
                    }
                }
            }
        }
        return vo;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExeReply> getQueryWrapper(QueryWrapper<ExeReply> queryWrapper,ExeReplyQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getExeReplyId())){
            queryWrapper.eq(ExeReply.EXE_REPLY_ID,queryForm.getExeReplyId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ExeReply.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ExeReply.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getExeReplyCode())){
            queryWrapper.eq(ExeReply.EXE_REPLY_CODE,queryForm.getExeReplyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getExeReplyName())){
            queryWrapper.eq(ExeReply.EXE_REPLY_NAME,queryForm.getExeReplyName());
        }
        if(StringUtils.isNotBlank(queryForm.getExeReplyAdv())){
            queryWrapper.eq(ExeReply.EXE_REPLY_ADV,queryForm.getExeReplyAdv());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExeReply.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExeReply.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExeReply.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExeReply.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ExeReply.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ExeReply.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ExeReply.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(ExeReply.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ExeReply.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(ExeReply.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}


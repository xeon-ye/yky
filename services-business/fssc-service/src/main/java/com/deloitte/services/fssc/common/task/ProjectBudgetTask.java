package com.deloitte.services.fssc.common.task;

import com.deloitte.platform.api.fssc.base.param.BaseBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.feign.SrpmsProjectBudgetAccountFeignService;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetAccountQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.base.entity.BaseBudgetAccount;
import com.deloitte.services.fssc.base.service.IBaseBudgetAccountService;
import com.deloitte.services.fssc.common.service.FsscRedisService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.util.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
@Slf4j
public class ProjectBudgetTask {

    @Autowired
    SrpmsProjectBudgetAccountFeignService budgetAccountFeignService;

    @Autowired
    IBaseBudgetAccountService budgetAccountService;

    @Autowired
    RedisTemplate redisTemplate;

    public static final String PROJECT_BUDGET_KEY = "FSSC_PROJECT_BUDGET_KEY";

    /**
     * 每天晚上11点执行 获取全量项目预算科目
     */
    @Scheduled(cron = "0 0 23 * * ?")
    @Transactional
    public void syncProjectBudget(){
        try {
            Object flag = redisTemplate.opsForValue().get(PROJECT_BUDGET_KEY);
            if(flag != null && "Y".equals(flag.toString())){
                log.info("已经有其他机器执行获取全量项目预算科目!");
                return;
            }
            redisTemplate.opsForValue().set(PROJECT_BUDGET_KEY,"Y",1, TimeUnit.HOURS);
            log.info("获取全量项目预算科目开始");
            Result<List<SrpmsProjectBudgetAccountVo>> budgetResult = budgetAccountFeignService.list(new SrpmsProjectBudgetAccountQueryForm());
            AssertUtils.asserts(budgetResult.isSuccess(),FsscErrorType.GET_ALL_PROJECT_BUDGET_FAIL);
            AssertUtils.asserts(CollectionUtils.isNotEmpty(budgetResult.getData()),FsscErrorType.GET_ALL_PROJECT_BUDGET_NO_DATA);
            BaseBudgetAccountQueryForm queryForm = new BaseBudgetAccountQueryForm();
            queryForm.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
            List<BaseBudgetAccount> oldBudgetAccountList = budgetAccountService.selectList(queryForm);
            Map<String,BaseBudgetAccount> accountCodeBeanMap = new HashMap<>(oldBudgetAccountList.size());
            List<BaseBudgetAccount> newBudgetAccountList = new ArrayList<>();
            for (BaseBudgetAccount budgetAccount : oldBudgetAccountList){
                accountCodeBeanMap.put(budgetAccount.getCode(),budgetAccount);
            }
            for(SrpmsProjectBudgetAccountVo projectBudgetAccountVo : budgetResult.getData()){
                BaseBudgetAccount budgetAccount = accountCodeBeanMap.get(projectBudgetAccountVo.getBudgetAccountCode());
                if (budgetAccount == null){
                    budgetAccount = new BaseBudgetAccount();
                    budgetAccount.setCode(projectBudgetAccountVo.getBudgetAccountCode());
                    budgetAccount.setName(projectBudgetAccountVo.getBudgetAccountName());
                    budgetAccount.setBudgetType(FsscEums.BUDGET_TYPE_PROJECT.getValue());
                    budgetAccount.setDescription(projectBudgetAccountVo.getBudgetAccountName());
                    budgetAccount.setExt1(projectBudgetAccountVo.getBudgetAccountYear());
                    budgetAccount.setValidFlag(projectBudgetAccountVo.getBudgetAccountStatus() == 1 ? FsscEums.VALID.getValue() : FsscEums.UN_VALID.getValue());
                    newBudgetAccountList.add(budgetAccount);
                }else{
                    budgetAccount.setName(projectBudgetAccountVo.getBudgetAccountName());
                    budgetAccount.setValidFlag(projectBudgetAccountVo.getBudgetAccountStatus() == 1 ? FsscEums.VALID.getValue() : FsscEums.UN_VALID.getValue());
                    budgetAccount.setExt1(projectBudgetAccountVo.getBudgetAccountYear());
                    newBudgetAccountList.add(budgetAccount);
                }
            }
            if (CollectionUtils.isNotEmpty(newBudgetAccountList)){
                budgetAccountService.saveOrUpdateBatch(newBudgetAccountList);
            }
        }catch (Exception e){
            log.error("同步项目预算科目失败,{}",e.getMessage());
        }finally {
            redisTemplate.opsForValue().set(PROJECT_BUDGET_KEY,"N",1, TimeUnit.HOURS);
        }
    }
}

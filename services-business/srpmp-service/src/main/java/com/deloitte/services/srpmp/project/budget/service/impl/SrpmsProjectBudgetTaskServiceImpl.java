package com.deloitte.services.srpmp.project.budget.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetTaskMapper;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetTask;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  ISrpmsProjectBudgetTaskService服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectBudgetTaskServiceImpl extends ServiceImpl<SrpmsProjectBudgetTaskMapper, SrpmsProjectBudgetTask> implements ISrpmsProjectBudgetTaskService {

}
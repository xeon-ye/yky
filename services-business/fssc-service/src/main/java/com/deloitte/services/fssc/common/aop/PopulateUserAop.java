package com.deloitte.services.fssc.common.aop;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.business.pe.entity.PerSelfAssessment;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.DocumentNumberUtil;
import com.deloitte.services.fssc.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
@Aspect
@Slf4j
public class PopulateUserAop {

    @Autowired
    private FsscCommonServices commonServices;

    /**
     * 设置更新人 创建人
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.baomidou.mybatisplus.extension.service.IService.saveOrUpdate(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            Object o = args[0];
            setValues(o);
            //单据编号
            String getId = "getId";
            Method getFormId = null;
            try {
                getFormId = o.getClass().getMethod(getId, null);
            }catch (NoSuchMethodException e){
                log.error("获取id出错:{}",e.getMessage());
            }
            Object invoke = getFormId.invoke(o);
            if(invoke==null){
                String tableName = o.getClass().getAnnotation(TableName.class).value();
                if(StringUtil.isNotEmpty(tableName)){
                    String simpleName = DocumentNumberUtil.KEYS.get(tableName);
                    if(StringUtil.isNotEmpty(simpleName)){
                        String budgetCode = "";
                        try {
                            String deptCode;
                            if (tableName.equals(FsscTableNameEums.PER_SELF_ASSESSMENT.getValue())){
                                deptCode = ((PerSelfAssessment)o).getDoUnitCode();
                            }else {
                                deptCode = commonServices.getCurrentDept().getDeptCode();
                            }
                            budgetCode = DocumentNumberUtil.BUDGETCODES.get(deptCode);
                        }catch (Exception e){
                            log.error("获取部门失败,{}", ExceptionUtil.getStackTraceAsString(e));
                        }
                        String documentNum = DocumentNumberUtil.generateDocumentNum(budgetCode, tableName);
                        Method method = o.getClass().getMethod("setDocumentNum", String.class);
                        method.invoke(o,documentNum);
                    }
                }
            }
        }
        return pjp.proceed();
    }


    /**
     * 批量设置更新人 创建人
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.baomidou.mybatisplus.extension.service.IService.saveOrUpdateBatch(..))")
    public Object aroundBatch(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            List list = (List) args[0];
            log.info("参数:{}", JSON.toJSONString(list));
            for (Object o : list) {
                setValues(o);
                commonServices.setAccountCode(o);
            }

        }

        return pjp.proceed();
    }


    private void setValues(Object o) throws Throwable {
        String setCreateUserName = "setCreateUserName";
        String setCreateBy = "setCreateBy";
        String setUpdateBy = "setUpdateBy";
        String getId = "getId";
        String setDeptId = "setDeptId";
        String setDeptName = "setDeptName";
        String setDeptCode = "setDeptCode";
        String setUnitName = "setUnitName";
        String setUnitId = "setUnitId";
        String setUnitCode = "setUnitCode";

        //当前用户
        UserVo currentUser =null;
        try {
            currentUser = commonServices.getCurrentUser();
        }catch (Exception e){
            log.error("获取用户失败");
        }
        //当前单位
        DeptVo currentDept =null;
        try {
            currentDept = commonServices.getCurrentDept();
        }catch (Exception e){
            log.error("获取单位失败");
        }
        Class<?> aClass = o.getClass();
        Method createUserName = null;
        Method createBy = null;
        Method updateBy = null;
        Method getFormId = null;

        Method deptId=null;
        Method deptName=null;
        Method deptCode=null;
        Method unitName=null;
        Method unitId=null;
        Method unitCode=null;
        try {
            createBy = aClass.getMethod(setCreateBy, java.lang.Long.class);
            updateBy = aClass.getMethod(setUpdateBy, java.lang.Long.class);
            createUserName = aClass.getMethod(setCreateUserName, java.lang.String.class);
            getFormId = aClass.getMethod(getId, null);
            deptId = aClass.getMethod(setDeptId, java.lang.Long.class);
            deptName = aClass.getMethod(setDeptName, java.lang.String.class);
            deptCode = aClass.getMethod(setDeptCode, java.lang.String.class);
            unitName = aClass.getMethod(setUnitName, java.lang.String.class);
            unitId = aClass.getMethod(setUnitId, java.lang.Long.class);
            unitCode = aClass.getMethod(setUnitCode, java.lang.String.class);
        }catch (NoSuchMethodException e){
            log.error("填充数据报错{}",e.getMessage());
        }


        if (getFormId != null) {
            Long id = StringUtil.getLong(getFormId.invoke(o));
            if (id == null) {
                //用户名
                if (createUserName != null&&createBy != null&&currentUser!=null) {
                    createUserName.invoke(o, currentUser.getName());
                    createBy.invoke(o, StringUtil.castTolong(currentUser.getId()));
                }

                //部门 单位信息
                if (deptId != null && deptCode != null && deptName != null && unitName != null && unitCode != null
                        && unitId != null) {

                    //当前部门
                    DeputyAccountVo currentDeputyAccount = currentUser.getCurrentDeputyAccount();
                    if(currentDeputyAccount!=null){
                        String getDept="getDeptId";
                        Method getDeptMethod=null;
                        try {
                            getDeptMethod = aClass.getMethod(getDept,null);
                        }catch (Exception e){
                            log.error("获取部门id方法失败,{}",e.getMessage());
                        }
                        if(getDeptMethod!=null){
                            Object deptIdd = getDeptMethod.invoke(o);
                            if(deptIdd==null){
                                deptId.invoke(o,currentDeputyAccount.getOrgId());
                                deptCode.invoke(o,currentDeputyAccount.getOrgCode());
                                deptName.invoke(o,currentDeputyAccount.getOrgName());
                            }
                        }
                    }

                    if(currentDept!=null){
                        unitId.invoke(o,StringUtil.castTolong(currentDept.getId()));
                        unitCode.invoke(o,currentDept.getDeptCode());
                        unitName.invoke(o,currentDept.getDeptName());
                    }
                }
            } else {
                if (updateBy != null&&currentUser!=null) {
                    updateBy.invoke(o, StringUtil.castTolong(currentUser.getId()));
                }
            }
        }

    }

}

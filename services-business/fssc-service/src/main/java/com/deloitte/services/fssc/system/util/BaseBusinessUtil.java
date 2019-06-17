package com.deloitte.services.fssc.system.util;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.deloitte.services.fssc.util.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 业务工具类
 */
public class BaseBusinessUtil {

    /**
     * 判断提交状态是否符合规定
     * @param isValid
     * @param doStatus
     */
    public static void checkAuditStatus(String isValid,String doStatus){
        //提交时
        if (FsscEums.SUBMIT.getValue().equals(doStatus) &&!FsscEums.NEW.getValue().equals(isValid)) {
            throw new FSSCException(FsscErrorType.ONLY_SUBMIT_NEW);
        }
        //失效时 当前状态不为有效 抛出只能失效当前有效的记录
        if (FsscEums.UN_VALID.getValue().equals(doStatus) &&
                !FsscEums.VALID.getValue().equals(isValid)) {
            throw new FSSCException(FsscErrorType.ONLY_SUBMIT_UNVALID);
        }
        //撤回时 当前状态不为提交 抛出只能撤回已提交的记录
        if (FsscEums.NEW.getValue().equals(doStatus) &&
                !FsscEums.SUBMIT.getValue().equals(isValid)) {
            throw new FSSCException(FsscErrorType.ONLY_RETURN_NEW);
        }
        //重新启用
        if(FsscEums.VALID.getValue().equals(doStatus) &&
                !FsscEums.UN_VALID.getValue().equals(isValid)){
            throw new FSSCException(FsscErrorType.ONLY_RESET_UNVALID);
        }

    }


    /**
     * 执行修改状态
     * @param object
     * @param doStatus
     * @param currentStatus
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void doModifyStatus(Object object,String doStatus,String currentStatus) {
        try {
            String setIsValid="setIsValid";
            String setAuditStatus="setAuditStatus";
            Class<?> clazz = object.getClass();
            Method setValid = clazz.getMethod(setIsValid, java.lang.String.class);
            Method setAudit = clazz.getMethod(setAuditStatus, java.lang.String.class);
            if (FsscEums.NEW.getValue().equals(doStatus)) {
                //撤回  失效按钮的提交
                if (FsscEums.UN_VALID.getValue().equals(currentStatus)) {
                    setValid.invoke(object, FsscEums.VALID.getValue());
                }
                //撤回 提交的
                if (FsscEums.SUBMIT.getValue().equals(currentStatus)) {
                    setValid.invoke(object, FsscEums.NEW.getValue());
                }
                //撤回 重新启用失效的
                if (FsscEums.VALID.getValue().equals(currentStatus)) {
                    setValid.invoke(object, FsscEums.UN_VALID.getValue());
                }
                setAudit.invoke(object, "");
            } else if (FsscEums.SUBMIT.getValue().equals(doStatus)) {
                //提交新建的
                setValid.invoke(object, FsscEums.SUBMIT.getValue());
                setAudit.invoke(object, FsscEums.SUBMIT.getValue());
            } else if (FsscEums.UN_VALID.getValue().equals(doStatus)) {
                //设置提交失效审核状态
                //todo 判断其他是否使用过这条记录
                setValid.invoke(object,FsscEums.SUBMIT.getValue());
                setAudit.invoke(object,FsscEums.UN_VALID.getValue());
            } else if(FsscEums.VALID.getValue().equals(doStatus)){
                setValid.invoke(object, FsscEums.SUBMIT.getValue());
                setAudit.invoke(object, FsscEums.VALID.getValue());
            }
        }catch (NoSuchMethodException e){
            throw new FSSCException(FsscErrorType.NO_SUCH_METHOD_EXCEPTION);
        }catch (InvocationTargetException e){
            throw new FSSCException(FsscErrorType.INVOCATION_TARGET_EXCEPTION);
        }catch (IllegalAccessException e){
            throw new FSSCException(FsscErrorType.ILLEGAL_ACCESS_EXCEPTION);
        }


    }


    public static void refusedOrPass(String passOrRefused,
                                     String refusedReason,
                                     List<DataAuditHis> hisList,
                                     List objects){
        try {

        DataAuditHis his;
        //通过
        if (FsscEums.PASS.getValue().equals(passOrRefused)) {
            for (Object info : objects) {
                Method getAuditStatus =info.getClass().getMethod("getAuditStatus",null);
                Method getId =info.getClass().getMethod("getId",null);
                Method setAuditStatus =info.getClass().getMethod("setAuditStatus",java.lang.String.class);
                Method setIsValid =info.getClass().getMethod("setIsValid",java.lang.String.class);
                String auditStatus = StringUtil.objectToString(getAuditStatus.invoke(info));
                if (FsscEums.SUBMIT.getValue().equals(auditStatus)||
                        FsscEums.VALID.getValue().equals(auditStatus)) {
                    setIsValid.invoke(info,FsscEums.VALID.getValue());
                }
                if (FsscEums.UN_VALID.getValue().equals(auditStatus)) {
                    setIsValid.invoke(info,FsscEums.UN_VALID.getValue());
                }
                setAuditStatus.invoke(info,"");
                //写入历史记录
                his = new DataAuditHis();
                his.setDocumentId(Long.valueOf(StringUtil.objectToString(getId.invoke(info))));
                his.setDocumentType(info.getClass().getAnnotation(TableName.class).value());
                his.setAuditOpin(FsscEums.PASS.getDescription());
                hisList.add(his);
            }

        }
        //拒绝
        if (FsscEums.REFUSED.getValue().equals(passOrRefused)) {
            for (Object info : objects) {
                Method getAuditStatus =info.getClass().getMethod("getAuditStatus",null);
                Method setAuditStatus =info.getClass().getMethod("setAuditStatus",java.lang.String.class);
                Method setIsValid =info.getClass().getMethod("setIsValid",java.lang.String.class);
                Method getId =info.getClass().getMethod("getId",null);
                String auditStatus = StringUtil.objectToString(getAuditStatus.invoke(info));

                if (FsscEums.UN_VALID.getValue().equals(auditStatus)) {
                    setIsValid.invoke(info,FsscEums.VALID.getValue());
                }
                if(FsscEums.VALID.getValue().equals(auditStatus)){
                    setIsValid.invoke(info,FsscEums.UN_VALID.getValue());
                }
                if (FsscEums.SUBMIT.getValue().equals(auditStatus)) {
                    setIsValid.invoke(info,FsscEums.NEW.getValue());
                }
                setAuditStatus.invoke(info,"");
                //写入历史记录
                his = new DataAuditHis();
                his.setDocumentId(Long.valueOf(StringUtil.objectToString(getId.invoke(info))));
                his.setDocumentType(info.getClass().getAnnotation(TableName.class).value());
                his.setAuditOpin(FsscEums.REFUSED.getDescription());
                his.setRefusedReson(refusedReason);
                hisList.add(his);
            }
        }
        }catch (NoSuchMethodException e){
            throw new FSSCException(FsscErrorType.NO_SUCH_METHOD_EXCEPTION);
        }catch (InvocationTargetException e){
            throw new FSSCException(FsscErrorType.INVOCATION_TARGET_EXCEPTION);
        }catch (IllegalAccessException e){
            throw new FSSCException(FsscErrorType.ILLEGAL_ACCESS_EXCEPTION);
        }
    }
}

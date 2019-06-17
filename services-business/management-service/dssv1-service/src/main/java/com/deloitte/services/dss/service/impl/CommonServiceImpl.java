package com.deloitte.services.dss.service.impl;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
//import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.service.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class CommonServiceImpl implements ICommonService {

    /**
     * 用户当前用户的单位
     * @return
     */
    /*@Override
    public UserVo getCurrentUser() {
        return UserUtil.getUserVo();
    }

    *//**
     * 获取当前用户的部门
     * @return
     *//*
    @Override
    public DeptVo getCurrentDept() {
        return UserUtil.getDept();
    }

    *//**
     * 获取当前用户的菜单
     * @return
     *//*
    public ResourceVo getMenu() {
        return UserUtil.getMenu();
    }
*/
}


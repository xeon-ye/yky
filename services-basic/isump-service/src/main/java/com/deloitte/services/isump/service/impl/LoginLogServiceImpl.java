package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.isump.entity.LoginLog;
import com.deloitte.services.isump.mapper.LoginLogMapper;
import com.deloitte.services.isump.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author : zhangdi
 * @Date : Create in 2019-05-24
 * @Description :  LoginLog服务实现类
 * @Modified :
 */
@Service
@Transactional
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {


    @Autowired
    private LoginLogMapper loginLogMapper;




}


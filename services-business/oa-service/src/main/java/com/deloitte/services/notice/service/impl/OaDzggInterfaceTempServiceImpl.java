package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.notice.param.OaDzggInterfaceTempQueryForm;
import com.deloitte.services.notice.entity.OaDzggInterfaceTemp;
import com.deloitte.services.notice.mapper.OaDzggInterfaceTempMapper;
import com.deloitte.services.notice.service.IOaDzggInterfaceTempService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-16
 * @Description :  OaDzggInterfaceTemp服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaDzggInterfaceTempServiceImpl extends ServiceImpl<OaDzggInterfaceTempMapper, OaDzggInterfaceTemp> implements IOaDzggInterfaceTempService {


    @Autowired
    private OaDzggInterfaceTempMapper oaDzggInterfaceTempMapper;

    @Override
    public IPage<OaDzggInterfaceTemp> selectPage(OaDzggInterfaceTempQueryForm queryForm ) {
        QueryWrapper<OaDzggInterfaceTemp> queryWrapper = new QueryWrapper <OaDzggInterfaceTemp>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaDzggInterfaceTempMapper.selectPage(new Page<OaDzggInterfaceTemp>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaDzggInterfaceTemp> selectList(OaDzggInterfaceTempQueryForm queryForm) {
        QueryWrapper<OaDzggInterfaceTemp> queryWrapper = new QueryWrapper <OaDzggInterfaceTemp>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaDzggInterfaceTempMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<OaDzggInterfaceTemp> getQueryWrapper(QueryWrapper<OaDzggInterfaceTemp> queryWrapper,OaDzggInterfaceTempQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getNoticeNo())){
            queryWrapper.eq(OaDzggInterfaceTemp.NOTICE_NO,queryForm.getNoticeNo());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptName())){
            queryWrapper.eq(OaDzggInterfaceTemp.APPLY_DEPT_NAME,queryForm.getApplyDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaDzggInterfaceTemp.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDate())){
            queryWrapper.eq(OaDzggInterfaceTemp.APPLY_DATE,queryForm.getApplyDate());
        }
        if(StringUtils.isNotBlank(queryForm.getTypeName())){
            queryWrapper.eq(OaDzggInterfaceTemp.TYPE_NAME,queryForm.getTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getSortName())){
            queryWrapper.eq(OaDzggInterfaceTemp.SORT_NAME,queryForm.getSortName());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentPath())){
            queryWrapper.eq(OaDzggInterfaceTemp.ATTACHMENT_PATH,queryForm.getAttachmentPath());
        }
        if(StringUtils.isNotBlank(queryForm.getIsRead())){
            queryWrapper.eq(OaDzggInterfaceTemp.IS_READ,queryForm.getIsRead());
        }
        return queryWrapper;
    }
     */
}


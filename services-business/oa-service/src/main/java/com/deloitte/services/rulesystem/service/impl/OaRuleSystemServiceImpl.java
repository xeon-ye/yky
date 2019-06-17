package com.deloitte.services.rulesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.rulesystem.param.OaRuleSystemQueryForm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.biz.OaProcessStatusEnum;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
import com.deloitte.services.noticeper.mapper.OaNoticePermissionMapper;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import com.deloitte.services.rulesystem.mapper.OaRuleSystemMapper;
import com.deloitte.services.rulesystem.service.IOaRuleSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaRuleSystem服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaRuleSystemServiceImpl extends ServiceImpl<OaRuleSystemMapper, OaRuleSystem> implements IOaRuleSystemService {


    @Autowired
    private OaRuleSystemMapper oaRuleSystemMapper;

    @Autowired
    private OaAttachmentMapper attachmentMapper;

    @Autowired
    private OaNoticePermissionMapper permissionMapper;

    @Override
    public boolean removeById(Serializable id) {
        boolean flag = super.removeById(id);
        permissionMapper.deletePermissionByObjId(String.valueOf(id));
        return flag;
    }

    @Override
    public OaRuleSystem getById(Serializable id) {
        OaRuleSystem oaRuleSystem = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaRuleSystem.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper <OaAttachment>(oaAttachment);
        oaRuleSystem.setAttachments(attachmentMapper.selectList(queryWrapper));

        List<OaNoticePermission> permissions = permissionMapper.getPermissionsByObjId(String.valueOf(oaRuleSystem.getId()));
        oaRuleSystem.setPermissionDepts(permissions);

        // 更新点击率
        OaRuleSystem newHit = new OaRuleSystem();
        newHit.setId(oaRuleSystem.getId());
        newHit.setRuleHit(oaRuleSystem.getRuleHit() + 1);
        oaRuleSystemMapper.updateById(newHit);

        return oaRuleSystem;
    }

    @Override
    public Result save(OaRuleSystemForm entity) {
        Result<String> result = new Result<>();
        OaRuleSystem oaRuleSystem =new BeanUtils<OaRuleSystem>().copyObj(entity, OaRuleSystem.class);
        super.save(oaRuleSystem);// 持久化规章制度
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaRuleSystem.getId())); // 设置新增的附件业务id
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp); // 持久化业务附件
        });

        // 存储部门节点
        entity.getPermissionDepts().forEach(dept -> {
            dept.setObjectId(oaRuleSystem.getId());
            OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
            permissionMapper.insert(temp);
        });

        return result.sucess(String.valueOf(oaRuleSystem.getId()));
    }

    @Override
    public Result update(long id, OaRuleSystemForm entity) {
        OaRuleSystem oaRuleSystem =new BeanUtils<OaRuleSystem>().copyObj(entity, OaRuleSystem.class);
        oaRuleSystem.setId(id);
        boolean flag = super.updateById(oaRuleSystem); // 更新列表
        // 更新附件
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
           atta.setBusicessId(String.valueOf(id));
           OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
           attachmentMapper.insert(temp);
        });

        permissionMapper.deletePermissionByObjId(String.valueOf(id));
        if("inter".equals(entity.getDeptPer())) {
            entity.getPermissionDepts().forEach(dept -> {
                dept.setObjectId(id);
                OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
                permissionMapper.insert(temp);
            });
        }

        return Result.success(flag);
    }

    @Override
    public IPage<OaRuleSystem> selectPageWithPermission(OaRuleSystemQueryForm queryForm, String token) {
        QueryWrapper<OaRuleSystem> queryWrapper = new QueryWrapper <OaRuleSystem>();
        String applyOrgName = queryForm.getApplyOrgName();
        queryForm.setApplyOrgName(null);
        queryForm.setApplyOrgCode(null);
        getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.like(OaRuleSystem.APPLY_ORG_NAME, applyOrgName);
        UserVo userVo = UserUtil.getUserVo();
        if(null != userVo){
            DeputyAccountVo accountVo = userVo.getCurrentDeputyAccount();
            if(accountVo != null){
                String orgCode = accountVo.getOrgCode();
                String userId = String.valueOf(accountVo.getId());
                queryWrapper.apply(" ( APPLY_USER_ID = {0} OR ( APPLY_ORG_CODE like {1} and APPLY_ORG_CODE <> {2} ) )",
                        userId, orgCode + "%", orgCode);
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户失败！");
        }

        return oaRuleSystemMapper.selectPage(new Page<OaRuleSystem>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }


    @Override
    public IPage<OaRuleSystem> selectPage(OaRuleSystemQueryForm queryForm, String token) {
        IPage<OaRuleSystem> pages = null;
        UserVo userVo = UserUtil.getUserVo();
        if(null != userVo) {
            String orgCode = userVo.getCurrentDeputyAccount().getOrgCode();
            String userId = userVo.getId();
            String sortCode = queryForm.getRuleSortCode();
            String curOrgCode = queryForm.getApplyOrgCode();
            DeptVo deptVo = UserUtil.getDept();
            if(2 == deptVo.getGroupType()) { // 外部人员
                QueryWrapper<OaRuleSystem> wrapper = new QueryWrapper<>();
                wrapper.eq(OaRuleSystem.DEPT_PER, "outer");
                wrapper.eq(OaRuleSystem.APPROVAL_STATUS, OaProcessStatusEnum.OA_PS_NORMAL);
                wrapper.eq(OaRuleSystem.RULE_SORT_CODE, sortCode);

                pages = oaRuleSystemMapper.selectPage(new Page<OaRuleSystem>(queryForm.getCurrentPage(),queryForm.getPageSize()), wrapper);
            } else {
                pages = oaRuleSystemMapper.getOrgPerList(new Page<OaRuleSystem>(queryForm.getCurrentPage(),queryForm.getPageSize()),
                        orgCode, userVo.getDept(), curOrgCode, userId, sortCode);
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败！");
        }

        return pages;
    }

    @Override
    public List<OaRuleSystem> selectList(OaRuleSystemQueryForm queryForm) {
        QueryWrapper<OaRuleSystem> queryWrapper = new QueryWrapper <OaRuleSystem>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaRuleSystemMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaRuleSystem> getHomeList(int num, String token) {
        OaRuleSystemQueryForm ruleSystemForm = new OaRuleSystemQueryForm();
        IPage<OaRuleSystem> pages = this.selectPage(ruleSystemForm, token);
        int len = pages.getRecords().size();
        return pages.getRecords().subList(0, len > num ? num : len);
//        UserVo userVo = UserUtil.getUserVo();
//        if(null != userVo){
//            String dept = userVo.getDept();
//            if(null != dept){
//                return oaRuleSystemMapper.getHomeList(num, dept);
//            } else {
//                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
//            }
//        } else {
//            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前登录用户信息失败！");
//        }
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<OaRuleSystem> getQueryWrapper(QueryWrapper<OaRuleSystem> queryWrapper,OaRuleSystemQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaRuleSystem.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getUrgentLevel())){
            queryWrapper.eq(OaRuleSystem.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getRuleSortCode())){
            queryWrapper.eq(OaRuleSystem.RULE_SORT_CODE,queryForm.getRuleSortCode());
        }
        if(StringUtils.isNotBlank(queryForm.getRuleSortName())){
            queryWrapper.eq(OaRuleSystem.RULE_SORT_NAME,queryForm.getRuleSortName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgCode())){
            queryWrapper.eq(OaRuleSystem.APPLY_ORG_CODE,queryForm.getApplyOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgName())){
            queryWrapper.eq(OaRuleSystem.APPLY_ORG_NAME,queryForm.getApplyOrgName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaRuleSystem.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaRuleSystem.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptCode())){
            queryWrapper.eq(OaRuleSystem.APPLY_DEPT_CODE,queryForm.getApplyDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApprovalStatus())){
            queryWrapper.eq(OaRuleSystem.APPROVAL_STATUS,queryForm.getApprovalStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptPer())){
            queryWrapper.eq(OaRuleSystem.DEPT_PER,queryForm.getDeptPer());
        }
        queryWrapper.orderByDesc(OaRuleSystem.APPLY_DATETIME);
        return queryWrapper;
    }
}


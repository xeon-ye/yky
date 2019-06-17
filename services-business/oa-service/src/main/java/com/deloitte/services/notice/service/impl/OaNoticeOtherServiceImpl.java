package com.deloitte.services.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.attachment.entity.OaAttachment;
import com.deloitte.services.attachment.mapper.OaAttachmentMapper;
import com.deloitte.services.notice.biz.OaProcessStatusEnum;
import com.deloitte.services.notice.entity.OaNoticeOther;
import com.deloitte.services.notice.entity.OaNotice;
import com.deloitte.services.notice.mapper.OaNoticeOtherMapper;
import com.deloitte.services.notice.service.IOaNoticeOtherService;
import com.deloitte.services.noticeper.entity.OaNoticePermission;
import com.deloitte.services.noticeper.mapper.OaNoticePermissionMapper;
import com.deloitte.services.oa.exception.OaErrorType;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description :  OaNoticeOther服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaNoticeOtherServiceImpl extends ServiceImpl<OaNoticeOtherMapper, OaNoticeOther> implements IOaNoticeOtherService {

    @Autowired
    private OaNoticeOtherMapper oaNoticeOtherMapper;

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
    public OaNoticeOther getById(Serializable id) {
        OaNoticeOther oaNoticeOther = super.getById(id);
        OaAttachment oaAttachment = new OaAttachment();
        oaAttachment.setBusicessId(String.valueOf(oaNoticeOther.getId()));
        QueryWrapper<OaAttachment> queryWrapper = new QueryWrapper<>(oaAttachment);
        oaNoticeOther.setAttachments(attachmentMapper.selectList(queryWrapper));

        List<OaNoticePermission> permissions = permissionMapper.getPermissionsByObjId(String.valueOf(oaNoticeOther.getId()));
        oaNoticeOther.setPermissionDepts(permissions);

        OaNoticeOther otherNew = new OaNoticeOther();
        otherNew.setId(oaNoticeOther.getId());
        otherNew.setNoticeHit(oaNoticeOther.getNoticeHit() + 1);
        oaNoticeOtherMapper.updateById(otherNew);

        return oaNoticeOther;
    }

    @Override
    public Result save(OaNoticeOtherForm entity) {
        OaNoticeOther oaNoticeOther = new BeanUtils<OaNoticeOther>().copyObj(entity, OaNoticeOther.class);
        boolean flag = super.save(oaNoticeOther);
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaNoticeOther.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        // 存储部门节点
        entity.getPermissionDepts().forEach(dept -> {
            dept.setObjectId(oaNoticeOther.getId());
            OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
            permissionMapper.insert(temp);
        });

        if(flag){
            return Result.success(String.valueOf(oaNoticeOther.getId()));
        }else {
            return Result.fail("新增数据失败");
        }
    }

    @Override
    public Result update(long id, OaNoticeOtherForm entity) {
        OaNoticeOther oaNoticeOther = new BeanUtils<OaNoticeOther>().copyObj(entity, OaNoticeOther.class);
        oaNoticeOther.setId(id);
        boolean flag = super.updateById(oaNoticeOther);
        attachmentMapper.deleteByBusinessId(String.valueOf(id));
        entity.getAttachments().forEach(atta -> {
            atta.setBusicessId(String.valueOf(oaNoticeOther.getId()));
            OaAttachment temp = new BeanUtils<OaAttachment>().copyObj(atta, OaAttachment.class);
            attachmentMapper.insert(temp);
        });

        permissionMapper.deletePermissionByObjId(String.valueOf(id));
        entity.getPermissionDepts().forEach(dept -> {
            dept.setObjectId(oaNoticeOther.getId());
            OaNoticePermission temp = new BeanUtils<OaNoticePermission>().copyObj(dept, OaNoticePermission.class);
            permissionMapper.insert(temp);

        });

        return Result.success(flag);
    }

    @Override
    public IPage<OaNoticeOther> selectPageWithPermission(OaNoticeOtherQueryForm queryForm, String token) {
        QueryWrapper<OaNoticeOther> queryWrapper = new QueryWrapper <OaNoticeOther>();
        String applyOrgName = queryForm.getApplyOrgName();
        queryForm.setApplyOrgCode(null);
        queryForm.setApplyOrgName(null);
        getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.like(OaNoticeOther.APPLY_ORG_NAME, applyOrgName);
        // Result result = userFeignService.getLoginUser(token);
        UserVo userVo = UserUtil.getUserVo();

        if(null != userVo){
            DeputyAccountVo deputyAccount = userVo.getCurrentDeputyAccount();
            if(deputyAccount != null){
                String orgCode = deputyAccount.getOrgCode();
                String userId = String.valueOf(deputyAccount.getId());
                queryWrapper.apply(" ( APPLY_USER_ID = {0} OR ( APPLY_ORG_CODE like {1} and APPLY_ORG_CODE <> {2} ) )",
                        userId, orgCode + "%", orgCode);
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取用户部门信息失败 !");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取当前用户失败");
        }

        return oaNoticeOtherMapper.selectPage(new Page<OaNoticeOther>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public IPage<OaNoticeOther> selectPage(OaNoticeOtherQueryForm queryForm, String token ) {
        IPage<OaNoticeOther> pages = null;
        UserVo userVo = UserUtil.getUserVo();
        String curOrgCode = queryForm.getApplyOrgCode();
        String typeCode = queryForm.getAppTypeCode();
        String orgCode, deptCode, userId;
        if(null != userVo) {
            orgCode = userVo.getCurrentDeputyAccount().getOrgCode();
            userId = userVo.getId();
            deptCode = userVo.getDept();
            // 判断当前登录用户是不是外部人员
            // userVo
            DeptVo deptVo = UserUtil.getDept();
            if (2 == deptVo.getGroupType()) { // 当前人员是外部人员
                OaNoticeOther oaNoticeOther = new OaNoticeOther();
                QueryWrapper<OaNoticeOther> wrapper = new QueryWrapper<>(oaNoticeOther);
                wrapper.eq(OaNoticeOther.DEPT_PER, "outer");
                wrapper.eq(OaNoticeOther.APP_TYPE_CODE, typeCode);
                wrapper.eq(OaNotice.APPROVAL_STATUS, OaProcessStatusEnum.OA_PS_NORMAL);

                pages = oaNoticeOtherMapper.selectPage(new Page<OaNoticeOther>(queryForm.getCurrentPage(), queryForm.getPageSize()), wrapper);
            } else { // 当前人员为内部人员，
                pages = oaNoticeOtherMapper.getOrgPerList(new Page<OaNoticeOther>(queryForm.getCurrentPage(), queryForm.getPageSize()),
                        orgCode, deptCode, curOrgCode, userId, typeCode);
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取登录用户信息失败！");
        }

        return pages;
    }

    @Override
    public List<OaNoticeOther> selectList(OaNoticeOtherQueryForm queryForm) {
        QueryWrapper<OaNoticeOther> queryWrapper = new QueryWrapper <OaNoticeOther>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaNoticeOtherMapper.selectList(queryWrapper);
    }

    @Override
    public List<OaNoticeOther> getHomeList(int num, String typeCode, String token) {
        OaNoticeOtherQueryForm noticeForm = new OaNoticeOtherQueryForm();
        noticeForm.setAppTypeCode(typeCode);
        IPage<OaNoticeOther> pages = this.selectPage(noticeForm, token);
        int len = pages.getRecords().size();
        return pages.getRecords().subList(0, len > num ? num : len);
//        UserVo userVo = UserUtil.getUserVo();
//        if(null != userVo){
//            String dept = userVo.getDept();
//            if(null != dept){
//                return oaNoticeOtherMapper.getHomeList(num, typeCode, dept);
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
    public QueryWrapper<OaNoticeOther> getQueryWrapper(QueryWrapper<OaNoticeOther> queryWrapper,OaNoticeOtherQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTitle())){
            queryWrapper.like(OaNoticeOther.TITLE,queryForm.getTitle());
        }
        if(StringUtils.isNotBlank(queryForm.getAppTypeCode())){
            queryWrapper.eq(OaNoticeOther.APP_TYPE_CODE,queryForm.getAppTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAppTypeName())){
            queryWrapper.eq(OaNoticeOther.APPLY_ORG_NAME,queryForm.getAppTypeName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgCode())){
            queryWrapper.eq(OaNoticeOther.APPLY_ORG_CODE,queryForm.getApplyOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyOrgName())){
            queryWrapper.eq(OaNoticeOther.APPLY_ORG_NAME,queryForm.getApplyOrgName());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserId())){
            queryWrapper.eq(OaNoticeOther.APPLY_USER_ID,queryForm.getApplyUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyUserName())){
            queryWrapper.eq(OaNoticeOther.APPLY_USER_NAME,queryForm.getApplyUserName());
        }
        if(null != queryForm.getUrgentLevel()){
            queryWrapper.eq(OaNoticeOther.URGENT_LEVEL,queryForm.getUrgentLevel());
        }
        if(null != queryForm.getDelFlag()){
            queryWrapper.eq(OaNoticeOther.DEL_FLAG,queryForm.getDelFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyDeptCode())){
            queryWrapper.eq(OaNoticeOther.APPLY_DEPT_CODE,queryForm.getApplyDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApprovalStatus())){
            queryWrapper.eq(OaNoticeOther.APPROVAL_STATUS, queryForm.getApprovalStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptPer())){
            queryWrapper.eq(OaNoticeOther.DEPT_PER, queryForm.getDeptPer());
        }
        queryWrapper.orderByDesc(OaNoticeOther.APPLY_DATETIME);
        return queryWrapper;
    }
}


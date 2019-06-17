package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.param.FsscUserQueryForm;
import com.deloitte.platform.api.isump.param.FsscUserQueryParam;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.Dept;
import com.deloitte.services.isump.entity.FsscUser;
import com.deloitte.services.isump.mapper.FsscUserMapper;
import com.deloitte.services.isump.service.IDeptService;
import com.deloitte.services.isump.service.IFsscUserService;
import com.deloitte.services.isump.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangdi
 * @Date 15/04/2019
 */
@Service
@Transactional
public class FsscUserServiceImpl extends ServiceImpl<FsscUserMapper, FsscUser> implements IFsscUserService {

    @Autowired
    private FsscUserMapper fsscUserMapper;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IDeptService iDeptService;

    @Override
    public List<FsscUser> selectList(FsscUserQueryForm queryForm) {
        QueryWrapper<FsscUser> queryWrapper = new QueryWrapper<FsscUser>();
        getQueryWrapper(queryWrapper, queryForm);
        return fsscUserMapper.selectList(queryWrapper);
    }

    /**
     * 列表查询（不分页）
     * @param userCode 员工号
     * @param userName 员工名称
     * @return
     */
    @Override
    public List<FsscUserVo> queryFsscUserInfo(String userCode, String userName) {
        return fsscUserMapper.queryFsscUserInfo(userCode, userName);
    }

    /**
     * 列表查询（分页）
     * @param userCode 员工号
     * @param userName 用户名称
     * @param country  国籍
     * @param page 页码
     * @param size 每页显示条数
     * @return
     */
    @Override
    public IPage<FsscUserVo> queryFsscUserInfoPage(String userCode, String userName, String country,String idCard, List<String> payeeType, int page, int size) {
        Page pages = new Page<>(page, size);
        List<FsscUserVo> list = fsscUserMapper.queryFsscUserInfoPage(userCode, userName, country,idCard, payeeType, pages);
        for(FsscUserVo fsscVo : list){
            UserVo userVo = iUserService.getUserVoById( Long.valueOf(fsscVo.getUserId()).longValue());
            if(org.apache.commons.lang3.StringUtils.isNotBlank(userVo.getDept())){
                QueryWrapper<Dept> deptQueryWrapper = new QueryWrapper<>();
                deptQueryWrapper.eq("DEPT_CODE", userVo.getDept());
                Dept dept = iDeptService.getOne(deptQueryWrapper);
                if(dept != null){
                    userVo.setDeptName(dept.getDeptName());
                }
            }
            fsscVo.setUserInfo(userVo);
        }
        return pages.setRecords(list);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<FsscUser> getQueryWrapper(QueryWrapper<FsscUser> queryWrapper, BaseQueryForm<FsscUserQueryParam> queryForm){
        FsscUserQueryParam fsscUserParam = queryForm.toParam(FsscUserQueryParam.class);
        //条件拼接
        if(StringUtils.isNotBlank(fsscUserParam.getId())){
            queryWrapper.like(FsscUser.ID, fsscUserParam.getId());
        }
        if(StringUtils.isNotBlank(fsscUserParam.getUserId())){
            queryWrapper.like(FsscUser.USER_ID, fsscUserParam.getUserId());
        }
        return queryWrapper;
    }
}

package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.param.FsscUserQueryForm;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.services.isump.entity.FsscUser;

import java.util.List;

/**
 * @author zhangdi
 * @Date 15/04/2019
 * @Description : FSSC服务类接口
 */
public interface IFsscUserService extends IService<FsscUser> {

    /**
     *  条件查询
     * @param queryForm
     * @return List<Organization>
     */
    List<FsscUser> selectList(FsscUserQueryForm queryForm);

    /**
     * 列表查询（不分页）
     * @param userCode 员工号
     * @param userName 用户名称
     * @return
     */
    List<FsscUserVo> queryFsscUserInfo(String userCode, String userName);

    /**
     * 列表查询（分页）
     * @param userCode 员工号
     * @param userName 用户名称
     * @return
     */
    IPage<FsscUserVo> queryFsscUserInfoPage(String userCode, String userName, String country,String idCard, List<String> payeeType, int page, int size);
}

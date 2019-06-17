package com.deloitte.services.project.service;

import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;

import java.io.File;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BPM服务类接口
 * @Modified :
 */
public interface ICommonService {
    /**
     * 获取当前用户信息
     * @param
     */
    UserVo getCurrentUser();
    /**
     * 获取当前用户单位
     * @param
     */
    DeptVo getCurrentDept();

    /**
     * 获取当前用户的菜单
     * @param
     */
    ResourceVo getMenu();

    /**
     * 获取当前用户的部门
     * @param
     */
    OrganizationVo getOrganization();
    /**
     * 发起一条待阅信息
     * @param userVo
     * @return
     */
    Result sendProcessTask(UserVo userVo, String businessId, VoucherTypeEnums typeEnums);

    /**
     * 公用上传文件服务器
     * @param fileUrl
     * @return
     */
    FileInfoVo uploadFile(String fileUrl);

    /**
     * 公用上传文件服务器
     * @param fileUrl
     * @return
     */
    FileInfoVo uploadFile(File file);

    /**
     * 根据申报书ID更新与之对应的申报书以及项目的审批状态
     * @param applicationId
     */
    void updateProjectAndApplicationStatusWithApplicationId(String applicationId);

    /**
     *@Description: CommonServiceImpl
     *@Param: [businessId 业务id, processVariables 参数, typeEnums 流程枚举]
     *@return: com.deloitte.platform.common.core.entity.vo.Result
     *@Author: zhengchun
     *@date: 2019/6/12 14:53
     */
    Result startAuditProcess(String  businessId, Map<String, String> processVariables, VoucherTypeEnums typeEnums);

    /**
     *@Description: CommonServiceImpl
     *@Param: [businessId 业务id, processVariables 参数, typeEnums 流程枚举,acceptVo 接收人信息]
     *@return: com.deloitte.platform.common.core.entity.vo.Result
     *@Author: zhengchun
     *@date: 2019/6/12 14:53
     */
    Result startAuditProcessByAccept(String  businessId, Map<String, String> processVariables, VoucherTypeEnums typeEnums, UserVo acceptVo);
}

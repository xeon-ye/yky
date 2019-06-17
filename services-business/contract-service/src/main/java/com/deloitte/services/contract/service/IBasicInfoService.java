package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.deloitte.services.contract.entity.BasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.contract.entity.BasicSubjectMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicInfo服务类接口
 * @Modified :
 */
public interface IBasicInfoService extends IService<BasicInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicInfo>
     */
    IPage<BasicInfo> selectPage(BasicInfoQueryForm queryForm,UserVo userVo);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicInfo>
     */
    List<BasicInfo> selectList(BasicInfoQueryForm queryForm);

    /**
     * yangyuanqing
     * 保存合同基本信息
     * @param basicInfoForm
     * @return
     */
    BasicInfo addContractInfo(BasicInfoForm basicInfoForm, UserVo userVo, OrganizationVo organizationVo);

    /**
     * 根据合同id查询合同基本信息
     * @param contractId
     * @return
     */
    BasicInfo selectContractInfoById(Long contractId);

    /**
     * 查询合同关联文件
     * @param contractId
     * @return
     */
    List<BasicAttamentMap> selectbasicAttamentByContractId(Long contractId);

    /**
     * 查询合同签约信息
     * @param contractId
     * @return
     */
    List<BasicSubjectMap> selectBasicSubjectByContractId(Long contractId);

    //    草稿箱查询
    List<BasicInfo> queryLineInfo(BasicInfoQueryForm base);

    //    保存复制合同页面基本信息
    BasicInfo InsertCopyInfo(String contractNumId,UserVo userVo, OrganizationVo organizationVo);

    //    查询复制合同信息
    BasicInfo queryCopyInfo();

    //合同页面基本信息查询
    BasicInfoQueryForm queryCurrentBasic(String contractId);

    //根据contractId查询关联合同
    BasicInfoVo queryCorrelationBasic(BasicInfoVo basicInfoVo);

    //删除合同(修改合同状态为已作废)
    int updateContractStatue(String contractId);

    BasicInfoVo saveContractFinalize(BasicInfoForm basicInfoForm, UserVo userVo);

    /**
     * 根据id修改合同备注保存
     * @param basicInfoForm
     * @return
     */
    int updateBasicInfoRemarkById(BasicInfoForm basicInfoForm);

    /**
     * 查询user信息
     * @param id
     * @return
     */
    UserVo getUserById(String id);

    /**
     * 查询合同履行情况检查
     * @param basicInfoQueryForm
     * @param userVo
     * @return
     */
    BasicInfoVo selectContractExecuteCheck(BasicInfoQueryForm basicInfoQueryForm,UserVo userVo);

    /**
     * 保存印花税信息
     * @param basicInfoForm
     * @return
     */
    int saveImprint(BasicInfoForm basicInfoForm);

    /**
     * 推送合同信息给财务系统
     * @param contractId
     * @return
     */
    Result selectBasicInfoToFssc(Long contractId);

    /**
     * 根据合同是否推送财务标记，查询未推送财务的合同信息
     * @return
     */
    Result selectBasicInfoByFlag();

    /**
     * 获取变动合同信息
     * @param basicInfoForm
     * @return
     */
    BasicInfoVo getChangeContract(BasicInfoForm basicInfoForm);

    /**
     * 保存合同变更信息
     * @param basicInfoForm
     * @param userVo
     * @param organizationVo
     * @return
     */
    BasicInfo insertChangeContract(BasicInfoForm basicInfoForm, UserVo userVo, OrganizationVo organizationVo);

    /**
     *
     * @param rel2000 变更/补充
     * @param rel4000 终止/解除
     * @param rel5000 单方解除
     * @return
     */
    Result saveBasicRelationStatus(String contractId, String rel2000, String rel4000, String rel5000);

    /**
     * 退回时只修改上一个合同的状态为变更前的状态
     * @return
     */
    Result saveBasicRelationStatusLast(Long contractId);

    /**
     * 将上一个合同状态修改回履行状态
     * @return
     */
    Result saveBasicOldContractStatus(String contractId);

    /**
     * 查询合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> selectContractLedger(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 查询合同台账信息数量
     * @param basicInfoQueryForm
     */
    BigDecimal selectContractLedgerCount(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 导出合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    Result exportContractLedger(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 倒签统计数量报表
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> selectNoBackDating(BasicInfoQueryForm basicInfoQueryForm);

    BasicInfoVo selectApprovalOpinion(BasicInfoQueryForm basicInfoQueryForm);

    BasicInfoVo selectExecuteStatue(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 设置合同履行期限信息
     * @param id
     * @return
     */
    BasicInfo setExecuteTime(Long id);

    /**
     * 根据合同oldid保存旅行信息
     */
    Result saveInfoByOldContractId(BasicInfo basicInfo);

    /**
     * 生成履行预警待阅
     * @return
     */
    Result sendExecuteWaring();

    /**
     * 获取需要预警的合同履行计划
     * @return
     */
    List<FinanceInfoVo> getExecuteWaringContract();

    /**
     * 生成合同履行期限预警待阅
     * @return
     */
    Result sendContractWaring();
}

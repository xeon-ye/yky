package com.deloitte.services.contract.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.param.BasicSubjectMapQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.services.contract.entity.ApprovalOpinion;
import com.deloitte.services.contract.entity.BasicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deloitte.services.contract.entity.BasicSubjectMap;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合同基本信息 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-03
 */
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {

    List<BasicInfo> selectContractInfoById(String contractId);

    //    草稿箱查询
    List<BasicInfo> queryLineInfo(BasicInfoQueryForm baseInfo);
    //    查询复制合同页面基本信息
    BasicInfo queryCopyInfo(String contractNumId);
    //查询合同签约主体关联表
    ArrayList<BasicSubjectMapForm> queryCopySubject(String contractNumId);
    //    查询复制合同页面基本信息(包含id)
    BasicInfo queryCopyInfoId(String contractNumId);
    //合同页面基本信息查询
    BasicInfoQueryForm queryCurrentBasic(String contractId);
    //根据contractId查询关联合同(变更后合同查询前面的合同)
    List<BasicInfoVo> queryCorrelationBasic(String contractId);
    //根据contractId查询关联合同(原合同查询后面的合同)
    List<BasicInfoVo> queryCorrelationBasicNew(String contractId);
    //删除合同(修改合同状态为已作废)
    int updateContractStatue(String contractId);
    int updateContractFinalize(BasicInfo basicInfo);
//    IPage<BasicInfo> selectOutLine(IPage<BasicInfo> var1, @Param("ew2") Wrapper<BasicInfo> var2, @Param("ew3") Wrapper<SysSignSubjectInfo> var3);

    /**
     * 根据id修改合同备注信息
     * @param basicInfoForm
     * @return
     */
    int updateBasicInfoRemarkById(BasicInfoForm basicInfoForm);

    /**
     * 查询合同履行情况检查
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> selectContractExecuteCheck(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 查询合同履行情况检查总数
     * @param basicInfoQueryForm
     * @return
     */
    BigDecimal selectContractExecuteCheckCount(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 查询合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> selectContractLedger(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 查询倒签统计数量报表
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> selectNoBackDating(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 查询合同台账信息总数
     * @param basicInfoQueryForm
     * @return
     */
    BigDecimal selectContractLedgerCount(BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 导出合同台账信息
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoVo> exportContractLedger(BasicInfoQueryForm basicInfoQueryForm);

    //查询审签意见报表数据 -合同部分
    List<BasicInfoVo> selectTotal(BasicInfoQueryForm basicInfoQueryForm);
    List<BasicInfoVo> selectOpinionBaseInfo(BasicInfoQueryForm basicInfoQueryForm);
    //查询审签意见报表数据 -意见部分
    List<ApprovalOpinionVo> selectOpinion(String contractId);

    /**
     * 查询正常履行合同比例
     * @param basicInfoQueryForm
     * @return
     */
    List<BasicInfoExecuteVo> selectExecuteRatio(BasicInfoQueryForm basicInfoQueryForm);

    // 查询逾期未付款
    BigDecimal getPayDate(BasicInfoQueryForm basicInfoQueryForm);
    // 查询逾期未收款
    BigDecimal getIncomeDate(BasicInfoQueryForm basicInfoQueryForm);
    // 付款金额与约定不符
    BigDecimal getPayMoney(BasicInfoQueryForm basicInfoQueryForm);
    // 收款金额与约定不符
    BigDecimal getIncomeMoney(BasicInfoQueryForm basicInfoQueryForm);
    // 获取月总金额（收入/支出）
    List<BasicInfoExecuteVo>  getMoneyAll(BasicInfoQueryForm basicInfoQueryForm);

    List<FinanceInfoVo> getExecuteWaringContract();

    List<BasicInfoVo> getContractWaring();
}

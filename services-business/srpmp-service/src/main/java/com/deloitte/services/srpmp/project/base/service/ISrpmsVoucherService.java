package com.deloitte.services.srpmp.project.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsVoucherQueryForm;
import com.deloitte.services.srpmp.common.enums.VoucherStatusEnums;
import com.deloitte.services.srpmp.common.enums.VoucherTypeEnums;
import com.deloitte.services.srpmp.project.accept.entity.SrpmsProjectAccept;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetYear;
import com.deloitte.services.srpmp.project.update.entity.SrpmsProjectUpdate;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description : SrpmsVoucher服务类接口
 * @Modified :
 */
public interface ISrpmsVoucherService extends IService<SrpmsVoucher> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SrpmsVoucher>
     */
    IPage<SrpmsVoucher> selectPage(SrpmsVoucherQueryForm queryForm, UserVo userVo);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SrpmsVoucher>
     */
    List<SrpmsVoucher> selectList(SrpmsVoucherQueryForm queryForm);


    /**
     * 更新单据状态
     */
    void updateStatus(Long voucherId, VoucherStatusEnums statusEnums);


    /**
     * 根据项目编号和单据类型生成一个新单据
     * @param projectId
     * @return
     */
    SrpmsVoucher generateNewVoucher(Long projectId, VoucherTypeEnums typeEnums);

    SrpmsVoucher generateNewVoucherAccept(SrpmsProjectAccept accept, VoucherTypeEnums typeEnums, UserVo userVo);

    SrpmsVoucher generateNewVoucherModify(SrpmsProjectUpdate update, VoucherTypeEnums typeEnums, UserVo userVo);

    SrpmsVoucher generateNewVoucherBudget(SrpmsProjectBudgetYear budgetYear, VoucherTypeEnums typeEnums, UserVo userVo);

    SrpmsVoucher getSrpmsVoucherByUpdateId(Long updateId);

    SrpmsVoucher getSrpmsVoucherByReject(Long projectId);
}

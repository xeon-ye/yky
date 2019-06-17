package com.deloitte.services.fssc.business.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.edu.param.FundsApplyLineQueryForm;
import com.deloitte.platform.api.fssc.edu.vo.FundsApplyLineVo;
import com.deloitte.services.fssc.business.edu.entity.FundsApplyLine;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description : FundsApplyLine服务类接口
 * @Modified :
 */
public interface IFundsApplyLineService extends IService<FundsApplyLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<FundsApplyLine>
     */
    IPage<FundsApplyLine> selectPage(FundsApplyLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<FundsApplyLine>
     */
    List<FundsApplyLine> selectList(FundsApplyLineQueryForm queryForm);

    /**
     * 根据单据编号和行号查询行信息
     * @param documentId
     * @param lineNumber
     * @return
     */
    FundsApplyLine getByKeyWord(Long documentId,Long lineNumber);

    /**
     *  分页查询行信息
     * @param queryForm
     * @return IPage<FundsApplyLine>
     */
    IPage<FundsApplyLineVo> selectVoPage(FundsApplyLineQueryForm queryForm);

}

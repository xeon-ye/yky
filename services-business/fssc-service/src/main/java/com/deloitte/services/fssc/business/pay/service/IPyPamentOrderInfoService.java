package com.deloitte.services.fssc.business.pay.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.pay.param.PyAllDocumentQueryForm;
import com.deloitte.platform.api.fssc.pay.param.PyPamentOrderInfoQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOrderInfoVo;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentOverListVo;
import com.deloitte.services.fssc.business.pay.entity.PyPamentOrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description : PyPamentOrderInfo服务类接口
 * @Modified :
 */
public interface IPyPamentOrderInfoService extends IService<PyPamentOrderInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PyPamentOrderInfo>
     */
    IPage<PyPamentOrderInfo> selectPage(PyPamentOrderInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PyPamentOrderInfo>
     */
    List<PyPamentOrderInfo> selectList(PyPamentOrderInfoQueryForm queryForm);

    /**
     * 对公
     * @param form
     * @return
     */
    List<PyPamentOverListVo> findDocumentAll(PyAllDocumentQueryForm form);

    /**
     * 对私
     * @param form
     * @return
     */
    List<PyPamentOverListVo> findPrivateAll(PyAllDocumentQueryForm form);

    /**
     * 公务卡
     * @param form
     * @return
     */
    List<PyPamentOverListVo> findBussinessCardAll(PyAllDocumentQueryForm form);

    boolean selectCount(String documentNum);

    Integer geExpenseAllDocument(PyPamentOrderInfoVo form);
    Integer tavelAllDocument(PyPamentOrderInfoVo form);
    Integer advanceAllDocument(PyPamentOrderInfoVo form);
    Integer borrowAllDocument(PyPamentOrderInfoVo form);
    Integer laborAllDocument(PyPamentOrderInfoVo form);
    Integer contractAllDocument(PyPamentOrderInfoVo form);
}

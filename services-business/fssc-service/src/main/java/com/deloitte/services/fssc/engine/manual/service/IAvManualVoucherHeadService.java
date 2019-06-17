package com.deloitte.services.fssc.engine.manual.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.vo.AvBaseElementVo;
import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description : AvManualVoucherHead服务类接口
 * @Modified :
 */
public interface IAvManualVoucherHeadService extends IService<AvManualVoucherHead> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvManualVoucherHead>
     */
    IPage<AvManualVoucherHead> selectPage(AvManualVoucherHeadQueryForm queryForm,Boolean lookAll);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvManualVoucherHead>
     */
    List<AvManualVoucherHead> selectList(AvManualVoucherHeadQueryForm queryForm);

    /**
     * 获取凭证类型
     * @return
     */
    List<AvBaseElement> selectJeCategory();

    /**
     * 手工录入凭证，根据账薄和序号，获取到相应的COA值
     * @param ledgerId
     * @return
     */
    List<AvBaseElementVo> selectBaseElementByNum(Long ledgerId);
}

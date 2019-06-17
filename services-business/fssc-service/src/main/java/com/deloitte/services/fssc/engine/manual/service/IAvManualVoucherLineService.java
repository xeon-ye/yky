package com.deloitte.services.fssc.engine.manual.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherLineQueryForm;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description : AvManualVoucherLine服务类接口
 * @Modified :
 */
public interface IAvManualVoucherLineService extends IService<AvManualVoucherLine> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvManualVoucherLine>
     */
    IPage<AvManualVoucherLine> selectPage(AvManualVoucherLineQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvManualVoucherLine>
     */
    List<AvManualVoucherLine> selectList(AvManualVoucherLineQueryForm queryForm);

    /**
     *
     * @param documentType
     * @param documentId
     * @return
     */
    Map<String,Object> selectMapLimitOne(String documentType,Long documentId);

}

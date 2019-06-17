package com.deloitte.services.fssc.bocb2e.biz.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.bocb2e.biz.IBocb2eBiz;
import com.deloitte.services.fssc.bocb2e.bo.Bocb2eBo;
import com.deloitte.services.fssc.bocb2e.bo.BocbHeader;
import com.deloitte.services.fssc.bocb2e.config.BocbHeadProperties;
import com.deloitte.services.fssc.business.pay.entity.PyPamentBusinessLine;
import com.deloitte.services.fssc.business.pay.service.IPyPamentBusinessLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class Bocb2eAbstract implements IBocb2eBiz {

    @Autowired
    private BocbHeadProperties headProperties;

    @Autowired
    private IPyPamentBusinessLineService pyPamentBusinessLineService;

    /**
     * 查询付款单行
     * @param paymentId
     * @return
     */
    protected List<PyPamentBusinessLine> findBusinessLines(Long paymentId){
        QueryWrapper<PyPamentBusinessLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(PyPamentBusinessLine.DOCUMENT_ID, paymentId);
        return  pyPamentBusinessLineService.list(lineQueryWrapper);
    }

    protected Bocb2eBo buildBoCommon() {
        //主体
        Bocb2eBo bo = new Bocb2eBo();

        //头
        BocbHeader header = new BeanUtils<BocbHeader>().copyObj(headProperties,BocbHeader.class);

        bo.setHeader(header);
        return bo;
    }
}

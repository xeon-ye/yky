package com.deloitte.services.fssc.business.rep.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.rep.param.RecieveClaimAreaQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveClaimArea;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveClaimArea服务类接口
 * @Modified :
 */
public interface IRecieveClaimAreaService extends IService<RecieveClaimArea> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<RecieveClaimArea>
     */
    IPage<RecieveClaimArea> selectPage(RecieveClaimAreaQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<RecieveClaimArea>
     */
    List<RecieveClaimArea> selectList(RecieveClaimAreaQueryForm queryForm);
}

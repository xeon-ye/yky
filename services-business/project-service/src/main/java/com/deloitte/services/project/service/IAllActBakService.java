package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.AllActBakQueryForm;
import com.deloitte.services.project.entity.AllActBak;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : AllActBak服务类接口
 * @Modified :
 */
public interface IAllActBakService extends IService<AllActBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AllActBak>
     */
    IPage<AllActBak> selectPage(AllActBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AllActBak>
     */
    List<AllActBak> selectList(AllActBakQueryForm queryForm);
}

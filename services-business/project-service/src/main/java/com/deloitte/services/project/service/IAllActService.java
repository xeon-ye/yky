package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.AllActQueryForm;
import com.deloitte.platform.api.project.vo.AllActVo;
import com.deloitte.services.project.entity.AllAct;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : AllAct服务类接口
 * @Modified :
 */
public interface IAllActService extends IService<AllAct> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AllAct>
     */
    IPage<AllAct> selectPage(AllActQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AllAct>
     */
    List<AllAct> selectList(AllActQueryForm queryForm);

    /**
     * 获取
     * @param applicationID
     * @return
     */
    List<AllAct> getAllActVo(long applicationID);
}

package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.project.param.ActBakQueryForm;
import com.deloitte.platform.api.project.vo.MainProVo;
import com.deloitte.services.project.entity.ActBak;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.project.entity.Budget;

import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : ActBak服务类接口
 * @Modified :
 */
public interface IActBakService extends IService<ActBak> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ActBak>
     */
    IPage<ActBak> selectPage(ActBakQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ActBak>
     */
    List<ActBak> selectList(ActBakQueryForm queryForm);

    /**
     * 删除
     * @param applicationId
     */
    void deleteAllList(String applicationId);

    /**
     * 获取
     * @param applicationId
     * @return
     */
    List<ActBak> getList(String applicationId);

    List<ActBak> getListByRepId(String replyId);
    void deleteListByRepId(String replyId);

}

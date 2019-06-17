package com.deloitte.services.rulesystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.oaservice.rulesystem.param.OaRuleSystemQueryForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.rulesystem.entity.OaRuleSystem;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description : OaRuleSystem服务类接口
 * @Modified :
 */
public interface IOaRuleSystemService extends IService<OaRuleSystem> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaRuleSystem>
     */
    IPage<OaRuleSystem> selectPage(OaRuleSystemQueryForm queryForm, String token);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaRuleSystem>
     */
    List<OaRuleSystem> selectList(OaRuleSystemQueryForm queryForm);

    /**
     * 首页列表查询
     * @param num
     * @return
     */
    List<OaRuleSystem> getHomeList(int num, String token);

    /**
     * 新增新闻规则
     * @param entity
     * @return
     */
    Result save(OaRuleSystemForm entity);

    /**
     * 更新规则制度
     * @param entity
     * @return
     */
    Result update(long id, OaRuleSystemForm entity);

    /**
     * 根据权限查询数据
     * @param oaRuleSystemQueryForm
     * @param token
     * @return
     */
    IPage<OaRuleSystem> selectPageWithPermission(OaRuleSystemQueryForm oaRuleSystemQueryForm, String token);

}

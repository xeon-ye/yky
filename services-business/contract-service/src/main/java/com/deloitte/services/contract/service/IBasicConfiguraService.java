package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.BasicConfiguraQueryForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraForm;
import com.deloitte.platform.api.contract.vo.BasicConfiguraVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.BasicConfigura;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : yangyq
 * @Date : Create in 2019-04-23
 * @Description : BasicConfigura服务类接口
 * @Modified :
 */
public interface IBasicConfiguraService extends IService<BasicConfigura> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasicConfigura>
     */
    IPage<BasicConfigura> selectPage(BasicConfiguraQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasicConfigura>
     */
    List<BasicConfigura> selectList(BasicConfiguraQueryForm queryForm);

    /**
     * 保存合同信息配置
     * @param basicConfiguraForm
     * @return
     */
    Result<BasicConfiguraVo> saveBasicConfigura(BasicConfiguraForm basicConfiguraForm);
}

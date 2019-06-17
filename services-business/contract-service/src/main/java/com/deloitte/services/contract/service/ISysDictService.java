package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysDictQueryForm;
import com.deloitte.platform.api.contract.vo.SysDictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysDict服务类接口
 * @Modified :
 */
public interface ISysDictService extends IService<SysDict> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysDict>
     */
    IPage<SysDict> selectPage(SysDictQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysDict>
     */
    List<SysDict> selectList(SysDictQueryForm queryForm);

    /**
     * 根据传入的types来查询所需的字典信息
     * @param types
     * @return
     */
    Map<String, SysDictVo> getSysDictVoByTypes(String[] types);

    /**
     * 保存合同类型信息
     * @param sysDict
     * @return
     */
    Result saveContractType(SysDict sysDict);

    /**
     * 修改合同信息
     * @param sysDict
     */
    Result updateContractType(SysDict sysDict);

    /**
     * 删除节点及其子节点全部信息
     * @param sysDict
     * @return
     */
    Result deleteContractType(SysDict sysDict);
}

package com.deloitte.services.srpmp.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryForm;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryParam;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainChildVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainTreeVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.srpmp.common.entity.SysDict;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;

import java.util.List;
import java.util.Map;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description : SysDict服务类接口
 * @Modified :
 */
public interface ISysDictService extends IService<SysDict> {

    /**
     *  查询字典维护列表数据service接口
     * @param queryForm
     * @return IPage<SysDict>
     */
    List<SysDictMainTainVo> list(SysDictQueryForm queryForm);

    /**
     * 根据条件查询字典子级数据
     *
     * @param mainTainVo
     * @return
     */
    JSONObject search(SysDictMainTainVo mainTainVo);

    void saveTree(SysDictMainTainTreeVo tainTreeVo, UserVo user);

    void saveOrUpdate(SysDictMainTainChildVo childVo, UserVo user);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysDict>
     */
    List<SysDict> selectList(BaseQueryForm<SysDictQueryParam> queryForm);

    /**
     *  条件查询
     * @param dictCodes
     * @return List<SysDict>
     */
    JSONObject selectByCodes(String dictCodes);

    /**
     * 根据value获取字典code
     * @param sysDictEnums
     * @param value
     * @return
     */
    String selectCodeByValue(SysDictEnums sysDictEnums, String value);

    /**
     * 根据value获取字典code（模糊查询）
     * @param sysDictEnums
     * @param value
     * @return
     */
    String selectCodeByLikeValue(SysDictEnums sysDictEnums, String value);

    /**
     * 根据code获取value
     * @param sysDictEnums
     * @param code
     * @return
     */
    String selectValueByCode(SysDictEnums sysDictEnums, String code);

    /**
     * 根据传入的字典编码查询字典service接口,根据code匹配名称
     *
     * @param dictCodes
     * @return
     */
    Map<String, String> getSysDictMap(String[] dictCodes);

    /**
     * 横纵向项目查询项目类别
     *
     * @param dictCode
     * @return
     */
    Map<String, String> getSysDictTranLong(String dictCode);

    /**
     * 根据传入的字典编码查询字典service接口，根据名称匹配code
     *
     * @param dictCodes
     * @return
     */
    Map<String, String> getSysDictCodeByValueMap(String[] dictCodes);

    Map<String, List<String>> getSysDictListMap(String[] dictCodes);

    Map<String, List<String>> getSysDictSelectCodeByCode(String dictCode);

    Map<String, String> getSysDictSelectValueByCode(String dictCode);

    Map<String, String> getDictByCategory(String sysDictCategory);
}

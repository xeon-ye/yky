package com.deloitte.services.srpmp.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryForm;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryParam;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainChildVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainNodeVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainTreeVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainVo;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.services.srpmp.common.entity.SysDict;
import com.deloitte.services.srpmp.common.enums.SysDictEnums;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.mapper.SysDictMapper;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description :  SysDict服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 查询字典维护列表数据service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SysDictMainTainVo> list(SysDictQueryForm queryForm) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper, queryForm);
        List<SysDict> sysDictList = sysDictMapper.selectList(queryWrapper);// 查询所有父级

        List<SysDictMainTainVo> sysDictVoList = new ArrayList<>();

        if(sysDictList != null && sysDictList.size() > 0) {
            SysDict sysDict;
            SysDictMainTainVo sysDictVo;
            for (Iterator e = sysDictList.iterator(); e.hasNext(); ) {
                sysDict = (SysDict) e.next();
                sysDictVo = new SysDictMainTainVo();
                // 设置一级对象
                sysDictVo.setId(CommonUtil.objectToString(sysDict.getId()));
                sysDictVo.setLabel(sysDict.getDictValue());
                sysDictVo.setDictCode(sysDict.getDictCode());
                if (SrpmsConstant.PRO_CATEGORY.equals(sysDict.getDictCode())
                        || SrpmsConstant.SUBJECT_OPTIONS.equals(sysDict.getDictCode())
                        || SrpmsConstant.BE_CURRENT.equals(sysDict.getDictCode())) {
                    sysDictVo.setTreeFlag(true);
                } else {
                    sysDictVo.setTreeFlag(false);
                }
                sysDictVoList.add(sysDictVo);
            }
        }

        return sysDictVoList;

    }

    /**
     * 查询基础信息子级数据
     *
     * @param mainTainVo
     * @return
     */
    @Override
    public JSONObject search(SysDictMainTainVo mainTainVo) {
        JSONObject result = new JSONObject();
        List<Map> list = sysDictMapper.querySysDictChild(mainTainVo.getId());// 根据父级ID查询
        JSONArray selectArr = new JSONArray();
        if(list != null && list.size() > 0) {
            JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));

            if(mainTainVo.isTreeFlag()) {
                selectArr = convertDictToTree(jsonArray, true);
            } else {
                selectArr = convertDictToSelect(jsonArray, true);
            }

        }
        result.put("mainTainList", selectArr);
        return result;
    }

    @Override
    public void saveTree(SysDictMainTainTreeVo tainTreeVo, UserVo user) {
        Long id = tainTreeVo.getId();

        if(sysDictMapper.getDictValueExists(tainTreeVo.getParentId(), tainTreeVo.getKeyName()) > 0) {
            throw new BaseException(SrpmsErrorType.SYS_DICT_VALUE_EXISTS);
        }

        SysDict sysDict = new SysDict();
        if(id == null || id == 0L) {// 新增操作
            String dictCode = sysDictMapper.getSysDictMaxDictCode(tainTreeVo.getParentId());
            int newDictCode;
            if(StringUtils.isNotBlank(dictCode)) {
                newDictCode = CommonUtil.getIntegerValue(dictCode);
                dictCode = CommonUtil.objectToString(newDictCode + 1);
            } else {// 不存在子级的时候根据父级code生成编号
                SysDict parentData = this.getById(tainTreeVo.getParentId());
                dictCode = parentData.getDictCode() + "01";
            }
            sysDict.setDictCode(dictCode);
            sysDict.setDictValue(tainTreeVo.getKeyName());
            sysDict.setDictParent(tainTreeVo.getParentId() == 0 ? null : tainTreeVo.getParentId());
            sysDict.setIsExpired(tainTreeVo.isActiveFlag() == true ? 0 : 1);
            sysDict.setCreateBy(user.getId());
            this.save(sysDict);

        } else {// 更新操作
            List<SysDict> sysDictEditList = new ArrayList<>();
            int isExpired = 0;
            if(!tainTreeVo.isActiveFlag()) {
                isExpired = 1;
            }
            sysDict.setId(tainTreeVo.getId());
            sysDict.setDictValue(tainTreeVo.getKeyName());
            sysDict.setIsExpired(isExpired);
            sysDict.setUpdateBy(user.getId());
            sysDictEditList.add(sysDict);

            List<Map> list = sysDictMapper.querySysDictChild(CommonUtil.objectToString(tainTreeVo.getId()));// 查询是否有子级
            if(list != null && list.size() > 0) {// 判断是否存在子级，启用禁用的时候子级跟随父级是否有效操作
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
                JSONObject sourceJson;
                for (int i = 0; i < jsonArray.size(); i++) {
                    sysDict = new SysDict();
                    sourceJson = jsonArray.getJSONObject(i);
                    sysDict.setId(sourceJson.getLong("ID"));
                    sysDict.setIsExpired(isExpired);
                    sysDictEditList.add(sysDict);
                }
            }
            this.saveOrUpdateBatch(sysDictEditList);
        }

        setRedisTemplateData(tainTreeVo.getParentId());
    }

    @Override
    public void saveOrUpdate(SysDictMainTainChildVo childVo, UserVo user) {
        String dictCode = sysDictMapper.getSysDictMaxDictCode(childVo.getParentId());// 查询最大的code值
        int newDictCode = 0;
        if(StringUtils.isNotBlank(dictCode)) {// 转化code类型
            newDictCode = CommonUtil.getIntegerValue(dictCode);
        }
        SysDict sysDict;
        List<SysDictMainTainNodeVo> sysDictVoList = childVo.getSysDictVoList();// 获取前端传递list数据
        Map<String, String> dataMap = new HashMap<>();
        if(sysDictVoList != null && sysDictVoList.size() > 0) {
            List<SysDict> sysDictAddList = new ArrayList<>();
            List<SysDict> sysDictEditList = new ArrayList<>();
            SysDictMainTainNodeVo nodeVo;
            int index = 0;
            for (Iterator e = sysDictVoList.iterator(); e.hasNext(); ) {
                index ++;
                nodeVo = (SysDictMainTainNodeVo) e.next();
                if(dataMap.containsKey(nodeVo.getKeyName())) {
                    throw new BaseException(SrpmsErrorType.SYS_DICT_VALUE_EXISTS);
                }
                sysDict = new SysDict();
                sysDict.setDictValue(nodeVo.getKeyName());
                sysDict.setIsExpired(nodeVo.isActiveFlag() == true ? 0 : 1);
                if(nodeVo.getId() == null || nodeVo.getId() == 0L) {// 组装新增数据
                    sysDict.setDictCode(CommonUtil.objectToString(newDictCode + index * 10));// code值新增
                    sysDict.setDictParent(childVo.getParentId());
                    sysDict.setCreateBy(user.getId());
                    sysDictAddList.add(sysDict);
                } else {// 组装更新数据
                    sysDict.setId(nodeVo.getId());
                    sysDict.setUpdateBy(user.getId());
                    sysDictEditList.add(sysDict);
                }
                dataMap.put(nodeVo.getKeyName(), nodeVo.getKeyName());
            }
            if(sysDictAddList.size() > 0) {// 执行新增操作
                this.saveBatch(sysDictAddList);
            }
            if(sysDictEditList.size() > 0) {// 执行更新操作
                this.saveOrUpdateBatch(sysDictEditList);
            }
        }

        setRedisTemplateData(childVo.getParentId());
    }


    public void setRedisTemplateData(Long id) {
        SysDict sysDict = this.getById(id);
        if (sysDict == null) {
            return;
        }
        String dictCode = sysDict.getDictCode();
        redisTemplate.delete("sysDict_" + dictCode);// 移除redis基础信息数据
        List<Map> voList = sysDictMapper.selectByCode(dictCode);
        if (voList.size() == 0) {
            return;
        }
        JSONArray selectArr;
        JSONArray subJsonArr;
        subJsonArr = JSONArray.parseArray(JSON.toJSONString(voList));

        if (SrpmsConstant.PRO_CATEGORY.equals(dictCode)
                || SrpmsConstant.SUBJECT_OPTIONS.equals(dictCode)
                || SrpmsConstant.BE_CURRENT.equals(dictCode)) {
            selectArr = convertDictToTree(subJsonArr, false);
        } else {
            selectArr = convertDictToSelect(subJsonArr, false);
        }
        redisTemplate.opsForValue().set("sysDict_" + dictCode, selectArr);// 重新设置数据
    }

    @Override
    public String selectCodeByValue(SysDictEnums sysDictEnums, String value) {
        return sysDictMapper.selectCodeByValue(sysDictEnums.getCode(), value);
    }

    @Override
    public String selectCodeByLikeValue(SysDictEnums sysDictEnums, String value) {
        return sysDictMapper.selectCodeByLikeValue(sysDictEnums.getCode(), value);
    }

    @Override
    public String selectValueByCode(SysDictEnums sysDictEnums, String code) {
        return sysDictMapper.selectValueByCode(sysDictEnums.getCode(), code);
    }

    @Override
    public List<SysDict> selectList(BaseQueryForm<SysDictQueryParam> queryForm) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<SysDict>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysDictMapper.selectList(queryWrapper);
    }

    @Override
    public JSONObject selectByCodes(String dictCode) {
        String[] strArr = dictCode.split("\\+");
        JSONObject relust = new JSONObject();
        JSONArray selectArr;
        JSONArray subJsonArr;
        for (int i = 0; i < strArr.length; i++) {

            log.info(strArr[i]);
            if (redisTemplate.opsForValue().get("sysDict_" + strArr[i]) == null) {
                log.info("没有从缓存中找到数据字典" + strArr[i]);
                log.info("从数据库中读取数据字典" + strArr[i]);
                List<Map> voList = sysDictMapper.selectByCode(strArr[i]);
                if (voList.size() == 0) {
                    continue;
                }
                subJsonArr = JSONArray.parseArray(JSON.toJSONString(voList));

                if (SrpmsConstant.PRO_CATEGORY.equals(strArr[i])
                        || SrpmsConstant.SUBJECT_OPTIONS.equals(strArr[i])
                        || SrpmsConstant.BE_CURRENT.equals(strArr[i])) {
                    selectArr = convertDictToTree(subJsonArr, false);
                } else {
                    selectArr = convertDictToSelect(subJsonArr, false);
                }
                redisTemplate.opsForValue().set("sysDict_" + strArr[i], selectArr);
            } else {
                selectArr = (JSONArray) redisTemplate.opsForValue().get("sysDict_" + strArr[i]);
                log.info("从缓存中读取数据字典" + strArr[i]);
            }
            relust.put(strArr[i], selectArr);
        }

        List<SysDict> parentChildList = sysDictMapper.getSysDictTanLongByCode(SrpmsConstant.PRO_CATEGORY);
        if (parentChildList != null && parentChildList.size() > 0) {
            String parentOrChildKey = "CATEGORY_PARENT_CHILDREN";
            String tranLongKey = "CATEGORY_TRAN_LONG";
            SysDict sysDict;
            JSONObject resultJson;
            selectArr = new JSONArray();
            JSONArray tranJsonArr = new JSONArray();
            int codeLength;
            for (Iterator e = parentChildList.iterator(); e.hasNext(); ) {
                sysDict = (SysDict) e.next();
                resultJson = new JSONObject();
                codeLength = sysDict.getDictCode().length();
                if(SrpmsConstant.PRO_CATEGORY.equals(sysDict.getDictCode())) {
                    continue;
                }
                if(SrpmsConstant.CATEGORY_PARENT_CHILDREN.equals(parentOrChildKey) && codeLength != 4) {
                    if(StringUtils.contains(SrpmsConstant.PRO_TYPE_CATEGORY, sysDict.getDictCode())
                            || StringUtils.contains(SrpmsConstant.TRAN_LONG_PRO_TYPE, sysDict.getDictCode())) {
                        resultJson.put("id", sysDict.getDictCode());
                        resultJson.put("label", sysDict.getDictValue());
                        selectArr.add(resultJson);
                    }
                }
                if(SrpmsConstant.CATEGORY_TRAN_LONG.equals(tranLongKey) && codeLength != 4) {
                    if(StringUtils.contains(SrpmsConstant.TRAN_LONG_PRO_TYPE, sysDict.getDictCode())) {
                        resultJson.put("id", sysDict.getDictCode());
                        resultJson.put("label", sysDict.getDictValue());
                        tranJsonArr.add(resultJson);
                    }
                }
            }
            relust.put(parentOrChildKey, selectArr);// 项目类别下拉(包含二级父级与三级子级)
            relust.put(tranLongKey, tranJsonArr);// 项目类别下拉(横纵向项目)
        }

        return relust;
    }

    public JSONArray convertDictToTree(JSONArray jsonArr, boolean mainTainFlag) {

        JSONArray reslut = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject sourceJson = jsonArr.getJSONObject(i);
            int level;
            if(mainTainFlag) {// 添加基础信息维护分支
                level = sourceJson.getInteger("LEVEL");
            } else {
                if (i == 0) {
                    continue;
                }
                level = sourceJson.getInteger("LEVEL") - 1;
            }
            JSONObject reslutJson = getJsonWithIdAndLabel(sourceJson, mainTainFlag);

            if (level == 1) {
                reslut.add(reslutJson);
            } else {

                JSONObject children = reslut.getJSONObject(reslut.size() - 1);
                JSONArray childrenArr = null;
                boolean addFlg = true;
                for (int k = 1; k < level; k++) {
                    childrenArr = children.getJSONArray("children");
                    if (childrenArr == null) {
                        JSONArray tempArr = new JSONArray();
                        tempArr.add(reslutJson);
                        children.put("children", tempArr);
                        addFlg = false;
                        break;
                    }
                    children = childrenArr.getJSONObject(childrenArr.size() - 1);
                }
                if (addFlg) {
                    childrenArr.add(reslutJson);
                }
            }
        }
        return reslut;
    }

    public JSONArray convertDictToSelect(JSONArray jsonArr, boolean mainTainFlag) {

        JSONArray reslut = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            if (!mainTainFlag && i == 0) {
                continue;
            }
            JSONObject sourceJson = jsonArr.getJSONObject(i);
            JSONObject reslutJson = getJsonWithIdAndLabel(sourceJson, mainTainFlag);
            reslut.add(reslutJson);
        }
        return reslut;
    }

    public JSONObject getJsonWithIdAndLabel(JSONObject sourceJson, boolean mainTainFlag) {
        JSONObject reslutJson = new JSONObject();
        if(mainTainFlag) {
            reslutJson.put("id", sourceJson.getString("ID"));
            reslutJson.put("keyCode", sourceJson.getString("DICT_CODE"));
            reslutJson.put("keyName", sourceJson.getString("DICT_VALUE"));
            reslutJson.put("activeFlag", sourceJson.getInteger("IS_EXPIRED") == 0 ? true : false);
        } else {
            reslutJson.put("id", sourceJson.getString("DICT_CODE"));
            reslutJson.put("label", sourceJson.getString("DICT_VALUE"));
        }
        return reslutJson;
    }

    @Override
    public Map<String, String> getDictByCategory(String sysDictCategory) {
        List<SysDict> sysDictList = sysDictMapper.selectSysDictByCategory(sysDictCategory);
        Map<String, String> sysMap = new TreeMap<>();
        for (SysDict sysDict : sysDictList) {
            sysMap.put(sysDict.getDictCode(), sysDict.getDictValue());
        }
        return sysMap;
    }

    /**
     * 根据字典编码查询字典信息service接口,根据code匹配名称
     *
     * @param dictCodes
     * @return
     */
    @Override
    public Map<String, String> getSysDictMap(String[] dictCodes) {

        Map<String, String> result = new HashMap<>();
        List<SysDict> list;
        SysDict sysDict;
        String tempKey;

        for (int i = 0; i < dictCodes.length; i++) {
            tempKey = dictCodes[i];
            if (SrpmsConstant.PRO_CATEGORY.equals(tempKey)) {
                list = sysDictMapper.getSysDictTreeByCode(tempKey);
            } else {
                list = sysDictMapper.selectByCodes(tempKey);
            }

            if (list != null && list.size() > 0) {
                for (Iterator e = list.iterator(); e.hasNext(); ) {
                    sysDict = (SysDict) e.next();
                    result.put(tempKey.concat(sysDict.getDictCode()), sysDict.getDictValue());
                }
            }
        }
        return result;
    }

    /**
     * 横纵向项目查询项目类型下拉
     *
     * @param dictCode
     * @return
     */
    @Override
    public Map<String, String> getSysDictTranLong(String dictCode) {

        Map<String, String> result = new HashMap<>();
        List<SysDict> list = sysDictMapper.getSysDictTanLongByCode(dictCode);
        if (list != null && list.size() > 0) {
            SysDict sysDict;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                sysDict = (SysDict) e.next();
                if (SrpmsConstant.PRO_CATEGORY.equals(sysDict.getDictCode())) {
                    continue;
                }
                if (sysDict.getDictCode().length() == 4) {
                    result.put(sysDict.getDictCode(), sysDict.getDictValue().substring(sysDict.getDictValue().lastIndexOf("-") + 1));
                } else {
                    result.put(sysDict.getDictCode(), sysDict.getDictValue());
                }
            }
        }
        return result;
    }

    /**
     * 根据字典编码查询字典信息service接口,根据名称匹配code
     *
     * @param dictCodes
     * @return
     */
    @Override
    public Map<String, String> getSysDictCodeByValueMap(String[] dictCodes) {

        Map<String, String> result = new HashMap<>();
        List<SysDict> list;
        SysDict sysDict;
        String tempKey;

        for (int i = 0; i < dictCodes.length; i++) {
            tempKey = dictCodes[i];
            list = sysDictMapper.selectByCodes(tempKey);

            if (list != null && list.size() > 0) {
                for (Iterator e = list.iterator(); e.hasNext(); ) {
                    sysDict = (SysDict) e.next();
                    result.put(tempKey.concat(sysDict.getDictValue()), sysDict.getDictCode());
                }
            }
        }
        return result;
    }

    /**
     * 根据字典编码查询字典信息service接口,树形-根据名称匹配code
     *
     * @param dictCode
     * @return
     */
    @Override
    public Map<String, String> getSysDictSelectValueByCode(String dictCode) {

        Map<String, String> result = new HashMap<>();
        List<SysDict> list = sysDictMapper.getSysDictTreeByCode(dictCode);
        if (list != null && list.size() > 0) {
            SysDict sysDict;
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                sysDict = (SysDict) e.next();
                result.put(sysDict.getDictValue(), sysDict.getDictCode());
            }
        }
        return result;
    }

    /**
     * 查询字典list数组，匹配excel下拉
     *
     * @param dictCodes
     * @return
     */
    @Override
    public Map<String, List<String>> getSysDictListMap(String[] dictCodes) {
        List<String> dictCodeList = Arrays.asList(dictCodes);
        List<SysDict> list = sysDictMapper.getSysDictSelectByCodes(dictCodeList);
        Map<String, List<String>> result = new HashMap<>();
        List<String> caregoryList;
        SysDict sysDict;
        String tempKey;
        for (int i = 0; i < dictCodes.length; i++) {
            caregoryList = new ArrayList<>();
            tempKey = dictCodes[i];
            for (Iterator e = list.iterator(); e.hasNext(); ) {
                sysDict = (SysDict) e.next();
                if (tempKey.equals(sysDict.getDictCode())) {
                    caregoryList.add(sysDict.getDictValue());
                }
            }
            result.put(tempKey, caregoryList);
        }

        return result;
    }

    /**
     * 查询字典树形list数组，匹配excel下拉
     *
     * @param dictCode
     * @return
     */
    @Override
    public Map<String, List<String>> getSysDictSelectCodeByCode(String dictCode) {
        List<SysDict> list = sysDictMapper.getSysDictTreeByCode(dictCode);
        Map<String, List<String>> result = new HashMap<>();
        List<String> caregoryList = new ArrayList<>();
        SysDict sysDict;
        for (Iterator e = list.iterator(); e.hasNext(); ) {
            sysDict = (SysDict) e.next();
            caregoryList.add(sysDict.getDictValue());
        }
        result.put(dictCode, caregoryList);
        return result;
    }

    /**
     * 通用查询
     *
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SysDict> getQueryWrapper(QueryWrapper<SysDict> queryWrapper, SysDictQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getDictCode())) {
            queryWrapper.like(SysDict.DICT_CODE, queryForm.getDictCode());
        }
        if (StringUtils.isNotBlank(queryForm.getDictValue())) {
            queryWrapper.like(SysDict.DICT_VALUE, queryForm.getDictValue());
        }
        queryWrapper.isNull(SysDict.DICT_PARENT);
        queryWrapper.eq(SysDict.IS_EXPIRED, "0");// 是否有效
        queryWrapper.eq(SysDict.EDIT_FLAG, "0");// 是否修改

        queryWrapper.orderByDesc(SysDict.CREATE_TIME);

        return queryWrapper;
    }

}


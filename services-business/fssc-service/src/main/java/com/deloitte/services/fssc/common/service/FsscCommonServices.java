package com.deloitte.services.fssc.common.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.api.fssc.unit.vo.UnitBankBaseInfoVo;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubType;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import com.deloitte.services.fssc.base.service.IBaseExpenseSubTypeService;
import com.deloitte.services.fssc.base.service.IBaseIncomeSubTypeService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.system.dic.entity.Dic;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.deloitte.services.fssc.system.dic.service.IDicService;
import com.deloitte.services.fssc.system.dic.service.IDicValueService;
import com.deloitte.services.fssc.system.unit.service.IUnitInfoService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class FsscCommonServices {

    @Autowired
    private IDicService dicService;
    @Autowired
    private IDicValueService dicValueService;
    @Autowired
    private IUnitInfoService unitInfoService;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private IBaseIncomeSubTypeService baseIncomeSubTypeService;

    @Autowired
    private IBaseExpenseSubTypeService baseExpenseSubTypeService;

    /**
     * 查询有效的值列表
     */
    public List<DicValueVo> findDicValueList(String code) {
        Object object = redisTemplate.opsForValue().get("FSSC_DIC_" + code);
        if (object != null) {
            List<DicValueVo> dicValueVoList = JSONArray.parseArray(JSON.toJSONString(object), DicValueVo.class);
            return dicValueVoList;
        }
        QueryWrapper<Dic> dicQueryWrapper = new QueryWrapper<>();
        dicQueryWrapper.eq("EUM_CODE", code).eq("IS_VALID", "Y");
        Dic one = dicService.getOne(dicQueryWrapper);
        if (one != null) {
            QueryWrapper<DicValue> dicValueQueryWrapper = new QueryWrapper<>();
            dicValueQueryWrapper.eq("DIC_PARENT_ID", one.getId()).eq("IS_VALID", "Y");
            List<DicValueVo> dicValueVoList = new BeanUtils<DicValueVo>()
                    .copyObjs(dicValueService.list(dicValueQueryWrapper), DicValueVo.class);
            redisTemplate.opsForValue().set("FSSC_DIC_" + code,dicValueVoList,1, TimeUnit.HOURS);
            return dicValueVoList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 查询所有的值列表
     */
    public List<DicValueVo> findAllDiList(String code) {
        QueryWrapper<Dic> dicQueryWrapper = new QueryWrapper<>();
        dicQueryWrapper.eq("EUM_CODE", code);
        Dic one = dicService.getOne(dicQueryWrapper);
        if (one != null) {
            QueryWrapper<DicValue> dicValueQueryWrapper = new QueryWrapper<>();
            dicValueQueryWrapper.eq("DIC_PARENT_ID", one.getId());
            return new BeanUtils<DicValueVo>()
                    .copyObjs(dicValueService.list(dicValueQueryWrapper), DicValueVo.class);
        } else {
            return new ArrayList<>();
        }

    }


    public Map<String, String> getValueByCode(List<DicValueVo> list) {
        Map<String, String> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (DicValueVo vo : list) {
                map.put(vo.getDicValue(), vo.getDicDesciption());
            }
        }
        return map;
    }

    /**
     * 查询银行相关信息
     */
    public UnitBankBaseInfoVo unitBankBaseUnitType(Long id) {
        return unitInfoService.unitBankBaseUnitType(id);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public UserVo getCurrentUser() {
        UserVo userVo = UserUtil.getUserVo();
        AssertUtils.asserts(userVo != null && userVo.getId() != null, FsscErrorType.GET_USER_NOT_EXIST);
        return userVo;
    }

    /**
     * 用户当前用户的单位
     *
     * @return
     */
    public DeptVo getCurrentDept() {
        DeptVo deptVo = UserUtil.getDept();
        AssertUtils.asserts(deptVo != null && deptVo.getId() != null, FsscErrorType.GET_DEPT_NOT_EXIST);
        return deptVo;
    }

    /**
     * 获取当前用户的处室
     *
     * @return
     */
    public OrganizationVo getCurrentOrg() {
        OrganizationVo orgVo = UserUtil.getOrganization();
        AssertUtils.asserts(orgVo != null && orgVo.getId() != null, FsscErrorType.GET_ORG_NOT_EXIST);
        return orgVo;
    }

    public JSONArray getCurrentMenu() {
        ResourceVo resourceVo = UserUtil.getMenu();
        return resourceVo != null ? getFsscMenu(resourceVo) : new JSONArray();
        //AssertUtils.asserts(resourceVo != null, FsscErrorType.GET_RESOURCE_NOT_EXIST);
    }

    public List<RoleVo> getCurrentRoles(){
        List<RoleVo> roleVoList = UserUtil.getRoles(FsscConstants.SYS_NAME_FOR_4A);
        AssertUtils.asserts(CollectionUtils.isNotEmpty(roleVoList), FsscErrorType.GET_ROLE_NOT_EXIST);
        return roleVoList;
    }

    public RoleVo getCurrentRole() {
        List<RoleVo> roleVoList = UserUtil.getRoles(FsscConstants.SYS_NAME_FOR_4A);
        AssertUtils.asserts(CollectionUtils.isNotEmpty(roleVoList), FsscErrorType.GET_ROLE_NOT_EXIST);
        return roleVoList.get(0);
    }

    /**
     * 设置会计科目code
     *
     * @param o
     */
    public void setAccountCode(Object o) {

        String accountMethod = "setAccountCode";
        String yuAccountMethod = "setBudgetAccountCode";
        String getInSubType = "getInComeSubTypeId";
        String getSubType = "getSubTypeId";

        Method accountM = null;
        Method yuAccountM = null;
        Method getIn = null;
        Method getSub = null;
        Class<?> aClass = o.getClass();
        try {
            accountM = aClass.getMethod(accountMethod, java.lang.String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            yuAccountM = aClass.getMethod(yuAccountMethod, java.lang.String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getIn = aClass.getMethod(getInSubType, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getSub = aClass.getMethod(getSubType, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long inComeSubTypeId = null;
        if (getIn != null) {
            try {
                Object invoke = getIn.invoke(o);
                if (invoke != null) {
                    String inId = StringUtil.objectToString(invoke);
                    inComeSubTypeId = StringUtil.castTolong(inId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Long subTypeId = null;
        if (getSub != null) {
            try {
                Object invoke = getSub.invoke(o);
                if (invoke != null) {
                    String inId = StringUtil.objectToString(invoke);
                    subTypeId = StringUtil.castTolong(inId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (inComeSubTypeId != null) {
            BaseIncomeSubType incomeSubType = baseIncomeSubTypeService.getById(inComeSubTypeId);
            if (incomeSubType != null) {
                String cAccountCode = incomeSubType.getCAccountCode();
                String yAccountCode = incomeSubType.getYAccountCode();
                if (accountM != null && yuAccountM != null) {
                    try {
                        accountM.invoke(o, cAccountCode);
                        yuAccountM.invoke(o, yAccountCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        if (subTypeId != null) {
            BaseExpenseSubType expenseSubType = baseExpenseSubTypeService.getById(subTypeId);
            if (expenseSubType != null) {
                String cAccountCode = expenseSubType.getCAccountCode();
                String yAccountCode = expenseSubType.getYAccountCode();
                if (accountM != null && yuAccountM != null) {
                    try {
                        accountM.invoke(o, cAccountCode);
                        yuAccountM.invoke(o, yAccountCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /**
     * 财务系统菜单
     *
     * @param rootResource
     * @return
     */
    public static JSONArray getFsscMenu(ResourceVo rootResource) {
        JSONArray menuArray = new JSONArray();
        JSONObject functionJson = JSONObject
                .parseObject(JSONObject.toJSONString(rootResource));
        JSONArray functionJsonArray = functionJson.getJSONArray("children");
        if (functionJsonArray != null) {
            for (int i = 0; i < functionJsonArray.size(); i++) {
                //一级菜单
                JSONObject functionLevel1 = functionJsonArray.getJSONObject(i);
                JSONObject menuLevel1 = new JSONObject();
                menuLevel1.put("menuName", functionLevel1.getString("name"));
                if (functionLevel1.getJSONArray("children") != null) {
                    //二级菜单
                    JSONArray functionLevel1Child = functionLevel1.getJSONArray("children");
                    JSONArray menuLevel1Child = new JSONArray();
                    for (int k = 0; k < functionLevel1Child.size(); k++) {
                        JSONObject functionLevel2 = functionLevel1Child.getJSONObject(k);
                        JSONObject menuLevel2 = new JSONObject();
                        menuLevel2.put("menuName", functionLevel2.getString("name"));
                        menuLevel2.put("name", (functionLevel2.getString("uri") + "").replace("/", ""));
                        if (functionLevel2.getJSONArray("children") != null) {
                            JSONArray functionLevel2Child = functionLevel2.getJSONArray("children");
                            JSONArray menuLevel2Child = new JSONArray();
                            for (int j = 0; j < functionLevel2Child.size(); j++) {
                                JSONObject functionLevel3 = functionLevel2Child.getJSONObject(j);
                                JSONObject menuLevel3 = new JSONObject();
                                menuLevel3.put("menuName", functionLevel3.getString("name"));
                                menuLevel3.put("name", (functionLevel3.getString("uri") + "").replace("/", ""));
                                if (functionLevel3.getJSONArray("children") != null) {
                                    JSONArray functionLevel3Child = functionLevel3.getJSONArray("children");
                                    JSONArray menuLevel3Child = new JSONArray();
                                    for (int m = 0; m < functionLevel3Child.size(); m++) {
                                        JSONObject functionLevel4 = functionLevel3Child.getJSONObject(j);
                                        JSONObject menuLevel4 = new JSONObject();
                                        menuLevel4.put("menuName", functionLevel4.getString("name"));
                                        menuLevel4.put("name", (functionLevel4.getString("uri") + "").replace("/", ""));
                                        menuLevel4.put("path", functionLevel4.getString("uri"));
                                        menuLevel3Child.add(menuLevel4);
                                    }
                                    menuLevel3.put("children", menuLevel3Child);
                                }
                                menuLevel3.put("path", functionLevel3.getString("uri"));
                                menuLevel2Child.add(menuLevel3);
                            }
                            menuLevel2.put("children", menuLevel2Child);
                        } else {
                            menuLevel2.put("name", (functionLevel2.getString("uri") + "").replace("/", ""));
                            menuLevel2.put("path", functionLevel2.getString("uri"));
                        }
                        menuLevel1Child.add(menuLevel2);
                    }
                    menuLevel1.put("children", menuLevel1Child);
                } else {
                    menuLevel1.put("name", (functionLevel1.getString("uri") + "").replace("/", ""));
                    menuLevel1.put("path", functionLevel1.getString("uri"));
                }
                menuArray.add(menuLevel1);
            }
        }
        return menuArray;
    }
}

package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAwardQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAwardQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAward;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineAwardMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineAwardService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :  SrpmsOutlineAward服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineAwardServiceImpl extends ServiceImpl<SrpmsOutlineAwardMapper, SrpmsOutlineAward> implements ISrpmsOutlineAwardService {


    @Autowired
    private SrpmsOutlineAwardMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询奖励service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineAwardVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineAwardQueryForm queryForm) {
        SrpmsOutlineAwardVoList dataVoList = new SrpmsOutlineAwardVoList();

        // 执行查询
        List<SrpmsOutlineAwardVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-奖励service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineAwardFormList data, UserVo user) {

        SrpmsOutlineErrorList resultError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-奖励删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-奖励新增方法,返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), resultError, user);
        }
        return resultError;
    }

    /**
     * 读取题录-奖励excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineAwardVoList srpmsOutlineVoList = new SrpmsOutlineAwardVoList();
        List<SrpmsOutlineAwardVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineAwardVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.AWARD_CAT, SrpmsConstant.AWARD_NAME, SrpmsConstant.AWARD_LEVEL});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 11) {
                vo = new SrpmsOutlineAwardVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setAwardCat(dictMap.get(SrpmsConstant.AWARD_CAT.concat(strings[4] == null ? "" : CommonUtil.deleteStringSpace(strings[4]))));// 奖励类别
                        vo.setAwardName(dictMap.get(SrpmsConstant.AWARD_NAME.concat(strings[7] == null ? "" : CommonUtil.deleteStringSpace(strings[7]))));// 奖项名称
                        vo.setAwardLevel(dictMap.get(SrpmsConstant.AWARD_LEVEL.concat(strings[8] == null ? "" : CommonUtil.deleteStringSpace(strings[8]))));// 奖项等级
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setAwardResults(strings[3]);// 获奖成果
                    vo.setAwardCatName(strings[4]);// 奖励类别名称
                    vo.setCompletionOrg(strings[5]);// 完成单位
                    vo.setCompletionPerson(strings[6]);// 主要完成人
                    vo.setAwardNameDict(strings[7]);// 奖项字典名称
                    vo.setAwardLevelName(strings[8]);// 奖项等级名称
                    vo.setProCode(strings[9]);// 项目编号
                    vo.setProName(strings[10]);// 项目名称
                    voList.add(vo);
                }
            } else {
                return new Result(SrpmsErrorType.IMPORT_EXCEL_ERROR.getCode(), SrpmsErrorType.IMPORT_EXCEL_ERROR.getMsg(), null);
            }
        }
        srpmsOutlineVoList.setSrpmsOutlineVoList(voList);

        return Result.success(srpmsOutlineVoList);
    }

    /**
     * 导出数据service接口
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineAwardVo> exportExcelQuery(SrpmsOutlineAwardQueryForm queryForm) {
        List<SrpmsOutlineAwardQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineAwardVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.AWARD_CAT, SrpmsConstant.AWARD_NAME, SrpmsConstant.AWARD_LEVEL});

        SrpmsOutlineAwardVo srpmsOutlineVo;
        SrpmsOutlineAwardQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineAwardVo();
            srpmsOutline = (SrpmsOutlineAwardQueryParam) iterator.next();
            BeanUtils.copyProperties(srpmsOutline, srpmsOutlineVo);

            // 查询单位名称
            if (!keyMap.containsKey(srpmsOutlineVo.getOrgId()) && srpmsOutlineVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(srpmsOutlineVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            } else {
                keyMap.put(srpmsOutlineVo.getOrgId(), orgName);
            }
            srpmsOutlineVo.setOrgName(orgName);

            if (dictMap != null) {
                if (StringUtils.isNotBlank(srpmsOutlineVo.getAwardCat())) {
                    srpmsOutlineVo.setAwardCatName(dictMap.get(SrpmsConstant.AWARD_CAT.concat(srpmsOutlineVo.getAwardCat())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getAwardName())) {
                    srpmsOutlineVo.setAwardNameDict(dictMap.get(SrpmsConstant.AWARD_NAME.concat(srpmsOutlineVo.getAwardName())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getAwardLevel())) {
                    srpmsOutlineVo.setAwardLevelName(dictMap.get(SrpmsConstant.AWARD_LEVEL.concat(srpmsOutlineVo.getAwardLevel())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-奖励操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineAwardForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineAwardForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineAwardForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-奖励操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineAwardForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineAwardForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineAward srpmsOutline;
                SrpmsOutlineAwardForm titleForm;
                String key;
                boolean checkFlag;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                int indexRow = 0;
                List<SrpmsOutlineAward> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineAward> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineAward();
                    titleForm = (SrpmsOutlineAwardForm) e.next();

                    Long orgId = titleForm.getOrgId();
                    String year = titleForm.getYear();
                    String month = titleForm.getMonth();

                    indexRow++;
                    checkFlag = false;

                    checkFlag = checkSrpmsOutlineFields(titleForm, checkFlag, stringBuilder);

                    // 点击保存的时候验证数据
                    if (checkFlag) {
                        stringBefore.append("第");
                        stringBefore.append(indexRow);
                        stringBefore.append("条：");
                        stringBuilder.insert(0, stringBefore);
                        listError.add(titleForm);
                        continue;
                    }

                    // 拼接条件重复验证 单位+年月+获奖名称
                    key = new StringBuilder()
                            .append(orgId)
                            .append(year)
                            .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getAwardName())).toString();

                    if (keyMap.containsKey(key)) {
                        stringBuilder.append("第");
                        stringBuilder.append(indexRow);
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据与");
                        stringBuilder.append("第");
                        stringBuilder.append(keyMap.get(key));
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据重复；");
                        listError.add(titleForm);
                        continue;
                    }
                    // 验证数据是否存在
                    if (getSrpmsOutlineExists(titleForm)) {
                        stringBuilder.append("第");
                        stringBuilder.append(indexRow);
                        stringBuilder.append("条");
                        stringBuilder.append("保存数据已存在；");
                        listError.add(titleForm);
                        continue;
                    }

                    BeanUtils.copyProperties(titleForm, srpmsOutline);

                    // 根据条件查询题录基础信息表
                    SrpmsOutlineBase srpmsOutlineBase =
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_02, orgId, year, month);
                    Long baseId;
                    if (srpmsOutlineBase != null) {
                        baseId = srpmsOutlineBase.getId();
                        srpmsOutlineBase.setOrgId(orgId);
                        srpmsOutlineBase.setYear(year);
                        srpmsOutlineBase.setMonth(month);
                        srpmsOutlineBaseService.saveOrUpdate(srpmsOutlineBase);
                    } else {
                        srpmsOutlineBase = new SrpmsOutlineBase();
                        // 执行新增题录基础表信息
                        srpmsOutlineBase.setOrgId(orgId);
                        srpmsOutlineBase.setYear(year);
                        srpmsOutlineBase.setMonth(month);
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_02);
                        // 执行保存操作
                        srpmsOutlineBaseService.save(srpmsOutlineBase);

                        baseId = srpmsOutlineBase.getId();
                    }
                    if (StringUtils.isBlank(titleForm.getId())) {

                        srpmsOutline.setBaseId(baseId);
                        srpmsOutline.setCreateBy(user.getId());
                        listAddSuccess.add(srpmsOutline);
                    } else {
                        srpmsOutline.setId(Long.valueOf(titleForm.getId()));
                        srpmsOutline.setBaseId(baseId);
                        srpmsOutline.setUpdateBy(user.getId());
                        listEditSuccess.add(srpmsOutline);
                    }
                    keyMap.put(key, indexRow);
                }
                // 错误信息返回
                if (!listError.isEmpty()) {
                    String errorMsg = stringBuilder.toString();
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                    resultError.setErrorMsg(errorMsg);
                    resultError.setErrorFlag(true);
                    resultError.setListError(listError);
                } else {
                    if (!listAddSuccess.isEmpty()) {// 批量新增
                        this.saveBatch(listAddSuccess);
                    }
                    if (!listEditSuccess.isEmpty()) {// 批量更新
                        this.saveOrUpdateBatch(listEditSuccess);
                    }
                }
            } catch (Exception e) {
                log.error("保存操作出现异常" + e.getMessage());
                throw new BaseException(PlatformErrorType.ADD_ERROR);
            }
        }

        return resultError;
    }

    /**
     * 验证奖励数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineAwardForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+年月+获奖名称)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("year", titleForm.getYear());
        paramMap.put("month", titleForm.getMonth());
        paramMap.put("awardName", CommonUtil.deleteStringSpace(titleForm.getAwardName()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineAwardForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
        // 单位ID
        if (titleForm.getOrgId() == null || titleForm.getOrgId() == 0) {
            checkFlag = true;
            errorMsg.append("单位ID为空,");
        }
        // 年
        if (StringUtils.isBlank(titleForm.getYear())) {
            checkFlag = true;
            errorMsg.append("年为空,");
        }
        // 获奖成果
        if (StringUtils.isBlank(titleForm.getAwardResults())) {
            checkFlag = true;
            errorMsg.append("获奖成果为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAwardResults(), 200)) {
            checkFlag = true;
            errorMsg.append("获奖成果超过设定长度,");
        }
        // 奖项类别
        if (StringUtils.isBlank(titleForm.getAwardCat())) {
            checkFlag = true;
            errorMsg.append("奖项类别为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAwardCat(), 10)) {
            checkFlag = true;
            errorMsg.append("奖项类别超过设定长度,");
        }
        // 完成单位
        if (StringUtils.isBlank(titleForm.getCompletionOrg())) {
            checkFlag = true;
            errorMsg.append("完成单位为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCompletionOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("完成单位超过设定长度,");
        }

        // 主要完成人
        if (StringUtils.isBlank(titleForm.getCompletionPerson())) {
            checkFlag = true;
            errorMsg.append("主要完成人为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCompletionPerson(), 200)) {
            checkFlag = true;
            errorMsg.append("主要完成人超过设定长度,");
        }

        // 奖项等级
        if (StringUtils.isBlank(titleForm.getAwardLevel())) {
            checkFlag = true;
            errorMsg.append("奖项等级为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAwardLevel(), 10)) {
            checkFlag = true;
            errorMsg.append("奖项等级超过设定长度,");
        }
        return checkFlag;
    }

}
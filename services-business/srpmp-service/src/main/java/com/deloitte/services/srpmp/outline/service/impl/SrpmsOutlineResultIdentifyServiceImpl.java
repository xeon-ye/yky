package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultIdentify;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineResultIdentifyMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineResultIdentifyService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
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
 * @Description :  SrpmsOutlineResultIdentify服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineResultIdentifyServiceImpl extends ServiceImpl<SrpmsOutlineResultIdentifyMapper, SrpmsOutlineResultIdentify> implements ISrpmsOutlineResultIdentifyService {


    @Autowired
    private SrpmsOutlineResultIdentifyMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询成果鉴定service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineResultIdentifyVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineResultIdentifyQueryForm queryForm) {
        SrpmsOutlineResultIdentifyVoList dataVoList = new SrpmsOutlineResultIdentifyVoList();

        // 执行查询
        List<SrpmsOutlineResultIdentifyVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-成果鉴定service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineResultIdentifyFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-成果鉴定删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-成果鉴定新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-成果鉴定excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineResultIdentifyVoList srpmsOutlineVoList = new SrpmsOutlineResultIdentifyVoList();
        List<SrpmsOutlineResultIdentifyVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineResultIdentifyVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.IDENTIFY_WAY});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 12) {
                vo = new SrpmsOutlineResultIdentifyVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setIdentifyWay(dictMap.get(SrpmsConstant.IDENTIFY_WAY.concat(strings[8] == null ? "" : CommonUtil.deleteStringSpace(strings[8]))));// 鉴定/验收形式
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setProResultName(strings[3]);// 成果名称
                    vo.setCompletionOrg(strings[4]);// 第一完成单位
                    vo.setCompletionPerson(strings[5]);// 完成人
                    vo.setTaskSource(strings[6]);// 任务来源
                    vo.setIdentifyOrg(strings[7]);// 鉴定/验收单位
                    vo.setIdentifyWayName(strings[8]);// 鉴定/验收形式名称
                    vo.setIdentifyTime(DateUtil.strToLocalDateTime(strings[9]));// 鉴定/验收时间
                    vo.setProCode(strings[10]);// 项目编号
                    vo.setProName(strings[11]);// 项目名称
                    voList.add(vo);
                }
            } else {
                return new Result(SrpmsErrorType.IMPORT_EXCEL_ERROR.getCode(), SrpmsErrorType.IMPORT_EXCEL_ERROR.getMsg(), null);
            }
        }
        srpmsOutlineVoList.setSrpmsOutlineVoList(voList);

        return Result.success(srpmsOutlineVoList);
    }

    @Override
    public List<SrpmsOutlineResultIdentifyVo> exportExcelQuery(SrpmsOutlineResultIdentifyQueryForm queryForm) {
        List<SrpmsOutlineResultIdentifyQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineResultIdentifyVo> srpmsOutlineVoList = new ArrayList<>();// 保存集合

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.IDENTIFY_WAY});

        SrpmsOutlineResultIdentifyVo srpmsOutlineVo;
        SrpmsOutlineResultIdentifyQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineResultIdentifyVo();
            srpmsOutline = (SrpmsOutlineResultIdentifyQueryParam) iterator.next();
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

            if (dictMap != null && StringUtils.isNotBlank(srpmsOutlineVo.getIdentifyWay())) {
                srpmsOutlineVo.setIdentifyWayName(dictMap.get(SrpmsConstant.IDENTIFY_WAY.concat(srpmsOutlineVo.getIdentifyWay())));
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-成果鉴定操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineResultIdentifyForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineResultIdentifyForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineResultIdentifyForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-成果鉴定操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineResultIdentifyForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineResultIdentifyForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineResultIdentify srpmsOutline;
                SrpmsOutlineResultIdentifyForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineResultIdentify> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineResultIdentify> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineResultIdentify();
                    titleForm = (SrpmsOutlineResultIdentifyForm) e.next();

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

                    // 拼接条件重复验证 单位+年月+成果名称
                    key = new StringBuilder()
                            .append(orgId)
                            .append(year)
                            .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getProResultName())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_03, orgId, year, month);
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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_03);
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
     * 验证成果鉴定数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineResultIdentifyForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+年月+成果名称)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("year", titleForm.getYear());
        paramMap.put("month", titleForm.getMonth());
        paramMap.put("proResultName", CommonUtil.deleteStringSpace(titleForm.getProResultName()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineResultIdentifyForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
        // 单位ID
        if (titleForm.getOrgId() == null || titleForm.getOrgId() == 0) {
            checkFlag = true;
            errorMsg.append("单位ID为空，");
        }
        // 年
        if (StringUtils.isBlank(titleForm.getYear())) {
            checkFlag = true;
            errorMsg.append("年为空，");
        }
        // 成果名称
        if (StringUtils.isBlank(titleForm.getProResultName())) {
            checkFlag = true;
            errorMsg.append("成果名称为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProResultName(), 200)) {
            checkFlag = true;
            errorMsg.append("成果名称超过设定长度，");
        }
        // 第一完成单位
        if (StringUtils.isBlank(titleForm.getCompletionOrg())) {
            checkFlag = true;
            errorMsg.append("第一完成单位为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCompletionOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("第一完成单位超过设定长度，");
        }
        // 完成人
        if (StringUtils.isBlank(titleForm.getCompletionPerson())) {
            checkFlag = true;
            errorMsg.append("完成人为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCompletionPerson(), 200)) {
            checkFlag = true;
            errorMsg.append("完成人超过设定长度，");
        }
        // 任务来源
        if (StringUtils.isBlank(titleForm.getTaskSource())) {
            checkFlag = true;
            errorMsg.append("任务来源为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getTaskSource(), 200)) {
            checkFlag = true;
            errorMsg.append("任务来源超过设定长度，");
        }
        // 鉴定/验收单位
        if (StringUtils.isBlank(titleForm.getIdentifyOrg())) {
            checkFlag = true;
            errorMsg.append("鉴定/验收单位为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getIdentifyOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("鉴定/验收单位超过设定长度，");
        }
        // 鉴定/验收形式
        if (StringUtils.isBlank(titleForm.getIdentifyWay())) {
            checkFlag = true;
            errorMsg.append("鉴定/验收形式为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getIdentifyWay(), 200)) {
            checkFlag = true;
            errorMsg.append("鉴定/验收形式超过设定长度，");
        }
        // 鉴定/验收时间
        if (titleForm.getIdentifyTime() == null) {
            checkFlag = true;
            errorMsg.append("鉴定/验收时间为空，");
        }
        return checkFlag;
    }

}
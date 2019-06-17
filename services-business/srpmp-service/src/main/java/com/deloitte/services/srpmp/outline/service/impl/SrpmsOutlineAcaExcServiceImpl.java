package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAcaExc;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineAcaExcMapper;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlinePaperMapper;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineSatBookMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineAcaExcService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
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
 * @Description :  SrpmsOutlineAcaExc服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineAcaExcServiceImpl extends ServiceImpl<SrpmsOutlineAcaExcMapper, SrpmsOutlineAcaExc> implements ISrpmsOutlineAcaExcService {


    @Autowired
    private SrpmsOutlineAcaExcMapper srpmsOutlineMapper;

    @Autowired
    private SrpmsOutlineSatBookMapper srpmsOutlineSatBookMapper;

    @Autowired
    private SrpmsOutlinePaperMapper srpmsOutlinePaperMapper;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询学术交流service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineAcaExcVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineAcaExcQueryForm queryForm) {

        SrpmsOutlineAcaExcVoList dataVoList = new SrpmsOutlineAcaExcVoList();

        // 执行查询
        List<SrpmsOutlineAcaExcVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;

    }

    /**
     * 保存题录-学术交流service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineAcaExcFormList data, UserVo user) {

        SrpmsOutlineErrorList resultError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-学术交流删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-学术交流新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), resultError, user);
        }
        return resultError;
    }

    /**
     * 读取题录-学术交流excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineAcaExcVoList srpmsOutlineVoList = new SrpmsOutlineAcaExcVoList();
        List<SrpmsOutlineAcaExcVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineAcaExcVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.PATENT_REGION, SrpmsConstant.SPONSOR_FLAG});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 10) {
                vo = new SrpmsOutlineAcaExcVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setRegion(dictMap.get(SrpmsConstant.PATENT_REGION.concat(strings[3] == null ? "" : CommonUtil.deleteStringSpace(strings[3]))));// 区域
                        vo.setSponsorFlag(dictMap.get(SrpmsConstant.SPONSOR_FLAG.concat(strings[4] == null ? "" : CommonUtil.deleteStringSpace(strings[4]))));// 主办/参加
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setRegionName(strings[3]);// 区域
                    vo.setSponsorFlagName(strings[4]);// 主办/参加
                    vo.setJoinOrg(strings[5]);// 参与单位
                    vo.setJoinType(strings[6]);// 会议类型
                    vo.setJoinCat(strings[7]);// 参与形式
                    if (CommonUtil.isValidDate(strings[8])) {
                        vo.setHoldingTime(DateUtil.strToLocalDateTime(strings[8]));// 举办时间
                    }
                    vo.setTeamName(strings[9]);// 会议名称
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
     * 导出数据查询service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineAcaExcVo> exportExcelQuery(SrpmsOutlineAcaExcQueryForm queryForm) {
        if (StringUtils.isNotBlank(queryForm.getHoldingTime())) {
            queryForm.setHoldingTime(DateUtil.strDateToStr(queryForm.getHoldingTime()));
        }
        List<SrpmsOutlineAcaExcQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineAcaExcVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PATENT_REGION, SrpmsConstant.SPONSOR_FLAG});

        SrpmsOutlineAcaExcVo srpmsOutlineVo;
        SrpmsOutlineAcaExcQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineAcaExcVo();
            srpmsOutline = (SrpmsOutlineAcaExcQueryParam) iterator.next();
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
                if (StringUtils.isNotBlank(srpmsOutlineVo.getRegion())) {
                    srpmsOutlineVo.setRegionName(dictMap.get(SrpmsConstant.PATENT_REGION.concat(srpmsOutlineVo.getRegion())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getSponsorFlag())) {
                    srpmsOutlineVo.setSponsorFlagName(dictMap.get(SrpmsConstant.SPONSOR_FLAG.concat(srpmsOutlineVo.getSponsorFlag())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-学术交流操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineAcaExcForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineAcaExcForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineAcaExcForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-学术交流操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlineAcaExcForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineAcaExcForm> listError = new ArrayList<>();// 返回错误返回界面集合
                List<SrpmsOutlineAcaExc> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineAcaExc> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                SrpmsOutlineAcaExc srpmsOutline;
                SrpmsOutlineAcaExcForm titleForm;
                String key;
                boolean checkFlag;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                int indexRow = 0;

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    indexRow++;
                    srpmsOutline = new SrpmsOutlineAcaExc();
                    titleForm = (SrpmsOutlineAcaExcForm) e.next();

                    Long orgId = titleForm.getOrgId();
                    String year = titleForm.getYear();
                    String month = titleForm.getMonth();

                    checkFlag = false;
                    // 单位ID
                    if (titleForm.getOrgId() == null || titleForm.getOrgId() == 0) {
                        checkFlag = true;
                        stringBuilder.append("单位ID为空,");
                    }
                    // 年
                    if (StringUtils.isBlank(titleForm.getYear())) {
                        checkFlag = true;
                        stringBuilder.append("年为空,");
                    }

                    // 点击保存的时候验证数据
                    if (checkFlag) {
                        stringBefore.append("第");
                        stringBefore.append(indexRow);
                        stringBefore.append("条：");
                        stringBuilder.insert(0, stringBefore);
                        listError.add(titleForm);
                        continue;
                    }

                    // 拼接条件重复验证 单位+年月+主题
                    key = new StringBuilder()
                            .append(orgId)
                            .append(year)
                            .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getTeamName())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_10, orgId, year, month);

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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_10);
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
     * 验证学术交流数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineAcaExcForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+年月+主题)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("year", titleForm.getYear());
        paramMap.put("month", titleForm.getMonth());
        paramMap.put("teamName", CommonUtil.deleteStringSpace(titleForm.getTeamName()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

}
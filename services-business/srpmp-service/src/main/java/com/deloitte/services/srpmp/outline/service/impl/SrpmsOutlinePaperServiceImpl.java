package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePaper;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlinePaperMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlinePaperService;
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
 * @Description :  SrpmsOutlinePaper服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlinePaperServiceImpl extends ServiceImpl<SrpmsOutlinePaperMapper, SrpmsOutlinePaper> implements ISrpmsOutlinePaperService {


    @Autowired
    private SrpmsOutlinePaperMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;


    /**
     * 查询论文service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlinePaperVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlinePaperQueryForm queryForm) {
        SrpmsOutlinePaperVoList dataVoList = new SrpmsOutlinePaperVoList();

        // 执行查询
        List<SrpmsOutlinePaperVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-论文service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlinePaperFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-论文新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-论文excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlinePaperVoList srpmsOutlineVoList = new SrpmsOutlinePaperVoList();
        List<SrpmsOutlinePaperVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlinePaperVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.JOURNAL_PROPERTY, SrpmsConstant.PATENT_REGION, SrpmsConstant.PAPER_CAT});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 19) {
                vo = new SrpmsOutlinePaperVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setPaperCat(dictMap.get(SrpmsConstant.PAPER_CAT.concat(strings[6] == null ? "" : CommonUtil.deleteStringSpace(strings[6]))));// 类别
                        vo.setProperty(dictMap.get(SrpmsConstant.JOURNAL_PROPERTY.concat(strings[14] == null ? "" : CommonUtil.deleteStringSpace(strings[14]))));// 期刊性质
                        vo.setRegion(dictMap.get(SrpmsConstant.PATENT_REGION.concat(strings[15] == null ? "" : CommonUtil.deleteStringSpace(strings[15]))));// 期刊区域
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setCorrespondenceAuthor(strings[3]);// 通讯作者
                    vo.setFirstAuthor(strings[4]);// 第一作者
                    vo.setOtherAuthor(strings[5]);// 其他作者
                    vo.setPaperCatName(strings[6]);// 类别名称
                    vo.setPaperTitle(strings[7]);// 论文题目
                    vo.setJournalTitle(strings[8]);// 期刊名称
                    vo.setPublicationOrg(strings[9]);// 发表单位
                    vo.setPaperVolume(strings[10]);// 卷
                    vo.setStage(strings[11]);// 期
                    vo.setPage(strings[12]);// 页码
                    vo.setInfluenceFactor(CommonUtil.getDoubleValue(strings[13]));// 影响因子
                    vo.setPropertyName(strings[14]);// 期刊性质名称
                    vo.setRegionName(strings[15]);// 期刊区域名称
                    vo.setProCode(strings[16]);// 项目编号
                    vo.setProName(strings[17]);// 项目名称
                    vo.setHproInCharge(strings[18]);// 项目负责人
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
    public List<SrpmsOutlinePaperVo> exportExcelQuery(SrpmsOutlinePaperQueryForm queryForm) {
        List<SrpmsOutlinePaperQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlinePaperVo> srpmsOutlineVoList = new ArrayList<>();

        if (list != null && list.size() > 0) {
            SrpmsOutlinePaperVo srpmsOutlineVo;
            SrpmsOutlinePaperQueryParam srpmsOutline;
            Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.JOURNAL_PROPERTY, SrpmsConstant.PATENT_REGION, SrpmsConstant.PAPER_CAT});
            Iterator iterator = list.iterator();
            Result<DeptVo> deptVoResult;
            String orgName = "";
            Map<Long, Object> keyMap = new HashMap<>();
            while (iterator.hasNext()) {
                srpmsOutlineVo = new SrpmsOutlinePaperVo();
                srpmsOutline = (SrpmsOutlinePaperQueryParam) iterator.next();
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
                    if (StringUtils.isNotBlank(srpmsOutlineVo.getProperty())) {
                        srpmsOutlineVo.setPropertyName(dictMap.get(SrpmsConstant.JOURNAL_PROPERTY.concat(srpmsOutlineVo.getProperty())));
                    }
                    if (StringUtils.isNotBlank(srpmsOutlineVo.getPaperCat())) {
                        srpmsOutlineVo.setPaperCatName(dictMap.get(SrpmsConstant.PAPER_CAT.concat(srpmsOutlineVo.getPaperCat())));
                    }
                    if (StringUtils.isNotBlank(srpmsOutlineVo.getRegion())) {
                        srpmsOutlineVo.setRegionName(dictMap.get(SrpmsConstant.PATENT_REGION.concat(srpmsOutlineVo.getRegion())));
                    }
                }
                srpmsOutlineVoList.add(srpmsOutlineVo);
            }
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-论文操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlinePaperForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlinePaperForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlinePaperForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-论文操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(
            List<SrpmsOutlinePaperForm> dataList,
            SrpmsOutlineErrorList resultError,
            UserVo user
    ) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlinePaperForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlinePaper srpmsOutline;
                SrpmsOutlinePaperForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlinePaper> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlinePaper> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlinePaper();
                    titleForm = (SrpmsOutlinePaperForm) e.next();

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

                    // 拼接条件重复验证 单位+论文名称+期刊名称
                    key = new StringBuilder()
                            .append(orgId)
//                        .append(year)
//                        .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getPaperTitle()))
                            .append(CommonUtil.deleteStringSpace(titleForm.getJournalTitle())).toString();

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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_08, orgId, year, month);
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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_08);
                        // 执行保存操作
                        srpmsOutlineBaseService.save(srpmsOutlineBase);

                        baseId = srpmsOutlineBase.getId();
                    }
                    if (StringUtils.isBlank(titleForm.getId())) {

                        srpmsOutline.setBaseId(baseId);
                        srpmsOutline.setInfluenceFactor(CommonUtil.getDoubleValue(titleForm.getInfluenceFactor()));
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
     * 验证论文数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlinePaperForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+论文名称+期刊名称)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
//        paramMap.put("year", year);
//        paramMap.put("month", month);
        paramMap.put("paperTitle", CommonUtil.deleteStringSpace(titleForm.getPaperTitle()));
        paramMap.put("journalTitle", CommonUtil.deleteStringSpace(titleForm.getJournalTitle()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlinePaperForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 通讯作者
        if (StringUtils.isBlank(titleForm.getCorrespondenceAuthor())) {
            checkFlag = true;
            errorMsg.append("通讯作者为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getCorrespondenceAuthor(), 200)) {
            checkFlag = true;
            errorMsg.append("通讯作者超过设定长度，");
        }
        // 第一作者
        if (StringUtils.isBlank(titleForm.getFirstAuthor())) {
            checkFlag = true;
            errorMsg.append("第一作者为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getFirstAuthor(), 200)) {
            checkFlag = true;
            errorMsg.append("第一作者超过设定长度，");
        }
        // 其他作者
        if (StringUtils.isBlank(titleForm.getOtherAuthor())) {
            checkFlag = true;
            errorMsg.append("其他作者为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getOtherAuthor(), 200)) {
            checkFlag = true;
            errorMsg.append("其他作者超过设定长度，");
        }
        // 类别
        if (StringUtils.isBlank(titleForm.getPaperCat())) {
            checkFlag = true;
            errorMsg.append("类别为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPaperCat(), 200)) {
            checkFlag = true;
            errorMsg.append("类别超过设定长度，");
        }
        // 论文题目
        if (StringUtils.isBlank(titleForm.getPaperTitle())) {
            checkFlag = true;
            errorMsg.append("论文题目为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPaperTitle(), 200)) {
            checkFlag = true;
            errorMsg.append("论文题目超过设定长度，");
        }
        // 期刊名称
        if (StringUtils.isBlank(titleForm.getJournalTitle())) {
            checkFlag = true;
            errorMsg.append("期刊名称为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getJournalTitle(), 200)) {
            checkFlag = true;
            errorMsg.append("期刊名称超过设定长度，");
        }
        // 发表单位
        if (StringUtils.isBlank(titleForm.getPublicationOrg())) {
            checkFlag = true;
            errorMsg.append("发表单位为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPublicationOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("发表单位超过设定长度，");
        }
        // 卷
        if (StringUtils.isBlank(titleForm.getPaperVolume())) {
            checkFlag = true;
            errorMsg.append("卷为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPaperVolume(), 200)) {
            checkFlag = true;
            errorMsg.append("卷超过设定长度，");
        }
        // 期
        if (StringUtils.isBlank(titleForm.getStage())) {
            checkFlag = true;
            errorMsg.append("期为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getStage(), 200)) {
            checkFlag = true;
            errorMsg.append("期超过设定长度，");
        }
        // 页码
        if (StringUtils.isBlank(titleForm.getPage())) {
            checkFlag = true;
            errorMsg.append("页码为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPage(), 200)) {
            checkFlag = true;
            errorMsg.append("页码超过设定长度，");
        }
        // 期刊性质
        if (StringUtils.isBlank(titleForm.getProperty())) {
            checkFlag = true;
            errorMsg.append("期刊性质为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProperty(), 200)) {
            checkFlag = true;
            errorMsg.append("期刊性质超过设定长度，");
        }
        // 期刊区域
        if (StringUtils.isBlank(titleForm.getRegion())) {
            checkFlag = true;
            errorMsg.append("期刊区域为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getRegion(), 200)) {
            checkFlag = true;
            errorMsg.append("期刊区域超过设定长度，");
        }
        // 国外才必须录入影响因子
        if("20".equals(titleForm.getRegion())) {
            if (titleForm.getInfluenceFactor() == null) {
                checkFlag = true;
                errorMsg.append("影响因子为空，");
            }
        }
        return checkFlag;
    }

}


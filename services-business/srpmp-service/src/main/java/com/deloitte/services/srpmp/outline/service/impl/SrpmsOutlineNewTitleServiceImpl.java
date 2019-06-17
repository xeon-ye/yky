package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryParam;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineNewTitle;
import com.deloitte.services.srpmp.outline.mapper.*;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineNewTitleService;
import com.deloitte.services.srpmp.outline.template.SrpmsOutlineAcaExcAndPaperTemplate;
import com.deloitte.services.srpmp.outline.template.SrpmsOutlineAwardTemplate;
import com.deloitte.services.srpmp.outline.template.SrpmsOutlineNewTitleTemplate;
import com.deloitte.services.srpmp.outline.template.SrpmsOutlinePatentAndMedicalTemplate;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.DateUtil;
import com.deloitte.services.srpmp.outline.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-22
 * @Description :  SrpmsOutlineNewTitle服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineNewTitleServiceImpl extends ServiceImpl<SrpmsOutlineNewTitleMapper, SrpmsOutlineNewTitle> implements ISrpmsOutlineNewTitleService {


    @Autowired
    private SrpmsOutlineNewTitleMapper srpmsOutlineMapper;

    @Autowired
    private SrpmsOutlineAwardMapper srpmsOutlineAwardMapper;

    @Autowired
    private SrpmsOutlineMedicalMapper srpmsOutlineMedicalMapper;

    @Autowired
    private SrpmsOutlineAcaExcMapper srpmsOutlineAcaExcMapper;

    @Autowired
    private SrpmsOutlinePatentMapper srpmsOutlinePatentMapper;

    @Autowired
    private SrpmsOutlineSatBookMapper srpmsOutlineSatBookMapper;

    @Autowired
    private SrpmsOutlinePaperMapper srpmsOutlinePaperMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询新获项目service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineNewTitleVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineNewTitleQueryForm queryForm) {

        SrpmsOutlineNewTitleVoList dataVoList = new SrpmsOutlineNewTitleVoList();

        // 执行查询
        List<SrpmsOutlineNewTitleVo> srpmsOutlineVoList = getSrpmsOutlineQueryList(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 前端展示查询service接口实现
     *
     * @param queryForm
     * @param type
     * @return
     */
    @Override
    public SrpmsOutlineReportList getExportReportData(SrpmsOutlineQueryParam queryForm, int type) {


        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件
        paramMap.put("orgId", queryForm.getOrgId());
        paramMap.put("year", queryForm.getYear());
        paramMap.put("month", queryForm.getMonth());

        SrpmsOutlineReportList dataList = new SrpmsOutlineReportList();
        List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList;

        // 查询组装数据返回前端
        switch (type) {
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST:// 新获科技部项目情况统计表
                List<SrpmsOutlineNewTitleReportCountVo> voList = srpmsOutlineMapper.getReportCount(paramMap);
                if (voList == null || voList.isEmpty()) {
                    Result.fail("题录新获项目查询统计没有数据");
                }
                setNewTitleCountAssemblyData(dataList, voList);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND:// 新获科研项目经费情况统计表
                reportOutlayVoList = srpmsOutlineMapper.getReportTotalOutlay(paramMap);
                if (reportOutlayVoList == null || reportOutlayVoList.isEmpty()) {
                    Result.fail("题录新获项目查询总经费没有数据");
                }
                setNewTitleOutlayAssemblyData(dataList, reportOutlayVoList, type);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE:// 到位科研项目经费情况统计表
                reportOutlayVoList = srpmsOutlineMapper.getReportReceiveOutlay(paramMap);
                if (reportOutlayVoList == null || reportOutlayVoList.isEmpty()) {
                    Result.fail("题录新获项目查询到位经费没有数据");
                }
                setNewTitleOutlayAssemblyData(dataList, reportOutlayVoList, type);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FOUR:// 科研成果及获奖情况统计表
                List<SrpmsOutlineAwardReportVo> reportVoList = srpmsOutlineAwardMapper.getReportCount(paramMap);
                if (reportVoList == null || reportVoList.isEmpty()) {
                    Result.fail("题录科研成果及获奖查询没有数据");
                }
                setAwardAssemblyData(dataList, reportVoList);
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIVE:// 专利、新药证书、医药器械证书情况统计表

                List<SrpmsOutlineMedicalReportVo> medicalReportVoList = srpmsOutlineMedicalMapper.getReportCount(paramMap);
                List<SrpmsOutlinePatentReportVo> patentReportVoList = srpmsOutlinePatentMapper.getReportCount(paramMap);

                setPatentAndMedicalAssemblyData(dataList, medicalReportVoList, patentReportVoList);

                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SIX:// 发表学术论文、科技著作情况统计表
                // 查询报表统计数据
                List<SrpmsOutlineAcaExcReportVo> acaExcReportVoList = srpmsOutlineAcaExcMapper.getReportCount(paramMap);

                List<SrpmsOutlinePaperReportVo> paperReportVoList = srpmsOutlinePaperMapper.getReportCount(paramMap);

                List<SrpmsOutlineSatBookReportVo> bookReportVoList = srpmsOutlineSatBookMapper.getReportCount(paramMap);

                setAcaExcAndPaperAndBookAssemblyData(dataList, acaExcReportVoList, paperReportVoList, bookReportVoList);

                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN:// 科技项目执行情况统计表
                List<SrpmsOutlineNewTitleStateCountVo> reportStateVoList = srpmsOutlineMapper.getReportStateCount(paramMap);
                if (reportStateVoList == null || reportStateVoList.isEmpty()) {
                    Result.fail("题录新获项目查询执行情况没有数据");
                }
                List<Map<String, Object>> firstCountList = new ArrayList<>();
                SrpmsOutlineNewTitleStateCountVo reportVo;
                Map<String, Object> firstLongList;
                Result<DeptVo> deptVoResult;
                String orgName = "";
                for (Iterator i = reportStateVoList.iterator(); i.hasNext(); ) {
                    int index = 1;
                    reportVo = (SrpmsOutlineNewTitleStateCountVo) i.next();
                    firstLongList = new HashMap<>();
                    if (reportVo.getOrgId() != null) {
                        deptVoResult = deptFeignService.getByCode(reportVo.getOrgId().toString());
                        if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                            orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                        }
                    }
                    firstLongList.put(SrpmsConstant.DATA + index, orgName);
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getNewCount());// 新中标
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getResearchCount());// 在研
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getEndCount());// 结题
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getTerminationCount());// 终止
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getCancelCount());// 取消
                    index++;
                    firstLongList.put(SrpmsConstant.DATA + index, reportVo.getDelayCount());// 延期
                    firstCountList.add(firstLongList);
                }
                dataList.setFirstCountList(firstCountList);
                break;
            default:
                break;
        }

        // 组装数据返回前端

        return dataList;
    }

    /**
     * 导出excel报表service接口实现
     *
     * @param queryParam
     * @param type
     * @return
     */
    @Override
    public String getExcelExportReport(SrpmsOutlineQueryParam queryParam, int type) {

        Long orgId = queryParam.getOrgId();
        String filePath = "";
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件
        paramMap.put("orgId", orgId);
        paramMap.put("year", queryParam.getYear());
        paramMap.put("month", queryParam.getMonth());

        try {
            filePath = exportExcelPath(type);

            switch (type) {
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST:// 新获科技部项目情况统计表

                    // 查询报表统计数据
                    List<SrpmsOutlineNewTitleReportCountVo> voList = srpmsOutlineMapper.getReportCount(paramMap);

                    if (voList != null && voList.size() > 0) {
                        SrpmsOutlineNewTitleReportCountVo vo;
                        Map<Long, String> deptMap = new HashMap<>();
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlineNewTitleTemplate first = new SrpmsOutlineNewTitleTemplate();

                        for (Iterator e = voList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineNewTitleReportCountVo) e.next();
                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptMap.put(vo.getOrgId(), deptName);
                        }

                        first.writeExcel(filePath, type, queryParam, deptMap, voList, null, null);
                    }
                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND:// 新获科研项目经费情况统计表

                    List<SrpmsOutlineNewTitleReportOutlayVo> reportOutlayVoList = srpmsOutlineMapper.getReportTotalOutlay(paramMap);

                    if (reportOutlayVoList != null && reportOutlayVoList.size() > 0) {
                        SrpmsOutlineNewTitleReportOutlayVo vo;
                        Map<Long, String> deptMap = new HashMap<>();
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlineNewTitleTemplate second = new SrpmsOutlineNewTitleTemplate();

                        for (Iterator e = reportOutlayVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineNewTitleReportOutlayVo) e.next();

                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptMap.put(vo.getOrgId(), deptName);
                        }

                        second.writeExcel(filePath, type, queryParam, deptMap, null, reportOutlayVoList, null);
                    }

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE:// 到位科研项目经费情况统计表

                    List<SrpmsOutlineNewTitleReportOutlayVo> reportRecOutlayVoList = srpmsOutlineMapper.getReportReceiveOutlay(paramMap);

                    if (reportRecOutlayVoList != null && reportRecOutlayVoList.size() > 0) {
                        SrpmsOutlineNewTitleReportOutlayVo vo;
                        Map<Long, String> deptMap = new HashMap<>();
                        Result<DeptVo> deptVoResult;
                        String deptName = "";

                        SrpmsOutlineNewTitleTemplate three = new SrpmsOutlineNewTitleTemplate();

                        for (Iterator e = reportRecOutlayVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineNewTitleReportOutlayVo) e.next();

                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptMap.put(vo.getOrgId(), deptName);
                        }

                        three.writeExcel(filePath, type, queryParam, deptMap, null, reportRecOutlayVoList, null);
                    }

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FOUR:// 科研成果及获奖情况统计表

                    List<SrpmsOutlineAwardReportVo> reportVoList = srpmsOutlineAwardMapper.getReportCount(paramMap);
                    if (reportVoList != null && reportVoList.size() > 0) {
                        SrpmsOutlineAwardReportVo vo;
                        Map<Long, String> deptMap = new HashMap<>();
                        Result<DeptVo> deptVoResult;
                        String deptName = "";

                        SrpmsOutlineAwardTemplate four = new SrpmsOutlineAwardTemplate();

                        for (Iterator e = reportVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineAwardReportVo) e.next();

                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptMap.put(vo.getOrgId(), deptName);
                        }

                        four.writeExcel(filePath, queryParam, deptMap, reportVoList);
                    }


                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIVE:// 专利、新药证书、医药器械证书情况统计表

                    List<SrpmsOutlineMedicalReportVo> awardReportVoList = srpmsOutlineMedicalMapper.getReportCount(paramMap);
                    Map<Long, String> deptAwardMap = new HashMap<>();
                    if (awardReportVoList != null && awardReportVoList.size() > 0) {
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlineMedicalReportVo vo;
                        for (Iterator e = awardReportVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineMedicalReportVo) e.next();
                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptAwardMap.put(vo.getOrgId(), deptName);
                        }
                    }

                    List<SrpmsOutlinePatentReportVo> patentReportVoList = srpmsOutlinePatentMapper.getReportCount(paramMap);
                    Map<Long, String> deptPatentMap = new HashMap<>();
                    if (patentReportVoList != null && patentReportVoList.size() > 0) {
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlinePatentReportVo vo;

                        for (Iterator e = patentReportVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlinePatentReportVo) e.next();
                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptPatentMap.put(vo.getOrgId(), deptName);
                        }
                    }

                    SrpmsOutlinePatentAndMedicalTemplate five = new SrpmsOutlinePatentAndMedicalTemplate();

                    five.writeExcel(filePath, queryParam, patentReportVoList, awardReportVoList, deptPatentMap, deptAwardMap);
                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SIX:// 发表学术论文、科技著作情况统计表

                    // 查询报表统计数据
                    List<SrpmsOutlineAcaExcReportVo> acaExcReportVoList = srpmsOutlineAcaExcMapper.getReportCount(paramMap);

                    List<SrpmsOutlinePaperReportVo> paperReportVoList = srpmsOutlinePaperMapper.getReportCount(paramMap);

                    List<SrpmsOutlineSatBookReportVo> bookReportVoList = srpmsOutlineSatBookMapper.getReportCount(paramMap);

                    Map<Long, String> deptAcaMap = new HashMap<>();
                    if (acaExcReportVoList != null && acaExcReportVoList.size() > 0) {
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlineAcaExcReportVo vo;
                        for (Iterator e = acaExcReportVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineAcaExcReportVo) e.next();
                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptAcaMap.put(vo.getOrgId(), deptName);
                        }
                    }

                    Map<Long, String> deptPaperMap = new HashMap<>();
                    if (paperReportVoList != null && paperReportVoList.size() > 0) {
                        Result<DeptVo> deptVoResult;
                        String deptName = "";
                        SrpmsOutlinePaperReportVo vo;

                        for (Iterator e = paperReportVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlinePaperReportVo) e.next();
                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptPaperMap.put(vo.getOrgId(), deptName);
                        }
                    }

                    SrpmsOutlineAcaExcAndPaperTemplate six = new SrpmsOutlineAcaExcAndPaperTemplate();

                    six.writeExcel(filePath, queryParam, paperReportVoList, acaExcReportVoList, bookReportVoList, deptAcaMap, deptPaperMap);
                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN:// 科技项目执行情况统计表

                    List<SrpmsOutlineNewTitleStateCountVo> reportStateVoList = srpmsOutlineMapper.getReportStateCount(paramMap);

                    if (reportStateVoList != null && reportStateVoList.size() > 0) {
                        SrpmsOutlineNewTitleStateCountVo vo;
                        Map<Long, String> deptMap = new HashMap<>();
                        Result<DeptVo> deptVoResult;
                        String deptName = "";

                        SrpmsOutlineNewTitleTemplate seven = new SrpmsOutlineNewTitleTemplate();

                        for (Iterator e = reportStateVoList.iterator(); e.hasNext(); ) {
                            vo = (SrpmsOutlineNewTitleStateCountVo) e.next();

                            deptVoResult = deptFeignService.getByCode(vo.getOrgId().toString());
                            if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                                deptName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                            }
                            deptMap.put(vo.getOrgId(), deptName);
                        }

                        seven.writeExcel(filePath, type, queryParam, deptMap, null, null, reportStateVoList);
                    }

                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            CommonUtil.deleteFile(filePath);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR);
        }


        return filePath;
    }

    /**
     * 保存题录-新获项目service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineNewTitleFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();

        // 调用题录-新获项目删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-新获项目新增方法,返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {

            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-新获项目excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineNewTitleVoList srpmsOutlineVoList = new SrpmsOutlineNewTitleVoList();
        List<SrpmsOutlineNewTitleVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineNewTitleVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.PRO_ENTER_TYPE, SrpmsConstant.OUTLINE_PRO_STAT});
        Map<String, String> dictCategoryMap = sysDictService.getSysDictSelectValueByCode(SrpmsConstant.PRO_CATEGORY);
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 15) {
                vo = new SrpmsOutlineNewTitleVo();
                String proCategoryName = strings[5];
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }

                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setProCode(strings[3]);// 项目编号
                    vo.setProName(strings[4]);// 项目名称
                    String proCategory = "";
                    if (dictCategoryMap != null) {
                        proCategory = dictCategoryMap.get(proCategoryName);
                    }
                    if (dictMap != null) {
                        vo.setProEnterType(dictMap.get(SrpmsConstant.PRO_ENTER_TYPE.concat(strings[7] == null ? "" : CommonUtil.deleteStringSpace(strings[7]))));// 参与程度
                        vo.setProState(dictMap.get(SrpmsConstant.OUTLINE_PRO_STAT.concat(strings[11] == null ? "" : CommonUtil.deleteStringSpace(strings[11]))));// 项目状态
                    }

                    // 组装当前项目类别父级code
                    if (StringUtils.isNotBlank(proCategory)) {
                        int catLength = (proCategory.length() - 4) / 2 + 1;
                        String[] proCategoryArray = new String[catLength];
                        for (int i = 0; i < catLength; i++) {
                            proCategoryArray[i] = proCategory.substring(0, proCategory.length() - (catLength - i - 1) * 2);
                        }
                        vo.setProCategoryArray(proCategoryArray);
                    }
                    vo.setProCategory(proCategory);// 项目类型
                    vo.setProCategoryName(proCategoryName);// 项目类型名称
                    vo.setProSourceOrg(strings[6]);// 来源机构
                    vo.setProEnterTypeName(strings[7]);// 参与程度名称
                    vo.setProInCharge(strings[8]);// 项目负责人
                    if (CommonUtil.isValidDate(strings[9])) {
                        vo.setProStartDate(DateUtil.strToLocalDateTime(strings[9]));// 项目开始时间
                    }
                    if (CommonUtil.isValidDate(strings[10])) {
                        vo.setProEndDate(DateUtil.strToLocalDateTime(strings[10]));// 项目结束时间
                    }
                    vo.setProStateName(strings[11]);// 项目状态名称
                    vo.setProTotalOutlay(CommonUtil.getDoubleValue(strings[12]));// 项目总经费
                    vo.setProReceiveOutlay(CommonUtil.getDoubleValue(strings[13]));// 项目到位经费
                    vo.setRemarks(strings[14]);// 备注
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
     * 各单位经费占比service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public Result getReportProportionByOrg(SrpmsOutlineNewTitleQueryForm queryForm) {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件
        paramMap.put("year", queryForm.getYear());
        paramMap.put("month", queryForm.getMonth());
        List<SrpmsOutlineNewTitleOutlayProportionVo> dataList = srpmsOutlineMapper.getReportProportionByOrg(paramMap);
        List<SrpmsOutlineNewTitleOutlayProportionVo> dataSumList = srpmsOutlineMapper.getReportProportionSumByOrg(paramMap);
        if (dataList == null || dataList.isEmpty()) {
            return Result.fail("没有统计数据");
        }
        if (dataSumList == null || dataSumList.isEmpty()) {
            return Result.fail("没有统计数据");
        }

        SrpmsOutlineNewTitleOutlayProportionVo sumVo = dataSumList.get(0);

        Iterator iterator = dataList.iterator();
        SrpmsOutlineNewTitleOutlayProportionVo proportionVo;
        Map<String, Object> resultMap;
        while (iterator.hasNext()) {
            proportionVo = (SrpmsOutlineNewTitleOutlayProportionVo) iterator.next();
            resultMap = new HashMap<>();
            resultMap.put(proportionVo.getOrgId().toString(), proportionVo.getReceiveOutlay() / sumVo.getSumReceiveOutlay());

            dataMapList.add(resultMap);
        }

        return Result.success(dataMapList);
    }

    /**
     * 各项目经费占比service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public Result getReportProportionByProject(SrpmsOutlineNewTitleQueryForm queryForm) {
        List<Map<String, Map<String, Double>>> dataMapList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件
        paramMap.put("year", queryForm.getYear());
        paramMap.put("month", queryForm.getMonth());
        List<SrpmsOutlineNewTitleOutlayProportionVo> dataList = srpmsOutlineMapper.getReportProportionByProject(paramMap);
        if (dataList == null || dataList.isEmpty()) {
            return Result.fail("没有统计数据");
        }

        Iterator iterator = dataList.iterator();
        SrpmsOutlineNewTitleOutlayProportionVo proportionVo;
        Map<String, Map<String, Double>> resultMap;
        Map<String, Double> dataMap;
        while (iterator.hasNext()) {
            proportionVo = (SrpmsOutlineNewTitleOutlayProportionVo) iterator.next();
            resultMap = new HashMap<>();
            dataMap = new HashMap<>();
            dataMap.put("到位经费", proportionVo.getReceiveOutlay());
            dataMap.put("未到位经费", proportionVo.getTotalOutlay() - proportionVo.getReceiveOutlay());
            resultMap.put(proportionVo.getProName(), dataMap);

            dataMapList.add(resultMap);
        }

        return Result.success(dataMapList);
    }

    /**
     * 导出excel数据
     *
     * @param request
     * @param response
     * @param queryForm
     * @return
     */
    @Override
    public Result exportExcel(HttpServletRequest request, HttpServletResponse response, SrpmsOutlineNewTitleQueryForm queryForm) {
        try {

            List<SrpmsOutlineNewTitleVo> voList = getSrpmsOutlineQueryList(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_NEW_TITLE_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_NEW_TITLE_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_NEW_TITLE_FILE_HEADER;

            String[][] content = new String[voList.size()][];
            SrpmsOutlineNewTitleVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getProCode();
                content[i][4] = vo.getProName();
                content[i][5] = vo.getProCategoryName();
                content[i][6] = vo.getProSourceOrg();
                content[i][7] = vo.getProEnterTypeName();
                content[i][8] = vo.getProInCharge();
                content[i][9] = CommonUtil.objectToString(vo.getProStartDate()).split("T")[0];
                content[i][10] = CommonUtil.objectToString(vo.getProEndDate()).split("T")[0];
                content[i][11] = vo.getProStateName();
                content[i][12] = vo.getProTotalOutlay().toString();
                content[i][13] = vo.getProReceiveOutlay().toString();
                content[i][14] = vo.getRemarks();
            }

            Map<Integer, String[]> mapSelect = new HashMap<>();
            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.PRO_ENTER_TYPE, SrpmsConstant.OUTLINE_PRO_STAT});
            Map<String, List<String>> treeSelect = sysDictService.getSysDictSelectCodeByCode(SrpmsConstant.PRO_CATEGORY);
            String[] proCategoryArray = new String[treeSelect.get(SrpmsConstant.PRO_CATEGORY).size()];
            mapSelect.put(5, treeSelect.get(SrpmsConstant.PRO_CATEGORY).toArray(proCategoryArray));
            String[] proEnterTypeArray = new String[dictListMap.get(SrpmsConstant.PRO_ENTER_TYPE).size()];
            mapSelect.put(7, dictListMap.get(SrpmsConstant.PRO_ENTER_TYPE).toArray(proEnterTypeArray));
            String[] proStateArray = new String[dictListMap.get(SrpmsConstant.OUTLINE_PRO_STAT).size()];
            mapSelect.put(11, dictListMap.get(SrpmsConstant.OUTLINE_PRO_STAT).toArray(proStateArray));

            ExcelUtil.exportExcelCategory(fileName, sheetName, title, content, request, response, mapSelect);

        } catch (Exception e) {
            throw new BaseException(SrpmsErrorType.EXPORT_EXCEL_FAIL);
        }
        return Result.success();
    }

    /**
     * 查询单位下拉
     *
     * @param user
     * @param dept
     * @return
     */
    @Override
    public List<SrpmsDeptVo> listSelectDept(UserVo user, DeptVo dept) {
        List<SrpmsDeptVo> list = new ArrayList<>();
        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        SrpmsDeptVo srpmsDeptVo;
        // 判断是否是一级单位,一级单位查询所有的单位信息，下属单位就是本级单位
        if (SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode) && (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode))) {
            Result<List<DeptVo>> listResult = deptFeignService.list(new DeptQueryForm());
            if (listResult != null && listResult.getData() instanceof List && !listResult.getData().isEmpty()) {
                for (Iterator i = listResult.getData().iterator(); i.hasNext(); ) {
                    dept = (DeptVo)i.next();
                    srpmsDeptVo = new SrpmsDeptVo();
                    srpmsDeptVo.setId(dept.getDeptCode());
                    srpmsDeptVo.setLabel(dept.getDeptName());
                    list.add(srpmsDeptVo);
                }
            }
        } else {
            srpmsDeptVo = new SrpmsDeptVo();
            srpmsDeptVo.setId(deptCode);
            srpmsDeptVo.setLabel(dept.getDeptName());
            list.add(srpmsDeptVo);
        }
        return list;
    }

    /**
     * 导出查询service接口实现
     *
     * @param queryForm
     * @return
     */
    public List<SrpmsOutlineNewTitleVo> getSrpmsOutlineQueryList(SrpmsOutlineNewTitleQueryForm queryForm) {
        List<SrpmsOutlineNewTitleQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineNewTitleVo> srpmsOutlineVoList = new ArrayList<>();// 保存集合

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.PRO_CATEGORY, SrpmsConstant.PRO_ENTER_TYPE, SrpmsConstant.OUTLINE_PRO_STAT});
        SrpmsOutlineNewTitleVo srpmsOutlineVo;
        SrpmsOutlineNewTitleQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        Map<Long, Object> keyMap = new HashMap<>();
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineNewTitleVo();
            srpmsOutline = (SrpmsOutlineNewTitleQueryParam) iterator.next();
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

            // 组装当前项目类别父级code
            int catLength = (srpmsOutlineVo.getProCategory().length() - 4) / 2 + 1;
            String[] proCategoryArray = new String[catLength];
            for (int i = 0; i < catLength; i++) {
                proCategoryArray[i] = srpmsOutlineVo.getProCategory().substring(0, srpmsOutlineVo.getProCategory().length() - (catLength - i - 1) * 2);
            }
            srpmsOutlineVo.setProCategoryArray(proCategoryArray);
            if (dictMap != null) {
                if (StringUtils.isNotBlank(srpmsOutlineVo.getProCategory())) {
                    srpmsOutlineVo.setProCategoryName(dictMap.get(SrpmsConstant.PRO_CATEGORY.concat(srpmsOutlineVo.getProCategory())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getProEnterType())) {
                    srpmsOutlineVo.setProEnterTypeName(dictMap.get(SrpmsConstant.PRO_ENTER_TYPE.concat(srpmsOutlineVo.getProEnterType())));
                }
                if (StringUtils.isNotBlank(srpmsOutlineVo.getProState())) {
                    srpmsOutlineVo.setProStateName(dictMap.get(SrpmsConstant.OUTLINE_PRO_STAT.concat(srpmsOutlineVo.getProState())));
                }
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);

        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-新获项目操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineNewTitleForm> dataList) {
        List<String> ids = new ArrayList<>();
        // 循环组装id数据
        Iterator iterator = dataList.iterator();
        SrpmsOutlineNewTitleForm titleForm;
        while (iterator.hasNext()) {
            titleForm = (SrpmsOutlineNewTitleForm) iterator.next();
            ids.add(titleForm.getId());
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-新获项目操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(List<SrpmsOutlineNewTitleForm> dataList, SrpmsOutlineErrorList resultError, UserVo user) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineNewTitleForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineNewTitle srpmsOutline;
                SrpmsOutlineNewTitleForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineNewTitle> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineNewTitle> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineNewTitle();
                    titleForm = (SrpmsOutlineNewTitleForm) e.next();

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

                    // 拼接条件重复验证 单位+项目编号
                    key = new StringBuilder()
                            .append(titleForm.getOrgId())
                            .append(CommonUtil.deleteStringSpace(titleForm.getProCode())).toString();

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
                    srpmsOutline.setProCategory(titleForm.getProCategoryArray()[titleForm.getProCategoryArray().length - 1]);

                    // 根据条件查询题录基础信息表
                    SrpmsOutlineBase srpmsOutlineBase =
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_01, orgId, year, month);

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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_01);
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
     * 验证新获项目数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineNewTitleForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+项目编号)
        paramMap.put("id", titleForm.getId());
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("proCode", CommonUtil.deleteStringSpace(titleForm.getProCode()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineNewTitleForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 项目编码
        if (StringUtils.isBlank(titleForm.getProCode())) {
            checkFlag = true;
            errorMsg.append("项目编号为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProCode(), 50)) {
            checkFlag = true;
            errorMsg.append("项目编号超过设定长度,");
        }
        // 项目名称
        if (StringUtils.isBlank(titleForm.getProName())) {
            checkFlag = true;
            errorMsg.append("项目名称为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProName(), 200)) {
            checkFlag = true;
            errorMsg.append("项目名称超过设定长度,");
        }
        // 项目类别
        if (titleForm.getProCategoryArray() == null || titleForm.getProCategoryArray().length == 0) {
            checkFlag = true;
            errorMsg.append("项目类别为空,");
        }
        // 来源机构
        if (StringUtils.isBlank(titleForm.getProSourceOrg())) {
            checkFlag = true;
            errorMsg.append("来源机构为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProSourceOrg(), 50)) {
            checkFlag = true;
            errorMsg.append("来源机构超过设定长度,");
        }
        // 参与程度
        if (StringUtils.isBlank(titleForm.getProEnterType())) {
            checkFlag = true;
            errorMsg.append("参与程度为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProEnterType(), 2)) {
            checkFlag = true;
            errorMsg.append("参与程度超过设定长度,");
        }
        // 项目负责人
        if (StringUtils.isBlank(titleForm.getProInCharge())) {
            checkFlag = true;
            errorMsg.append("项目负责人为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProInCharge(), 50)) {
            checkFlag = true;
            errorMsg.append("项目负责人超过设定长度,");
        }

        // 项目开始时间
        if (titleForm.getProStartDate() == null) {
            checkFlag = true;
            errorMsg.append("项目开始时间为空,");
        }
        // 项目结束时间
        if (titleForm.getProEndDate() == null) {
            checkFlag = true;
            errorMsg.append("项目结束时间为空,");
        }
        // 项目状态
        if (StringUtils.isBlank(titleForm.getProState())) {
            checkFlag = true;
            errorMsg.append("项目状态为空,");
        } else if (CommonUtil.maxLengthCheck(titleForm.getProState(), 20)) {
            checkFlag = true;
            errorMsg.append("项目状态超过设定长度,");
        }
        // 项目总经费
        if (titleForm.getProTotalOutlay() == null) {
            checkFlag = true;
            errorMsg.append("项目总经费为空,");
        }
        // 项目到位经费
        if (titleForm.getProTotalOutlay() == null) {
            checkFlag = true;
            errorMsg.append("项目到位经费为空,");
        }
        return checkFlag;
    }


    /**
     * 新获项目数量统计组装数据
     *
     * @param dataList
     * @param voList
     * @return
     */
    public SrpmsOutlineReportList setNewTitleCountAssemblyData(SrpmsOutlineReportList dataList, List<SrpmsOutlineNewTitleReportCountVo> voList) {
        List<Map<String, Object>> firstCountList = new ArrayList<>();
        List<Map<String, Object>> secondCountList = new ArrayList<>();
        List<Map<String, Object>> threeCountList = new ArrayList<>();
        Map<String, Object> firstLongList;
        Map<String, Object> secondLongList;
        Map<String, Object> threeLongList;
        int totalCount;
        int indexCount;
        SrpmsOutlineNewTitleReportCountVo resultVo;
        Result<DeptVo> deptVoResult;
        String orgName = "";
        for (Iterator i = voList.iterator(); i.hasNext(); ) {
            int index = 1;
            resultVo = (SrpmsOutlineNewTitleReportCountVo) i.next();

            /**
             * 新获国家自然基金项目情况统计表
             */
            totalCount = 0;
            firstLongList = new HashMap<>();

            if (resultVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(resultVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            }

            firstLongList.put(SrpmsConstant.DATA + index, orgName);

            // 新药创制专项（项）-课题
            index++;
            indexCount = resultVo.getGxProjectCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 新药创制专项（项）-课题
            index++;
            indexCount = resultVo.getGxTopicCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 新药创制专项（项）-任务
            index++;
            indexCount = resultVo.getGxTaskCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 传染病专项（项）-项目
            index++;
            indexCount = resultVo.getGcProjectCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 传染病专项（项）-课题
            index++;
            indexCount = resultVo.getGcTopicCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 传染病专项（项）-任务
            index++;
            indexCount = resultVo.getGcTaskCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重点研发计划-项目
            index++;
            indexCount = resultVo.getGProjectCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重点研发计划-课题
            index++;
            indexCount = resultVo.getGTopicCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重点研发计划-任务
            index++;
            indexCount = resultVo.getGTaskCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 技术创新引导专项
            index++;
            indexCount = resultVo.getMakeCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 基地和人才专项
            index++;
            indexCount = resultVo.getSpecialCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 基础条件平台-牵头单位
            index++;
            indexCount = resultVo.getHeadCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 基础条件平台-参加单位
            index++;
            indexCount = resultVo.getJoinCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际合作项目
            index++;
            indexCount = resultVo.getCooperateProjectCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 其他计划项目
            index++;
            indexCount = resultVo.getOtherPlanCount();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, totalCount);
            firstCountList.add(firstLongList);

            /**
             * 新获国家自然基金项目情况统计表
             */
            totalCount = 0;
            secondLongList = new HashMap<>();
            index = 1;

            secondLongList.put(SrpmsConstant.DATA + index, orgName);

            // 面上项目
            index++;
            indexCount = resultVo.getOverallCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重点项目（项）-项目
            index++;
            indexCount = resultVo.getPointProjectCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重点项目（项）-课题
            index++;
            indexCount = resultVo.getPointTopicCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大项目-项目
            index++;
            indexCount = resultVo.getBigProjectCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大项目-课题
            index++;
            indexCount = resultVo.getBigTopicCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大研究计划项目-项目
            index++;
            indexCount = resultVo.getPlanProjectCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大研究计划项目-课题
            index++;
            indexCount = resultVo.getPlanTopicCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 青年科学基金项目
            index++;
            indexCount = resultVo.getProjectFirstCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 地区科学基金项目
            index++;
            indexCount = resultVo.getProjectSecoendCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 优秀青年科学基金项目
            index++;
            indexCount = resultVo.getProjectThreeCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家杰出青年科学基金项目
            index++;
            indexCount = resultVo.getProjectFourCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 创新群体项目
            index++;
            indexCount = resultVo.getProjectFiveCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际（地区）合作研究项目
            index++;
            indexCount = resultVo.getProjectSixCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 海外及港澳学者合作研究基金项目
            index++;
            indexCount = resultVo.getProjectSevenCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重大科研仪器研制项目
            index++;
            indexCount = resultVo.getProjectEightCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 联合基金项目
            index++;
            indexCount = resultVo.getProjectNineCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 应急管理项目
            index++;
            indexCount = resultVo.getProjectTenCount();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, totalCount);
            secondCountList.add(secondLongList);

            /**
             * 新获部委局（省、市）等地方项目情况统计表
             */
            totalCount = 0;
            index = 1;

            threeLongList = new HashMap<>();

            threeLongList.put(SrpmsConstant.DATA + index, orgName);
            // 行业专项
            index++;
            indexCount = resultVo.getGuildCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 其他项目
            index++;
            indexCount = resultVo.getOtherCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 教育部（项）-项目
            index++;
            indexCount = resultVo.getMoeProjectCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 教育部（项）-创新团队
            index++;
            indexCount = resultVo.getMoeMakeCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家发改委项目
            index++;
            indexCount = resultVo.getNdrcCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家药监局项目
            index++;
            indexCount = resultVo.getSfdaCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家中医药局项目
            index++;
            indexCount = resultVo.getSatcmCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 其他部委项目
            index++;
            indexCount = resultVo.getOtherMacCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 地方项目
            index++;
            indexCount = resultVo.getPlaceCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际合作项目
            index++;
            indexCount = resultVo.getNatunalCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 与企业联合项目
            index++;
            indexCount = resultVo.getEnterCooCount();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, totalCount);
            threeCountList.add(threeLongList);


        }

        dataList.setFirstCountList(firstCountList);
        dataList.setSecondCountList(secondCountList);
        dataList.setThreeCountList(threeCountList);

        return dataList;
    }

    /**
     * 新获项目经费统计组装数据
     *
     * @param dataList
     * @param voList
     * @return
     */
    public SrpmsOutlineReportList setNewTitleOutlayAssemblyData(
            SrpmsOutlineReportList dataList,
            List<SrpmsOutlineNewTitleReportOutlayVo> voList,
            int type
    ) {
        List<Map<String, Object>> firstCountList = new ArrayList<>();
        List<Map<String, Object>> secondCountList = new ArrayList<>();
        List<Map<String, Object>> threeCountList = new ArrayList<>();
        Map<String, Object> firstLongList;
        Map<String, Object> secondLongList;
        Map<String, Object> threeLongList;
        int totalCount;
        int indexCount;
        SrpmsOutlineNewTitleReportOutlayVo resultVo;
        Result<DeptVo> deptVoResult;
        String orgName = "";
        for (Iterator i = voList.iterator(); i.hasNext(); ) {
            int index = 1;
            resultVo = (SrpmsOutlineNewTitleReportOutlayVo) i.next();

            /**
             * 国家自然基金项目经费（万元）
             */
            totalCount = 0;
            firstLongList = new HashMap<>();

            if (resultVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(resultVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            }

            firstLongList.put(SrpmsConstant.DATA + index, orgName);

            // 面上项目
            index++;
            indexCount = resultVo.getOverallOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重点项目（项)
            index++;
            indexCount = resultVo.getPointProjectOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大项目
            index++;
            indexCount = resultVo.getBigProjectOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 重大研究计划项目
            index++;
            indexCount = resultVo.getPlanProjectOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 青年科学基金项目
            index++;
            indexCount = resultVo.getProjectFirstOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 地区科学基金项目
            index++;
            indexCount = resultVo.getProjectSecoendOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 优秀青年科学基金项目
            index++;
            indexCount = resultVo.getProjectThreeOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家杰出青年科学基金项目
            index++;
            indexCount = resultVo.getProjectFourOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 创新群体项目
            index++;
            indexCount = resultVo.getProjectFiveOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际（地区）合作研究项目
            index++;
            indexCount = resultVo.getProjectSixOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 海外及港澳学者合作研究基金项目
            index++;
            indexCount = resultVo.getProjectSevenOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重大科研仪器研制项目
            index++;
            indexCount = resultVo.getProjectEightOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 联合基金项目
            index++;
            indexCount = resultVo.getProjectNineOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 应急管理项目
            index++;
            indexCount = resultVo.getProjectTenOutlay();
            totalCount += indexCount;
            firstLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, totalCount);
            firstCountList.add(firstLongList);

            /**
             * 科技部项目经费（万元）
             */
            totalCount = 0;
            index = 1;
            secondLongList = new HashMap<>();

            secondLongList.put(SrpmsConstant.DATA + index, orgName);
            // 国家科技重大专项
            index++;
            indexCount = resultVo.getGxProjectOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家重点研发计划
            index++;
            indexCount = resultVo.getGProjectOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 技术创新引导专项
            index++;
            indexCount = resultVo.getMakeOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 基地和人才专项
            index++;
            indexCount = resultVo.getSpecialOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            if (SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE == type) {
                // 重大科学计划
                index++;
                indexCount = resultVo.getBigTechOutlay();
                totalCount += indexCount;
                secondLongList.put(SrpmsConstant.DATA + index, indexCount);

                // 科技支撑计划
                index++;
                indexCount = resultVo.getTechBraceOutlay();
                totalCount += indexCount;
                secondLongList.put(SrpmsConstant.DATA + index, indexCount);

                // 973计划
                index++;
                indexCount = resultVo.getPlanFirstOutlay();
                totalCount += indexCount;
                secondLongList.put(SrpmsConstant.DATA + index, indexCount);

                // 863计划
                index++;
                indexCount = resultVo.getPlanSecoendOutlay();
                totalCount += indexCount;
                secondLongList.put(SrpmsConstant.DATA + index, indexCount);
            }


            // 基础条件平台
            index++;
            indexCount = resultVo.getHeadOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际合作项目
            index++;
            indexCount = resultVo.getCooperateProjectOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 其他计划项目
            index++;
            indexCount = resultVo.getOtherPlanOutlay();
            totalCount += indexCount;
            secondLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, totalCount);
            secondCountList.add(secondLongList);

            /**
             * 部委局（省、市）等地方项目经费（万元）
             */
            totalCount = 0;
            index = 1;

            threeLongList = new HashMap<>();

            threeLongList.put(SrpmsConstant.DATA + index, orgName);
            // 国家卫生健康委
            index++;
            indexCount = resultVo.getGuildOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 教育部（项）
            index++;
            indexCount = resultVo.getMoeProjectOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家发改委项目
            index++;
            indexCount = resultVo.getNdrcOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家药监局项目
            index++;
            indexCount = resultVo.getSfdaOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国家中医药局项目
            index++;
            indexCount = resultVo.getSatcmOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 其他部委项目
            index++;
            indexCount = resultVo.getOtherMacOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 地方项目
            index++;
            indexCount = resultVo.getPlaceOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 国际合作项目
            index++;
            indexCount = resultVo.getNatunalOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 与企业联合项目
            index++;
            indexCount = resultVo.getEnterCooOutlay();
            totalCount += indexCount;
            threeLongList.put(SrpmsConstant.DATA + index, indexCount);

            // 合计
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, totalCount);
            threeCountList.add(threeLongList);

        }

        dataList.setFirstCountList(firstCountList);
        dataList.setSecondCountList(secondCountList);
        dataList.setThreeCountList(threeCountList);

        return dataList;
    }

    /**
     * 科研成果及获奖情况统计表设置数据
     *
     * @param dataList
     * @param voList
     * @return
     */
    public SrpmsOutlineReportList setAwardAssemblyData(SrpmsOutlineReportList dataList, List<SrpmsOutlineAwardReportVo> voList) {
        List<Map<String, Object>> firstCountList = new ArrayList<>();
        List<Map<String, Object>> secondCountList = new ArrayList<>();
        List<Map<String, Object>> threeCountList = new ArrayList<>();
        List<Map<String, Object>> fourCountList = new ArrayList<>();
        Map<String, Object> firstLongList;
        Map<String, Object> secondLongList;
        Map<String, Object> threeLongList;
        Map<String, Object> fourLongList;
        SrpmsOutlineAwardReportVo resultVo;
        Result<DeptVo> deptVoResult;
        String orgName = "";
        for (Iterator i = voList.iterator(); i.hasNext(); ) {
            int index = 1;
            resultVo = (SrpmsOutlineAwardReportVo) i.next();

            if (resultVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(resultVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            }

            /**
             * 国家级科技奖励
             */
            firstLongList = new HashMap<>();

            firstLongList.put(SrpmsConstant.DATA + index, orgName);
            // 最高奖
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCBestCount());
            // 国际合作奖
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCCooperateCount());
            // 自然科学类一等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCNaturalFirstCount());
            // 自然科学类二等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCNaturalSecoendCount());
            // 科技进步奖一等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCImproveFirstCount());
            // 科技进步奖二等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCImproveSecoendCount());
            // 发明奖一等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCInventFirstCount());
            // 发明奖二等
            index++;
            firstLongList.put(SrpmsConstant.DATA + index, resultVo.getCInventSecoendCount());
            firstCountList.add(firstLongList);

            /**
             * 省部级
             */
            secondLongList = new HashMap<>();
            index = 1;

            secondLongList.put(SrpmsConstant.DATA + index, orgName);
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSepcialCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSNaturalFirstCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSNaturalSecoendCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSNaturalThreeCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSImproveFirstCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSImproveSecoendCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSImproveThreeCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSInventFirstCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSInventSecoendCount());
            index++;
            secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSInventThreeCount());
            secondCountList.add(secondLongList);

            /**
             * 高校科技奖励
             */
            threeLongList = new HashMap<>();
            index = 1;

            threeLongList.put(SrpmsConstant.DATA + index, orgName);
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGNaturalFirstCount());
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGNaturalSecoendCount());
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGImproveFirstCount());
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGImproveSecoendCount());
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGInventFirstCount());
            index++;
            threeLongList.put(SrpmsConstant.DATA + index, resultVo.getGInventSecoendCount());
            threeCountList.add(threeLongList);

            /**
             * 中华医学科技奖/其他社会奖
             */
            fourLongList = new HashMap<>();

            index = 1;

            fourLongList.put(SrpmsConstant.DATA + index, orgName);

            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getZFirstCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getZSecoendCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getZThreeCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getOFirstCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getOSecoendCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, resultVo.getOThreeCount());
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, 0);
            index++;
            fourLongList.put(SrpmsConstant.DATA + index, 0);
            fourCountList.add(fourLongList);

        }

        dataList.setFirstCountList(firstCountList);
        dataList.setSecondCountList(secondCountList);
        dataList.setThreeCountList(threeCountList);
        dataList.setFourCountList(fourCountList);

        return dataList;
    }


    /**
     * 专利、新药证书、医药器械证书情况统计表设置数据
     *
     * @param dataList
     * @param voList
     * @return
     */
    public SrpmsOutlineReportList setPatentAndMedicalAssemblyData(
            SrpmsOutlineReportList dataList,
            List<SrpmsOutlineMedicalReportVo> voList,
            List<SrpmsOutlinePatentReportVo> patentReportVoList
    ) {
        List<Map<String, Object>> firstCountList = new ArrayList<>();
        List<Map<String, Object>> secondCountList = new ArrayList<>();
        List<Map<String, Object>> threeCountList = new ArrayList<>();
        Map<String, Object> firstLongList;
        Map<String, Object> secondLongList;
        Map<String, Object> threeLongList;

        /**
         * 专利统计
         */
        if (patentReportVoList != null && patentReportVoList.size() > 0) {
            SrpmsOutlinePatentReportVo patentReportVo;
            Result<DeptVo> deptVoResult;
            String orgName = "";
            for (Iterator e = patentReportVoList.iterator(); e.hasNext(); ) {
                int index = 1;
                patentReportVo = (SrpmsOutlinePatentReportVo) e.next();
                firstLongList = new HashMap<>();
                if (patentReportVo.getOrgId() != null) {
                    deptVoResult = deptFeignService.getByCode(patentReportVo.getOrgId().toString());
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                    }
                }
                firstLongList.put(SrpmsConstant.DATA + index, orgName);
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, patentReportVo.getTotalCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, patentReportVo.getInNoAuthCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, patentReportVo.getInAuthCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, patentReportVo.getOutNoAuthCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, patentReportVo.getOutAuthCount());
                firstCountList.add(firstLongList);
            }
        }

        if (voList != null && voList.size() > 0) {
            SrpmsOutlineMedicalReportVo resultVo;
            Result<DeptVo> deptVoResult;
            String orgName = "";
            for (Iterator e = voList.iterator(); e.hasNext(); ) {
                int index = 1;
                resultVo = (SrpmsOutlineMedicalReportVo) e.next();
                /**
                 * 新药证书统计
                 */
                secondLongList = new HashMap<>();

                if (resultVo.getOrgId() != null) {
                    deptVoResult = deptFeignService.getByCode(resultVo.getOrgId().toString());
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                    }
                }
                secondLongList.put(SrpmsConstant.DATA + index, orgName);
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getTotalNewCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getWFirstCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getWSecondCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getWThreeCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getWFourCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getZhFirstCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getZhSecondCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getZhThreeCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getZhFourCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getZhFiveCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSFirstCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSSecondCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSThreeCount());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getSFourCount());

                secondCountList.add(secondLongList);

                /**
                 * 医疗器械
                 */
                threeLongList = new HashMap<>();

                index = 1;
                threeLongList.put(SrpmsConstant.DATA + index, orgName);
                index++;
                threeLongList.put(SrpmsConstant.DATA + index, resultVo.getTotalMedicalCount());
                index++;
                threeLongList.put(SrpmsConstant.DATA + index, resultVo.getQFirstCount());
                index++;
                threeLongList.put(SrpmsConstant.DATA + index, resultVo.getQSecondCount());
                index++;
                threeLongList.put(SrpmsConstant.DATA + index, resultVo.getQThreeCount());
                index++;
                threeLongList.put(SrpmsConstant.DATA + index, resultVo.getQFourCount());
                threeCountList.add(threeLongList);
            }

        }

        dataList.setFirstCountList(firstCountList);
        dataList.setSecondCountList(secondCountList);
        dataList.setThreeCountList(threeCountList);

        return dataList;
    }


    /**
     * 发表学术论文、科技著作情况统计表
     *
     * @param dataList
     * @param acaExcReportVoList
     * @param paperReportVoList
     * @param bookReportVoList
     * @return
     */
    public SrpmsOutlineReportList setAcaExcAndPaperAndBookAssemblyData(
            SrpmsOutlineReportList dataList,
            List<SrpmsOutlineAcaExcReportVo> acaExcReportVoList,
            List<SrpmsOutlinePaperReportVo> paperReportVoList,
            List<SrpmsOutlineSatBookReportVo> bookReportVoList
    ) {
        List<Map<String, Object>> firstCountList = new ArrayList<>();
        List<Map<String, Object>> secondCountList = new ArrayList<>();
        Map<String, Object> firstLongList;
        Map<String, Object> secondLongList;

        /**
         * 论文科技著作统计
         */

        Map<Long, String> orgMap = new HashMap<>();
        if (bookReportVoList != null && bookReportVoList.size() > 0) {
            SrpmsOutlineSatBookReportVo bookReportVo;
            StringBuilder stringBuilder = new StringBuilder();
            for (Iterator e = bookReportVoList.iterator(); e.hasNext(); ) {
                bookReportVo = (SrpmsOutlineSatBookReportVo) e.next();
                stringBuilder.append(bookReportVo.getChiefEditorCount());
                stringBuilder.append("-");
                stringBuilder.append(bookReportVo.getJoinBookCount());
                orgMap.put(bookReportVo.getOrgId(), stringBuilder.toString());
            }
        }
        if (paperReportVoList != null && paperReportVoList.size() > 0) {
            SrpmsOutlinePaperReportVo reportVo;
            int chiefEditorCount = 0;
            int joinBookCount = 0;
            String[] bookValueArray;
            Result<DeptVo> deptVoResult;
            String orgName = "";
            for (Iterator e = paperReportVoList.iterator(); e.hasNext(); ) {
                int index = 1;
                reportVo = (SrpmsOutlinePaperReportVo) e.next();
                if (orgMap != null && orgMap.containsKey(reportVo.getOrgId())) {
                    bookValueArray = orgMap.get(reportVo.getOrgId()).split("-");
                    chiefEditorCount = Integer.valueOf(bookValueArray[0]);
                    joinBookCount = Integer.valueOf(bookValueArray[1]);
                }
                firstLongList = new HashMap<>();
                if (reportVo.getOrgId() != null) {
                    deptVoResult = deptFeignService.getByCode(reportVo.getOrgId().toString());
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                    }
                }
                firstLongList.put(SrpmsConstant.DATA + index, orgName);
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutJournalCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getInJournalNationCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getInJournalCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, chiefEditorCount);
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, joinBookCount);
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutSciFirstCount() +
                        reportVo.getOutSciSecoendCount() +
                        reportVo.getOutSciThreeCount() +
                        reportVo.getOutSciFourCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutSciFirstCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutSciSecoendCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutSciThreeCount());
                index++;
                firstLongList.put(SrpmsConstant.DATA + index, reportVo.getOutSciFourCount());
                firstCountList.add(firstLongList);
            }
        }

        if (acaExcReportVoList != null && acaExcReportVoList.size() > 0) {
            SrpmsOutlineAcaExcReportVo resultVo;
            Result<DeptVo> deptVoResult;
            String orgName = "";
            for (Iterator e = acaExcReportVoList.iterator(); e.hasNext(); ) {
                int index = 1;
                resultVo = (SrpmsOutlineAcaExcReportVo) e.next();
                secondLongList = new HashMap<>();

                if (resultVo.getOrgId() != null) {
                    deptVoResult = deptFeignService.getByCode(resultVo.getOrgId().toString());
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                    }
                }
                secondLongList.put(SrpmsConstant.DATA + index, orgName);
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getOutTeamHost());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getOutTeamJoin());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getInTeamHost());
                index++;
                secondLongList.put(SrpmsConstant.DATA + index, resultVo.getInTeamJoin());
                secondCountList.add(secondLongList);

            }

        }

        dataList.setFirstCountList(firstCountList);
        dataList.setSecondCountList(secondCountList);

        return dataList;
    }

    /**
     * 复制模板文件,返回文件路径
     *
     * @param type
     * @return
     */
    public String exportExcelPath(int type) {
        //模板的路径
        StringBuilder templateFilePath = new StringBuilder();
        templateFilePath.append("excel/template/");

        StringBuilder newFilePath = new StringBuilder();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        //新的文件路径
        File jarPath = null;
        try {
            jarPath = new File(ResourceUtils.getURL("").getPath()).getAbsoluteFile();
            newFilePath.append(jarPath);
            newFilePath.append(SrpmsConstant.SEPARATOR);
            newFilePath.append(SrpmsConstant.EXCEL);
            newFilePath.append(SrpmsConstant.SEPARATOR);

            File file = new File(newFilePath.toString());
            // 创建目录
            if (!file.exists()) {
                file.mkdirs();
            }

            switch (type) {
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST:// 新获科技部项目情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_NEW_TITLE_COUNT);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND:// 新获科研项目经费情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_NEW_TITLE_TOTAL_OUTLAY);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE:// 到位科研项目到位经费情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_NEW_TITLE_REC_OUTLAY);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FOUR:// 科研成果及获奖情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_AWARD_COUNT);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIVE:// 专利、新药证书、医药器械证书情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_PATENT_MEDICAL_COUNT);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SIX:// 发表学术论文、科技著作情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_PAPER_BOOK_COUNT);

                    break;
                case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN:// 科技项目执行情况统计表
                    templateFilePath.append(SrpmsConstant.TEMPLATE_NEW_TITLE_STATE);
                    break;
                default:
                    break;
            }

            templateFilePath.append(SrpmsConstant.EXT_EXCEL_XLSX);
            inputStream = this.getClass().getClassLoader().getResourceAsStream(templateFilePath.toString());


            if (inputStream != null) {
                newFilePath.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
                newFilePath.append(RandomUtils.nextInt(0, 9999));
                newFilePath.append(SrpmsConstant.EXT_EXCEL_XLSX);

                outputStream = new FileOutputStream(newFilePath.toString());

                // 实现文件的复制
                byte[] b = new byte[1024];
                int len;
                while ((len = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, len);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }
        return newFilePath.toString();
    }

}


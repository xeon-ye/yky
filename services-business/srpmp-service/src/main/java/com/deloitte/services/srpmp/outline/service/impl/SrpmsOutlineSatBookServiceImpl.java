package com.deloitte.services.srpmp.outline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineSatBookQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineSatBookQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.exception.SrpmsErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineBase;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineSatBook;
import com.deloitte.services.srpmp.outline.mapper.SrpmsOutlineSatBookMapper;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineBaseService;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineSatBookService;
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
 * @Description :  SrpmsOutlineSatBook服务实现类
 * @Modified :
 */
@Service
@Slf4j
@Transactional
public class SrpmsOutlineSatBookServiceImpl extends ServiceImpl<SrpmsOutlineSatBookMapper, SrpmsOutlineSatBook> implements ISrpmsOutlineSatBookService {


    @Autowired
    private SrpmsOutlineSatBookMapper srpmsOutlineMapper;

    @Autowired
    private ISrpmsOutlineBaseService srpmsOutlineBaseService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private DeptFeignService deptFeignService;

    /**
     * 查询科技著作service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public SrpmsOutlineSatBookVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineSatBookQueryForm queryForm) {
        SrpmsOutlineSatBookVoList dataVoList = new SrpmsOutlineSatBookVoList();

        // 执行查询
        List<SrpmsOutlineSatBookVo> srpmsOutlineVoList = exportExcelQuery(queryForm);

        dataVoList.setSrpmsOutlineVoList(srpmsOutlineVoList);
        return dataVoList;
    }

    /**
     * 保存题录-科技著作service接口实现
     *
     * @param data
     * @return
     */
    @Override
    public SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineSatBookFormList data, UserVo user) {

        SrpmsOutlineErrorList listError = new SrpmsOutlineErrorList();// 返回错误返回界面集合

        // 调用题录-科技著作删除方法
        if (data.getDeleteOutlineList() != null && data.getDeleteOutlineList().size() > 0) {
            deleteSrpmsOutline(data.getDeleteOutlineList());
        }

        // 调用题录-科技著作新增方法，返回错误数据
        if (data.getAddOutlineList() != null && data.getAddOutlineList().size() > 0) {
            return saveSrpmsOutline(data.getAddOutlineList(), listError, user);
        }
        return listError;
    }

    /**
     * 读取题录-科技著作excel文件service接口实现
     *
     * @param file
     * @return
     */
    @Override
    public Result readExeclFile(MultipartFile file) throws Exception {
        SrpmsOutlineSatBookVoList srpmsOutlineVoList = new SrpmsOutlineSatBookVoList();
        List<SrpmsOutlineSatBookVo> voList = new ArrayList<>();
        List<String[]> list = ExcelUtil.readExcel(file);
        if (list.isEmpty()) {
            return Result.fail("导入excel未读取到数据");
        }
        Iterator iterator = list.iterator();
        SrpmsOutlineSatBookVo vo;
        Result<DeptVo> deptVoResult;
        long orgCode = 0L;
        Map<String, String> dictMap = sysDictService.getSysDictCodeByValueMap(new String[]{SrpmsConstant.BOOK_LEVEL});
        while (iterator.hasNext()) {
            String[] strings = (String[]) iterator.next();
            if (strings.length == 11) {
                vo = new SrpmsOutlineSatBookVo();
                if (StringUtils.isNotBlank(strings[0])) {
                    deptVoResult = deptFeignService.getDeptByName(strings[0]);
                    if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                        orgCode = CommonUtil.getLongValue(deptVoResult.getData().getDeptCode());
                    }
                    if (dictMap != null) {
                        vo.setBookLevel(dictMap.get(SrpmsConstant.BOOK_LEVEL.concat(strings[6] == null ? "" : CommonUtil.deleteStringSpace(strings[6]))));// 主编/参编
                    }
                    vo.setOrgId(orgCode);
                    vo.setOrgName(strings[0]);// 单位名称
                    vo.setYear(strings[1]);// 年
                    vo.setMonth(strings[2]);// 月
                    vo.setBookName(strings[3]);// 著作名
                    vo.setAuthor(strings[4]);// 作者
                    vo.setBookOrg(strings[5]);// 作者单位
                    vo.setBookLevelName(strings[6]);// 主编/参编名称
                    vo.setPress(strings[7]);// 出版社
                    if (CommonUtil.isValidDate(strings[8])) {
                        vo.setPressTime(DateUtil.strToLocalDateTime(strings[8]));// 出版时间
                    }
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
     * 科技著作导出数据service接口实现
     *
     * @param queryForm
     * @return
     */
    @Override
    public List<SrpmsOutlineSatBookVo> exportExcelQuery(SrpmsOutlineSatBookQueryForm queryForm) {
        if (StringUtils.isNotBlank(queryForm.getPressTime())) {
            queryForm.setPressTime(DateUtil.strDateToStr(queryForm.getPressTime()));
        }
        List<SrpmsOutlineSatBookQueryParam> list = srpmsOutlineMapper.listQuery(queryForm);

        List<SrpmsOutlineSatBookVo> srpmsOutlineVoList = new ArrayList<>();

        Map<String, String> dictMap = sysDictService.getSysDictMap(new String[]{SrpmsConstant.BOOK_LEVEL});

        SrpmsOutlineSatBookVo srpmsOutlineVo;
        SrpmsOutlineSatBookQueryParam srpmsOutline;
        Iterator iterator = list.iterator();
        Result<DeptVo> deptVoResult;
        String orgName = "";
        while (iterator.hasNext()) {
            srpmsOutlineVo = new SrpmsOutlineSatBookVo();
            srpmsOutline = (SrpmsOutlineSatBookQueryParam) iterator.next();
            BeanUtils.copyProperties(srpmsOutline, srpmsOutlineVo);
            // 查询单位名称
            if (srpmsOutlineVo.getOrgId() != null) {
                deptVoResult = deptFeignService.getByCode(srpmsOutlineVo.getOrgId().toString());
                if (deptVoResult.getData() instanceof DeptVo && deptVoResult.getData() != null) {
                    orgName = CommonUtil.objectToString(deptVoResult.getData().getDeptName());
                }
            }

            srpmsOutlineVo.setOrgName(orgName);
            if (dictMap != null && StringUtils.isNotBlank(srpmsOutlineVo.getBookLevel())) {
                srpmsOutlineVo.setBookLevelName(dictMap.get(SrpmsConstant.BOOK_LEVEL.concat(srpmsOutlineVo.getBookLevel())));
            }
            srpmsOutlineVoList.add(srpmsOutlineVo);
        }
        return srpmsOutlineVoList;
    }

    /**
     * 执行批量删除题录-科技著作操作
     *
     * @param dataList
     */
    public boolean deleteSrpmsOutline(List<SrpmsOutlineSatBookForm> dataList) {
        List<String> ids = new ArrayList<>();
        if (dataList != null && !dataList.isEmpty()) {
            // 循环组装id数据
            Iterator iterator = dataList.iterator();
            SrpmsOutlineSatBookForm titleForm;
            while (iterator.hasNext()) {
                titleForm = (SrpmsOutlineSatBookForm) iterator.next();
                ids.add(titleForm.getId());
            }
        }
        // 执行删除操作
        return this.removeByIds(ids);
    }

    /**
     * 执行保存题录-科技著作操作
     *
     * @param dataList
     * @param resultError
     * @return
     */
    public SrpmsOutlineErrorList saveSrpmsOutline(List<SrpmsOutlineSatBookForm> dataList, SrpmsOutlineErrorList resultError, UserVo user) {
        if (dataList != null && !dataList.isEmpty()) {
            try {

                List<SrpmsOutlineSatBookForm> listError = new ArrayList<>();// 返回错误返回界面集合

                SrpmsOutlineSatBook srpmsOutline;
                SrpmsOutlineSatBookForm titleForm;
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBefore = new StringBuilder();
                Map<String, Integer> keyMap = new HashMap<>();
                String key;
                boolean checkFlag;
                int indexRow = 0;
                List<SrpmsOutlineSatBook> listAddSuccess = new ArrayList<>();// 验证通过保存集合
                List<SrpmsOutlineSatBook> listEditSuccess = new ArrayList<>();// 验证通过更新集合

                for (Iterator e = dataList.iterator(); e.hasNext();) {
                    srpmsOutline = new SrpmsOutlineSatBook();
                    titleForm = (SrpmsOutlineSatBookForm) e.next();

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

                    // 拼接条件重复验证 单位+著作名+出版社
                    key = new StringBuilder()
                            .append(orgId)
//                        .append(year)
//                        .append(month)
                            .append(CommonUtil.deleteStringSpace(titleForm.getBookName()))
                            .append(CommonUtil.deleteStringSpace(titleForm.getPress())).toString();

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
                    // 验证数据是否存在，存在数据返回错误数据，不存在执行新增操作
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
                            srpmsOutlineBaseService.getSrpmsOutlineBase(SrpmsConstant.SRPMS_OUTLINE_TYPE_09, orgId, year, month);
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
                        srpmsOutlineBase.setType(SrpmsConstant.SRPMS_OUTLINE_TYPE_09);
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
                throw new BaseException(PlatformErrorType.NO_DATA_FOUND);
            }
        }
        return resultError;
    }

    /**
     * 验证科技著作数据是否存在
     *
     * @param titleForm
     * @return
     */
    public boolean getSrpmsOutlineExists(SrpmsOutlineSatBookForm titleForm) {
        Map<String, Object> paramMap = new HashMap<>();
        // 设置查询条件(单位+著作名+出版社)
        paramMap.put("orgId", titleForm.getOrgId());
        paramMap.put("id", titleForm.getId());
//        paramMap.put("year", year);
//        paramMap.put("month", month);
        paramMap.put("bookName", CommonUtil.deleteStringSpace(titleForm.getBookName()));
        paramMap.put("press", CommonUtil.deleteStringSpace(titleForm.getPress()));

        return srpmsOutlineMapper.getQueryCount(paramMap) == 0 ? false : true;
    }

    /**
     * 保存的时候check字段
     *
     * @param titleForm
     * @return
     */
    public boolean checkSrpmsOutlineFields(SrpmsOutlineSatBookForm titleForm, boolean checkFlag, StringBuilder errorMsg) {
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
        // 著作名
        if (StringUtils.isBlank(titleForm.getBookName())) {
            checkFlag = true;
            errorMsg.append("著作名为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getBookName(), 200)) {
            checkFlag = true;
            errorMsg.append("著作名超过设定长度，");
        }
        // 作者
        if (StringUtils.isBlank(titleForm.getAuthor())) {
            checkFlag = true;
            errorMsg.append("作者为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getAuthor(), 200)) {
            checkFlag = true;
            errorMsg.append("作者超过设定长度，");
        }
        // 作者单位
        if (StringUtils.isBlank(titleForm.getBookOrg())) {
            checkFlag = true;
            errorMsg.append("作者单位为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getBookOrg(), 200)) {
            checkFlag = true;
            errorMsg.append("作者单位超过设定长度，");
        }
        // 出版社
        if (StringUtils.isBlank(titleForm.getPress())) {
            checkFlag = true;
            errorMsg.append("出版社为空，");
        } else if (CommonUtil.maxLengthCheck(titleForm.getPress(), 50)) {
            checkFlag = true;
            errorMsg.append("出版社超过设定长度，");
        }
        // 出版时间
        if (titleForm.getPressTime() == null) {
            checkFlag = true;
            errorMsg.append("出版时间为空，");
        }
        return checkFlag;
    }

}

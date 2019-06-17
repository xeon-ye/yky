package com.deloitte.services.fssc.system.attchdef.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.attchdef.param.BaseFileDefQueryForm;
import com.deloitte.platform.api.fssc.attchdef.vo.BaseFileDefForm;
import com.deloitte.platform.api.fssc.attchdef.vo.BaseFileDefLineForm;
import com.deloitte.platform.api.fssc.attchdef.vo.BaseFileDefLineVo;
import com.deloitte.platform.api.fssc.attchdef.vo.BaseFileDefVo;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDef;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDefLine;
import com.deloitte.services.fssc.system.attchdef.service.IBaseFileDefLineService;
import com.deloitte.services.fssc.system.attchdef.service.IBaseFileDefService;
import com.deloitte.services.fssc.system.file.entity.File;
import com.deloitte.services.fssc.system.file.service.IFileService;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.FsscHttpUtils;
import com.deloitte.services.fssc.util.StringUtil;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description :   BaseFileDef控制器实现类
 * @Modified :
 */
@Api(tags = "文件类型定义操作接口")
@Slf4j
@RestController
@RequestMapping(value = "/attchdef/base-file-def")
public class BaseFileDefController {

    @Autowired
    private IBaseFileDefService baseFileDefService;

    @Autowired
    private IBaseFileDefLineService baseFileDefLineService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ProcessMapper processMapper;
    /**
     * POST---新增
     *
     * @param baseFileDefForm
     * @return
     */
    @PostMapping(value = "addOrUpdate")
    @ApiOperation(value = "新增BaseFileDef", notes = "新增一个BaseFileDef")
    @Transactional
    public synchronized Result add(@Valid @RequestBody @ApiParam(name = "baseFileDefForm", value = "新增BaseFileDef的form表单", required = true)
                              BaseFileDefForm baseFileDefForm) {
        log.info("form:", baseFileDefForm.toString());
        if(baseFileDefForm.getId()!=null){
            validateIsModify(baseFileDefForm.getId());
        }
        BaseFileDef baseFileDef = new BeanUtils<BaseFileDef>().copyObj(baseFileDefForm, BaseFileDef.class);
        boolean update = baseFileDefService.saveOrUpdate(baseFileDef);
        List<BaseFileDef> list= validateIsRepeat(baseFileDef.getDocumentType(),baseFileDef.getMainTypeId(),baseFileDef.getInComeMainTypeId());
        AssertUtils.asserts(list.size()<2, FsscErrorType.DATA_DUPLICATE);
        if (update) {
            List<BaseFileDefLineForm> lineForms = baseFileDefForm.getBaseFileDefLineForms();
            List<Long> longList = lineForms.stream().map(b -> b.getId()).collect(Collectors.toList());
            longList.removeAll(Collections.singleton(null));
            QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq(BaseFileDefLine.FILE_DEF_ID, baseFileDef.getId());
            if (CollectionUtils.isNotEmpty(longList)) {
                lineQueryWrapper.notIn(BaseFileDefLine.ID, longList);
            }
            baseFileDefLineService.remove(lineQueryWrapper);
            List<BaseFileDefLine> baseFileDefLines =
                    new BeanUtils<BaseFileDefLine>().copyObjs(lineForms, BaseFileDefLine.class);
            baseFileDefLines.stream().forEach(fe -> fe.setFileDefId(baseFileDef.getId()));
            baseFileDefLineService.saveOrUpdateBatch(baseFileDefLines);
        } else {
            throw new FSSCException(FsscErrorType.SAVE_FAIL);
        }


        return Result.success();
    }


    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "删除BaseFileDef", notes = "根据url的id来指定删除对象")
    @Transactional
    public Result delete(@RequestBody List<Long> ids) {
        AssertUtils.asserts(CollectionUtils.isNotEmpty(ids), FsscErrorType.PARAM_CANT_BE_NULL);
        Collection<BaseFileDef> baseFileDefs = baseFileDefService.listByIds(ids);
        for (BaseFileDef def : baseFileDefs) {
            validateIsModify(def.getId());
        }
        baseFileDefService.removeByIds(ids);
        QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.in(BaseFileDefLine.FILE_DEF_ID, ids);
        baseFileDefLineService.remove(lineQueryWrapper);
        return Result.success();
    }

    /**
     * 验证行是否被占用
     * @param defId
     */
    private void validateIsModify(Long defId) {
        QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BaseFileDefLine.FILE_DEF_ID, defId);
        List<BaseFileDefLine> list = baseFileDefLineService.list(lineQueryWrapper);
        for (BaseFileDefLine line : list) {
            Long lineId = line.getId();
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.FILE_DEF_LINE_ID, lineId);
            List<File> fileList = fileService.list(fileQueryWrapper);
            AssertUtils.asserts(CollectionUtils.isEmpty(fileList), FsscErrorType.REFERENCES_BY_OTHER_FOR_INVALID);
        }

    }

    private List<BaseFileDef> validateIsRepeat(String documentType,Long mainTypeId,Long inMainTypeId){

        QueryWrapper<BaseFileDef> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BaseFileDef.DOCUMENT_TYPE, documentType)
        .eq(BaseFileDef.IS_VALID,"Y");
        if(mainTypeId!=null){
            lineQueryWrapper.eq(BaseFileDef.MAIN_TYPE_ID,mainTypeId);
        }
        if(inMainTypeId!=null){
            lineQueryWrapper.eq(BaseFileDef.IN_COME_MAIN_TYPE_ID,inMainTypeId);
        }
        List<BaseFileDef> list = baseFileDefService.list(lineQueryWrapper);
        return list;

    }





    @GetMapping(value = "getById/{id}")
    @ApiOperation(value = "获取BaseFileDef", notes = "获取指定ID的BaseFileDef信息")
    public Result<BaseFileDefVo> get(@PathVariable(value = "id") Long id) {
        log.info("get with id:{}", id);
        BaseFileDef baseFileDef = baseFileDefService.getById(id);
        BaseFileDefVo baseFileDefVo = new BeanUtils<BaseFileDefVo>().copyObj(baseFileDef, BaseFileDefVo.class);
        QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BaseFileDefLine.FILE_DEF_ID, id);
        List<BaseFileDefLine> list = baseFileDefLineService.list(lineQueryWrapper);
        baseFileDefVo.setBaseFileDefLineVos(new BeanUtils<BaseFileDefLineVo>().copyObjs(list, BaseFileDefLineVo.class));
        return Result.success(baseFileDefVo);
    }

    @PostMapping(value = "validOrUnValid")
    @ApiOperation(value = "失效或启用", notes = "失效或启用")
    @Transactional
    public Result unValid(@RequestBody BaseModifyStatusForm form) {
        List<Long> ids = form.getIds();
        String status = form.getStatus();
        log.info("get with id:{}", form.toString());
        AssertUtils.asserts(CollectionUtils.isNotEmpty(ids), FsscErrorType.ID_CANT_BE_NULL);
        Collection<BaseFileDef> fileDefs = baseFileDefService.listByIds(ids);
        if (CollectionUtils.isNotEmpty(fileDefs)) {
            for (BaseFileDef baseFileDef : fileDefs) {
                if ("Y".equals(status)) {
                    baseFileDef.setIsValid(status);
                    List<BaseFileDef> list= validateIsRepeat(baseFileDef.getDocumentType(),baseFileDef.getMainTypeId(),baseFileDef.getInComeMainTypeId());
                    AssertUtils.asserts(CollectionUtils.isEmpty(list), FsscErrorType.DATA_DUPLICATE);
                }
                if ("N".equals(status)) {
                    AssertUtils.asserts("Y".equals(baseFileDef.getIsValid()), FsscErrorType.ONLY_SUBMIT_UNVALID);
                    baseFileDef.setIsValid(status);
                }
            }
        }
        baseFileDefService.updateBatchById(fileDefs);
        return Result.success();
    }


//    @PostMapping(value = "valid/{id}")
//    @ApiOperation(value = "启用", notes = "启用")
//    @Transactional
//    public Result valid(@PathVariable(value = "id") Long id) {
//        log.info("get with id:{}", id);
//        BaseFileDef fileDef = baseFileDefService.getById(id);
//        AssertUtils.asserts(fileDef != null, FsscErrorType.NOT_FIND_DATA);
//        fileDef.setIsValid("Y");
//        baseFileDefService.updateById(fileDef);
//        return Result.success();
//    }


    @GetMapping(value = "findDefLines")
    @ApiOperation(value = "列表查询文件定义行", notes = "根据条件查询BaseFileDef列表数据")
    public Result<List<BaseFileDefLineVo>> list(@RequestParam(value = "documentType") String documentType,
                                                @RequestParam(value = "documentId", required = false) Long documentId,
                                                @RequestParam(value = "mainTypeId", required = false) Long mainTypeId,
                                                @RequestParam(value = "inComeMainTypeId", required = false) Long inComeMainTypeId) throws CloneNotSupportedException {
        String isValid=validateDocument(documentId,documentType);
        QueryWrapper<BaseFileDef> queryWrapper = new QueryWrapper<BaseFileDef>();
        AssertUtils.asserts(!(mainTypeId == null && inComeMainTypeId == null), FsscErrorType.MAIN_TYPE_CANT_ALL_BE_NULL);
        queryWrapper.eq(BaseFileDef.DOCUMENT_TYPE, documentType)
                .eq(mainTypeId != null, BaseFileDef.MAIN_TYPE_ID, mainTypeId)
                .eq(inComeMainTypeId != null, BaseFileDef.IN_COME_MAIN_TYPE_ID, inComeMainTypeId);
        if("Y".equals(isValid)){
            queryWrapper.eq(BaseFileDef.IS_VALID,isValid);
        }
        List<BaseFileDef> baseFileDefs = baseFileDefService.list(queryWrapper);
        AssertUtils.asserts(CollectionUtils.isNotEmpty(baseFileDefs), FsscErrorType.FILE_DEF_NOT_FINED);


        BaseFileDef baseFileDef = baseFileDefs.get(0);
        Long defId = baseFileDef.getId();
        if(documentId!=null){
            QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
            fileQueryWrapper.eq(File.DOCUMENT_ID, documentId)
                    .eq(File.DOCUMENT_TYPE, documentType);
            List<File> fileList22 = fileService.list(fileQueryWrapper);
            if(CollectionUtils.isNotEmpty(fileList22)){
                for (File e:fileList22){
                    BaseFileDefLine baseFileDefLine = baseFileDefLineService.getById(fileList22.get(0).getFileDefLineId());
                    if(baseFileDefLine!=null){
                        defId=baseFileDefLine.getFileDefId();
                        break;
                    }
                }
            }else {
                if(!"Y".equals(isValid)){
                    throw new FSSCException(FsscErrorType.YOU_NOT_UPLOAD_FILE);
                }
            }
        }
        QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
        lineQueryWrapper.eq(BaseFileDefLine.FILE_DEF_ID, defId);
        List<BaseFileDefLine> list = baseFileDefLineService.list(lineQueryWrapper);

        List<BaseFileDefLineVo> baseFileDefLineVos = new BeanUtils<BaseFileDefLineVo>()
                .copyObjs(list, BaseFileDefLineVo.class);
        List<BaseFileDefLineVo> resultVos = new ArrayList<>();
        if (documentId != null) {
            for (BaseFileDefLineVo lineVo : baseFileDefLineVos) {
                Long lineId = StringUtil.castTolong(lineVo.getId());
                QueryWrapper<File> fileQueryWrapper = new QueryWrapper<>();
                fileQueryWrapper.eq(File.DOCUMENT_ID, documentId)
                        .eq(File.DOCUMENT_TYPE, documentType)
                        .eq(File.FILE_DEF_LINE_ID, lineId);
                List<File> fileList = fileService.list(fileQueryWrapper);
                if (CollectionUtils.isNotEmpty(fileList)) {
                    for (File file : fileList) {
                        BaseFileDefLineVo vo = (BaseFileDefLineVo) lineVo.clone();
                        vo.setFileId(StringUtil.objectToString(file.getId()));
                        vo.setFileName(file.getFileName());
                        vo.setFileType(file.getFileType());
                        vo.setFileUrl(file.getFileUrl());
                        resultVos.add(vo);
                    }

                } else {
                    resultVos.add(lineVo);
                }
            }
            return Result.success(resultVos);
        }

        return Result.success(baseFileDefLineVos);
    }

    private String validateDocument(Long documentId, String documentType) {
        if(documentId==null){
            return "Y";
        }
        String status = processMapper.findStatus(documentId, documentType);
        if(FsscEums.NEW.getValue().equals(status)
        ||FsscEums.RECALLED.getValue().equals(status)
        ||FsscEums.REJECTED.getValue().equals(status)){
            return "Y";
        }
        return "N";
    }


    @GetMapping(value = "page/conditions")
    @ApiOperation(value = "分页查询文件定义", notes = "根据条件查询BaseFileDef分页数据")
    public Result<IPage<BaseFileDefVo>> search(@ApiParam(name = "baseFileDefQueryForm", value = "BaseFileDef查询参数", required = true)
                                                       BaseFileDefQueryForm baseFileDefQueryForm) {
        log.info("search with baseFileDefQueryForm:{}", baseFileDefQueryForm.toString());
        return new Result<IPage<BaseFileDefVo>>().sucess(getPages(baseFileDefQueryForm));
    }


    private IPage<BaseFileDefVo> getPages(BaseFileDefQueryForm baseFileDefQueryForm) {

        IPage<BaseFileDef> baseFileDefPage = baseFileDefService.selectPage(baseFileDefQueryForm);
        IPage<BaseFileDefVo> baseFileDefVoPage = new BeanUtils<BaseFileDefVo>().copyPageObjs(baseFileDefPage, BaseFileDefVo.class);
        for (BaseFileDefVo vo : baseFileDefVoPage.getRecords()) {
            Long aLong = StringUtil.castTolong(vo.getId());
            QueryWrapper<BaseFileDefLine> lineQueryWrapper = new QueryWrapper<>();
            lineQueryWrapper.eq(BaseFileDefLine.FILE_DEF_ID, aLong);
            List<BaseFileDefLine> list = baseFileDefLineService.list(lineQueryWrapper);
            if (CollectionUtils.isNotEmpty(list)) {
                String names = "";
                for (BaseFileDefLine line : list) {
                    names += line.getAttachName() + ",";
                }
                if (names.length() > 1) {
                    names = names.substring(0, names.length() - 1);
                }
                vo.setAttchNames(names);
            }
        }
        return baseFileDefVoPage;
    }

    @GetMapping(value = "exportExcel")
    @ApiOperation(value = "导出", notes = "导出")
    public void exportExcel(@ApiParam(name = "baseFileDefQueryForm", value = "BaseFileDef查询参数", required = true)
                                    BaseFileDefQueryForm baseFileDefQueryForm) {
        log.info("search with baseFileDefQueryForm:{}", baseFileDefQueryForm.toString());
        List<BaseFileDefVo> records = getPages(baseFileDefQueryForm).getRecords();
        String[] title = {"序号", "单据名称", "支出大类", "收入大类", "是否有效", "附件类型"};
        String fileName = "附件类型定义";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s : title) {
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            BaseFileDefVo vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getDocumentName();
            content[i][2] = vo.getMainTypeName();
            content[i][3] = vo.getInComeMainTypeName();
            if ("Y".equals(vo.getIsValid())) {
                content[i][4] = "是";
            }
            if ("N".equals(vo.getIsValid())) {
                content[i][4] = "否";
            }
            content[i][5] = vo.getAttchNames();
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

}




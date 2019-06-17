package com.deloitte.services.fssc.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeUnitQueryForm;
import com.deloitte.platform.api.fssc.base.vo.AddDocumentTypeUnitForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeUnitForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeUnitVo;
import com.deloitte.platform.api.fssc.base.vo.BaseModifyStatusForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeUnit;
import com.deloitte.services.fssc.base.service.IBaseDocumentTypeUnitService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :   单据类型-组织单位 控制器实现类
 * @Modified :
 */
@Api(tags = "单据类型-组织单位 操作接口")
@Slf4j
@RestController
@RequestMapping("/base/documentTypeUnit")
@Deprecated
public class BaseDocumentTypeUnitController {

    @Autowired
    public IBaseDocumentTypeUnitService docTypeUnitService;


    @ApiOperation(value = "分页查询单据类型-单位信息",httpMethod = "POST", notes = "根据条件查询单据类型-单位信息分页数据")
    @ApiParam(name = "queryForm", value = "单据类型-单位信息查询参数", required = true)
    @PostMapping(value = "/page/conditions")
    public Result<IPage<BaseDocumentTypeUnitVo>> search(
            @Valid @RequestBody BaseDocumentTypeUnitQueryForm queryForm) {
        log.info("search with BaseDocumentTypeUnitQueryForm:{}",
                queryForm.toString());
        IPage<BaseDocumentTypeUnit> pageInfo = docTypeUnitService.selectPage(queryForm);
        IPage<BaseDocumentTypeUnitVo> voPageInfo = new BeanUtils<BaseDocumentTypeUnitVo>()
                .copyPageObjs(pageInfo, BaseDocumentTypeUnitVo.class);
        return Result.success(voPageInfo);
    }

    @ApiOperation(value = "修改单据类型-单位启用状态",httpMethod = "POST", notes = "支持批量")
    @ApiParam(name = "form", value = "修改单据类型-单位启用状态Form", required = true)
    @PostMapping(value = "/modifyValidStatus")
    public Result modifyValidStatus(@RequestBody @Valid BaseModifyStatusForm form) {
        if (CollectionUtils.isEmpty(form.getIds())){
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        docTypeUnitService.modifyValidFlag(form.getIds(), form.getStatus());
        return Result.success();
    }

    @ApiOperation(value = "新增单据类型-单位", httpMethod = "POST",notes = "支持批量")
    @ApiParam(name = "addForm", value = "修改单据类型-单位Form", required = true)
    @PostMapping(value = "/addDocTypeUnit")
    public Result addDocTypeUnit(@RequestBody AddDocumentTypeUnitForm addForm)
            throws Exception {
        log.info("search with AddDocumentTypeUnitForm:{}", JSON.toJSONString(addForm));
        List<BaseDocumentTypeUnitForm> formList = addForm.getFormList();
        List<BaseDocumentTypeUnit> newAllPoList = new ArrayList<>();
        String[] docTypeIds = addForm.getDocumentTypeIds().split(",");
        log.info("docTypeIds length: {}", docTypeIds.length);
        if (ArrayUtils.isEmpty(docTypeIds)) {
            throw new FSSCException(FsscErrorType.IN_PARAM_CAN_NOT_EMPTY);
        }
        for (String docTypeIdStr : docTypeIds) {
            Long docTypeId = Long.valueOf(docTypeIdStr);
            BaseDocumentTypeUnitQueryForm queryForm = new BaseDocumentTypeUnitQueryForm();
            queryForm.setDocumentTypeId(docTypeId);
            List<BaseDocumentTypeUnit> oldFormList = docTypeUnitService.selectList(queryForm);
            List<BaseDocumentTypeUnit> poList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(formList)) {
                for (BaseDocumentTypeUnitForm form : formList) {
                    BaseDocumentTypeUnit docTypeUnit = new BaseDocumentTypeUnit();
                    BeanUtils.copyProperties(form, docTypeUnit);
                    docTypeUnit.setDocumentTypeId(docTypeId);
                    docTypeUnit.setValidFlag(FsscEums.VALID.getValue());
                    poList.add(docTypeUnit);
                }
            }
            List<Long> oldDocTypeIdList = oldFormList.stream()
                    .map(BaseDocumentTypeUnit::getDocumentTypeId)
                    .collect(Collectors.toList());
            List<BaseDocumentTypeUnit> newPoList = poList.stream().filter(e -> !oldDocTypeIdList
                    .contains(e.getDocumentTypeId())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(newPoList)) {
                newAllPoList.addAll(newPoList);
            }
        }
        if (CollectionUtils.isEmpty(newAllPoList)) {
            throw new FSSCException(FsscErrorType.CANNOT_ALLOCATION_NEW_EXPENSE);
        }
        docTypeUnitService.saveOrUpdateBatch(newAllPoList);
        return Result.success();
    }

}




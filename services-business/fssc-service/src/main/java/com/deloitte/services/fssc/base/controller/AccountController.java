package com.deloitte.services.fssc.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.base.param.AccountSimpleQueryForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.fssc.base.entity.AccountSimple;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jawjiang
 */
@Api(tags = "会计科目-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/account")
@Deprecated
public class AccountController {

    @ApiOperation(value = "分页查询科目信息", notes = "分页查询科目信息")
    @ApiParam(name = "queryForm", value = "科目信息查询参数", required = true)
    @PostMapping(value = "page/conditions")
    public Result<IPage<AccountSimple>> search(@RequestBody AccountSimpleQueryForm queryForm) {
        // TODO 现在只是添加假数据接口,查询组织信息,后面需要调用4A接口查询
        log.info("search with searchOrgUnit:");
        List<AccountSimple> accountSimpleList = VirtualDataProvideTool.getAccountSimple();
        if (StringUtils.isNotBlank(queryForm.getName())) {
            accountSimpleList = accountSimpleList.stream()
                    .filter(e -> e.getName().indexOf(queryForm.getName()) != -1)
                    .collect(Collectors.toList());
        }
        if (StringUtils.isNotBlank(queryForm.getCode())) {
            accountSimpleList = accountSimpleList.stream()
                    .filter(e -> e.getName().indexOf(queryForm.getCode()) != -1)
                    .collect(Collectors.toList());
        }
        IPage<AccountSimple> iPage = new Page();
        iPage.setRecords(accountSimpleList).setTotal(Long.parseLong(accountSimpleList.size() + ""));
        return Result.success(iPage);
    }
}

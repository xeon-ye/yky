package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.FinExecutionVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.mapper.FinExecutionMapper;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :   Execution控制器实现类
 * @Modified :
 */
@Api(tags = "财务 收入预算操作接口")
@Slf4j
@RestController
public class ExecutionController {

    @Autowired
    public FinExecutionMapper finExecutionMapper;

    @ApiOperation(value = "当年总收入预算数据", notes = "当年总收入预算数据")
    @PostMapping("/queryTotalExe")
    public Result queryTotalExe(){
        Map map = new Hashtable();
        map.put("period", TimeUtil.getTime());
        FinExecutionVo finExecutionVo = finExecutionMapper.queryTotalExe(map);
        Result result = Result.success(finExecutionVo);
        return result;
    }
}




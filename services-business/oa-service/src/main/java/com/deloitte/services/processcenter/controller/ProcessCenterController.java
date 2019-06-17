package com.deloitte.services.processcenter.controller;

import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.processcenter.entity.ProcessCenterDto;
import com.deloitte.services.processcenter.service.ProcessCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-03-26
 * @Description :   流程中心
 * @Modified :
 */
@Api(tags = "流程中心操作接口")
@Slf4j
@RestController
@RequestMapping("/bpm")
public class ProcessCenterController {

    @Autowired
    private ProcessCenterService ibpmService;

    @ApiOperation(value = "流程中心模块", notes = "查询待办/已办总数/办结事宜总数/抄送事宜总数/我的请求总数")
    @PostMapping("/processcenter/model")
    public Result search(@RequestBody ProcessCenterDto processCenterDto) {
        log.info("processCenterDto:{}", processCenterDto);
        return ibpmService.search(processCenterDto);
    }

}

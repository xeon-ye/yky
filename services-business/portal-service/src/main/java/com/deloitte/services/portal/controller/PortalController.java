package com.deloitte.services.srpmp.controller;


import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : pengchao
 * @Date : Create in 2019-02-12
 * @Description :   ScientificResearchPerson控制器实现类
 * @Modified :
 */
@RequestMapping("/portal")
@RestController
public class PortalController {

    @GetMapping(value = "/test")
    public Result get() {
        return Result.success("ok");
    }
}




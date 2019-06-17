package com.deloitte.platform.api.srpmp.common;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryForm;
import com.deloitte.platform.api.srpmp.common.vo.SysDictForm;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainChildVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainTreeVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description :  SysDict控制器接口
 * @Modified :
 */

public interface SysDictClient {

    /**
     *  POST---节点新增
     * @param mainTainChildVo
     * @return
     */
    @PostMapping(value = "/node")
    Result addNode(@Valid @ModelAttribute SysDictMainTainChildVo mainTainChildVo, UserVo user);

    /**
     *  POST---树形字典新增
     * @param tainTreeVo
     * @return
     */
    @PostMapping
    Result addTree(@Valid @ModelAttribute SysDictMainTainTreeVo tainTreeVo, UserVo user);
//
//    /**
//    *  Delete---删除
//    * @param  id
//    * @return
//    */
//    @DeleteMapping(value = "/{id}")
//    Result delete(@PathVariable long id);
//
//    /**
//     *  Patch----部分更新
//     * @param  id, sysDictForm
//     * @return
//     */
//    @CrossOrigin
//    @PatchMapping(value = "/{id}")
//    Result update(@PathVariable long id, @Valid @RequestBody SysDictForm sysDictForm);
//
//    /**
//    *  GET----根据ID获取
//    * @param  id
//    * @return
//    */
//    @CrossOrigin
//    @GetMapping(value = "/{id}")
//    Result get(@PathVariable long id);
//
//    /**
//     *  GET----根据ID获取
//     * @param  code
//     * @return
//     */
//    @CrossOrigin
//    @GetMapping(value = "/code/{code}")
//    Result getByCode(@PathVariable String code);

    @CrossOrigin
    @PostMapping(value = "/search")
    Result search(@Valid @RequestBody SysDictMainTainVo mainTainVo);

    /**
     *  GET----根据ID获取
     * @param  codes
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/codes/{codes}")
    Result getByCodes(@PathVariable String codes);

    /**
     *  POST----复杂查询
     * @param  sysDictQueryForm
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/list")
    Result list(@Valid @RequestBody SysDictQueryForm sysDictQueryForm);
}

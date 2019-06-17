package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.AttamentInfoQueryForm;
import com.deloitte.platform.api.contract.vo.AttamentInfoForm;
import com.deloitte.platform.api.contract.vo.AttamentInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  AttamentInfo控制器接口
 * @Modified :
 */
public interface AttamentInfoClient {

    public static final String path="/contract/attament-info";


    /**
     *  POST---新增
     * @param attamentInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AttamentInfoForm attamentInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, attamentInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AttamentInfoForm attamentInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AttamentInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   attamentInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AttamentInfoVo>> list(@Valid @RequestBody AttamentInfoQueryForm attamentInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  attamentInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AttamentInfoVo>> search(@Valid @RequestBody AttamentInfoQueryForm attamentInfoQueryForm);
}

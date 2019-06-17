package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicProjectMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicProjectMap控制器接口
 * @Modified :
 */
public interface BasicProjectMapClient {

    public static final String path="/contract/basic-project-map";


    /**
     *  POST---新增
     * @param basicProjectMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicProjectMapForm basicProjectMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicProjectMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicProjectMapForm basicProjectMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicProjectMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicProjectMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicProjectMapVo>> list(@Valid @RequestBody BasicProjectMapQueryForm basicProjectMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicProjectMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicProjectMapVo>> search(@Valid @RequestBody BasicProjectMapQueryForm basicProjectMapQueryForm);
}

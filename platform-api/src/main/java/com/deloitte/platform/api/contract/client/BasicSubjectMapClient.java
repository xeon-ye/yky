package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicSubjectMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicSubjectMapForm;
import com.deloitte.platform.api.contract.vo.BasicSubjectMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicSubjectMap控制器接口
 * @Modified :
 */
public interface BasicSubjectMapClient {

    public static final String path="/contract/basic-subject-map";


    /**
     *  POST---新增
     * @param basicSubjectMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicSubjectMapForm basicSubjectMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicSubjectMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicSubjectMapForm basicSubjectMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicSubjectMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicSubjectMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicSubjectMapVo>> list(@Valid @RequestBody BasicSubjectMapQueryForm basicSubjectMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicSubjectMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicSubjectMapVo>> search(@Valid @RequestBody BasicSubjectMapQueryForm basicSubjectMapQueryForm);
}

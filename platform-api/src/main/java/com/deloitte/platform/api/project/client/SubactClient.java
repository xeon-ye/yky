package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.SubactQueryForm;
import com.deloitte.platform.api.project.vo.SubactForm;
import com.deloitte.platform.api.project.vo.SubactVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :  Subact控制器接口
 * @Modified :
 */
public interface SubactClient {

    public static final String path="/project/subact";


    /**
     *  POST---新增
     * @param subactForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SubactForm subactForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, subactForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody SubactForm subactForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SubactVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   subactQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SubactVo>> list(@Valid @RequestBody SubactQueryForm subactQueryForm);


    /**
     *  POST----复杂查询
     * @param  subactQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SubactVo>> search(@Valid @RequestBody SubactQueryForm subactQueryForm);
}

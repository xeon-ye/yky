package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.SubactBakQueryForm;
import com.deloitte.platform.api.project.vo.SubactBakForm;
import com.deloitte.platform.api.project.vo.SubactBakVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :  SubactBak控制器接口
 * @Modified :
 */
public interface SubactBakClient {

    public static final String path="/project/subact-bak";


    /**
     *  POST---新增
     * @param subactBakForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SubactBakForm subactBakForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, subactBakForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody SubactBakForm subactBakForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SubactBakVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   subactBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SubactBakVo>> list(@Valid @RequestBody SubactBakQueryForm subactBakQueryForm);


    /**
     *  POST----复杂查询
     * @param  subactBakQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SubactBakVo>> search(@Valid @RequestBody SubactBakQueryForm subactBakQueryForm);
}

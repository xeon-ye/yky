package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.InsandequipQueryForm;
import com.deloitte.platform.api.project.vo.InsandequipForm;
import com.deloitte.platform.api.project.vo.InsandequipVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  Insandequip控制器接口
 * @Modified :
 */
public interface InsandequipClient {

    public static final String path="/project/insandequip";


    /**
     *  POST---新增
     * @param insandequipForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute InsandequipForm insandequipForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, insandequipForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody InsandequipForm insandequipForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<InsandequipVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   insandequipQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<InsandequipVo>> list(@Valid @RequestBody InsandequipQueryForm insandequipQueryForm);


    /**
     *  POST----复杂查询
     * @param  insandequipQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<InsandequipVo>> search(@Valid @RequestBody InsandequipQueryForm insandequipQueryForm);
}

package com.deloitte.platform.api.fssc.engine.automatic;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvAccountElementForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvAccountElementVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvAccountElement控制器接口
 * @Modified :
 */
public interface AvAccountElementClient {

    public static final String path="/fssc/av-account-element";


    /**
     *  POST---新增
     * @param avAccountElementForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AvAccountElementForm avAccountElementForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, avAccountElementForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AvAccountElementForm avAccountElementForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AvAccountElementVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   avAccountElementQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AvAccountElementVo>> list(@Valid @RequestBody AvAccountElementQueryForm avAccountElementQueryForm);


    /**
     *  POST----复杂查询
     * @param  avAccountElementQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AvAccountElementVo>> search(@Valid @RequestBody AvAccountElementQueryForm avAccountElementQueryForm);
}

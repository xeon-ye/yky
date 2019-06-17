package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccSelectedNotifyQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSelectedNotifyForm;
import com.deloitte.platform.api.hr.gcc.vo.GccSelectedNotifyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccSelectedNotify控制器接口
 * @Modified :
 */
public interface GccSelectedNotifyClient {

    public static final String path="/hr/gcc-selected-notify";


    /**
     *  POST---新增
     * @param gccSelectedNotifyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccSelectedNotifyForm gccSelectedNotifyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccSelectedNotifyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccSelectedNotifyForm gccSelectedNotifyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccSelectedNotifyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccSelectedNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccSelectedNotifyVo>> list(@Valid @RequestBody GccSelectedNotifyQueryForm gccSelectedNotifyQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccSelectedNotifyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccSelectedNotifyVo>> search(@Valid @RequestBody GccSelectedNotifyQueryForm gccSelectedNotifyQueryForm);
}

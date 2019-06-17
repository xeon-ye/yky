package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccCheckResultQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCheckResultForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCheckResultVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccCheckResult控制器接口
 * @Modified :
 */
public interface GccCheckResultClient {

    public static final String path="/hr/gcc-check-result";


    /**
     *  POST---新增
     * @param gccCheckResultForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccCheckResultForm gccCheckResultForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccCheckResultForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccCheckResultForm gccCheckResultForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccCheckResultVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccCheckResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccCheckResultVo>> list(@Valid @RequestBody GccCheckResultQueryForm gccCheckResultQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccCheckResultQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccCheckResultVo>> search(@Valid @RequestBody GccCheckResultQueryForm gccCheckResultQueryForm);
}

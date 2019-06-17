package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccAwardQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAwardForm;
import com.deloitte.platform.api.hr.gcc.vo.GccAwardVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccAward控制器接口
 * @Modified :
 */
public interface GccAwardClient {

    public static final String path="/hr/gcc-award";


    /**
     *  POST---新增
     * @param gccAwardForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccAwardForm gccAwardForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccAwardForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccAwardForm gccAwardForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccAwardVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccAwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccAwardVo>> list(@Valid @RequestBody GccAwardQueryForm gccAwardQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccAwardQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccAwardVo>> search(@Valid @RequestBody GccAwardQueryForm gccAwardQueryForm);

    /**
     *  POST---批量新增或更新
     * @param forms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccAwardForm> forms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}

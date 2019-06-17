package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccThesisQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccThesisForm;
import com.deloitte.platform.api.hr.gcc.vo.GccThesisVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccThesis控制器接口
 * @Modified :
 */
public interface GccThesisClient {

    public static final String path="/hr/gcc-thesis";


    /**
     *  POST---新增
     * @param gccThesisForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccThesisForm gccThesisForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccThesisForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccThesisForm gccThesisForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccThesisVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccThesisQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccThesisVo>> list(@Valid @RequestBody GccThesisQueryForm gccThesisQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccThesisQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccThesisVo>> search(@Valid @RequestBody GccThesisQueryForm gccThesisQueryForm);


    /**
     *  POST---批量新增或更新
     * @param gccThesisForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccThesisForm> gccThesisForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}

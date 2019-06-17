package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccProducesResultsQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProducesResultsForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProducesResultsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccProducesResults控制器接口
 * @Modified :
 */
public interface GccProducesResultsClient {

    public static final String path="/hr/gcc-produces-results";


    /**
     *  POST---新增
     * @param gccProducesResultsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccProducesResultsForm gccProducesResultsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccProducesResultsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccProducesResultsForm gccProducesResultsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccProducesResultsVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccProducesResultsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccProducesResultsVo>> list(@Valid @RequestBody GccProducesResultsQueryForm gccProducesResultsQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccProducesResultsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccProducesResultsVo>> search(@Valid @RequestBody GccProducesResultsQueryForm gccProducesResultsQueryForm);


    /**
     *  POST---新增
     * @param gccProducesResultsForm
     * @return
     */
    @PostMapping(value = path+"/addByUserId")
    Result addByUserId(@Valid @ModelAttribute GccProducesResultsForm gccProducesResultsForm);

    /**
     *  GET----根据ID获取
     * @param  userId
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{userId}")
    Result<GccProducesResultsVo> getByUserId(@PathVariable(value = "userId") long userId);
}

package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.DictQueryForm;
import com.deloitte.platform.api.isump.vo.DictForm;
import com.deloitte.platform.api.isump.vo.DictVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Dict控制器接口
 * @Modified :
 */
public interface DictClient {

    public static final String path="/isump/dict";


    /**
     *  POST---新增
     * @param dictForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DictForm dictForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, dictForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DictForm dictForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DictVo> get(@PathVariable(value = "id") long id);

    /**
     * 根据类型获取字典map
     * @param code
     * @return
     */
    @GetMapping(value = path+"/getDictMap/{code}")
    Result getDictMap(@PathVariable(value = "code") String code);


    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DictVo>> list(@Valid @RequestBody DictQueryForm dictQueryForm);


    /**
     *  POST----复杂查询
     * @param  dictQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DictVo>> search(@Valid @RequestBody DictQueryForm dictQueryForm);

    @PostMapping(value = path+"pageForFeign/conditions")
    Result<GdcPage<DictVo>> searchForFeign(@Valid @RequestBody DictQueryForm dictQueryForm);
}

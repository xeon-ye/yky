package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccTeachingQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTeachingForm;
import com.deloitte.platform.api.hr.gcc.vo.GccTeachingVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccTeaching控制器接口
 * @Modified :
 */
public interface GccTeachingClient {

    public static final String path="/hr/gcc-teaching";


    /**
     *  POST---新增
     * @param gccTeachingForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccTeachingForm gccTeachingForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccTeachingForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccTeachingForm gccTeachingForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccTeachingVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccTeachingQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccTeachingVo>> list(@Valid @RequestBody GccTeachingQueryForm gccTeachingQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccTeachingQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccTeachingVo>> search(@Valid @RequestBody GccTeachingQueryForm gccTeachingQueryForm);

    /**
     *  POST---批量新增或更新
     * @param gccWorkExperienceForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccTeachingForm> gccWorkExperienceForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}

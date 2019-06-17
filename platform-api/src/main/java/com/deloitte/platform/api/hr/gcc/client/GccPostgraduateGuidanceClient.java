package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccPostgraduateGuidanceQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPostgraduateGuidanceForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPostgraduateGuidanceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccPostgraduateGuidance控制器接口
 * @Modified :
 */
public interface GccPostgraduateGuidanceClient {

    public static final String path="/hr/gcc-postgraduate-guidance";


    /**
     *  POST---新增
     * @param gccPostgraduateGuidanceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccPostgraduateGuidanceForm gccPostgraduateGuidanceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccPostgraduateGuidanceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccPostgraduateGuidanceForm gccPostgraduateGuidanceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccPostgraduateGuidanceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccPostgraduateGuidanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccPostgraduateGuidanceVo>> list(@Valid @RequestBody GccPostgraduateGuidanceQueryForm gccPostgraduateGuidanceQueryForm);



    /**
     *  POST---新增
     * @param gccAcademicInnovationForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdateByUserId")
    Result saveOrUpdate(@Valid @ModelAttribute List<GccPostgraduateGuidanceForm> gccAcademicInnovationForm);



    /**
     *  GET----根据ID获取
     * @param  userId
     * @return
     */
    @GetMapping(value = path+"/getByUserId/{userId}")
    Result<GccPostgraduateGuidanceVo> getByUserId(@PathVariable(value = "userId") long userId);

    /**
     *  POST----复杂查询
     * @param  gccPostgraduateGuidanceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccPostgraduateGuidanceVo>> search(@Valid @RequestBody GccPostgraduateGuidanceQueryForm gccPostgraduateGuidanceQueryForm);
}

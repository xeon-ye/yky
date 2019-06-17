package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccEducationalReformQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccEducationalReformForm;
import com.deloitte.platform.api.hr.gcc.vo.GccEducationalReformVo;
import com.deloitte.platform.api.hr.gcc.vo.GccWorkExperienceForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccEducationalReform控制器接口
 * @Modified :
 */
public interface GccEducationalReformClient {

    public static final String path="/hr/gcc-educational-reform";


    /**
     *  POST---新增
     * @param gccEducationalReformForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccEducationalReformForm gccEducationalReformForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccEducationalReformForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccEducationalReformForm gccEducationalReformForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccEducationalReformVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccEducationalReformQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccEducationalReformVo>> list(@Valid @RequestBody GccEducationalReformQueryForm gccEducationalReformQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccEducationalReformQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccEducationalReformVo>> search(@Valid @RequestBody GccEducationalReformQueryForm gccEducationalReformQueryForm);

    /**
     *  POST---批量新增或更新
     * @param gccEducationalReformForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccEducationalReformForm> gccEducationalReformForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}

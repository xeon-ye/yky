package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccReviewPlatformQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewPlatformForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewPlatformVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :  GccReviewPlatform控制器接口
 * @Modified :
 */
public interface GccReviewPlatformClient {

    public static final String path="/hr/gcc-review-platform";


    /**
     *  POST---新增
     * @param gccReviewPlatformForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccReviewPlatformForm gccReviewPlatformForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccReviewPlatformForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccReviewPlatformForm gccReviewPlatformForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccReviewPlatformVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccReviewPlatformQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccReviewPlatformVo>> list(@Valid @RequestBody GccReviewPlatformQueryForm gccReviewPlatformQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccReviewPlatformQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccReviewPlatformVo>> search(@Valid @RequestBody GccReviewPlatformQueryForm gccReviewPlatformQueryForm);




}

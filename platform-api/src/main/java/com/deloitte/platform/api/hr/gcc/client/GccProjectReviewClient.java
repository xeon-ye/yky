package com.deloitte.platform.api.hr.gcc.client;

import com.deloitte.platform.api.hr.gcc.param.GccProjectReviewQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectReviewForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProjectReviewVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccProjectReview控制器接口
 * @Modified :
 */
public interface GccProjectReviewClient {

    public static final String path="/hr/gcc-project-review";


    /**
     *  POST---新增
     * @param gccProjectReviewForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccProjectReviewForm gccProjectReviewForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccProjectReviewForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccProjectReviewForm gccProjectReviewForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccProjectReviewVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccProjectReviewQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccProjectReviewVo>> list(@Valid @RequestBody GccProjectReviewQueryForm gccProjectReviewQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccProjectReviewQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccProjectReviewVo>> search(@Valid @RequestBody GccProjectReviewQueryForm gccProjectReviewQueryForm);
}

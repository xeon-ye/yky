package com.deloitte.platform.api.hr.gcc.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccReviewOptionQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReOpBatch;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewOptionForm;
import com.deloitte.platform.api.hr.gcc.vo.GccReviewOptionVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-13
 * @Description :  GccReviewOption控制器接口
 * @Modified :
 */
public interface GccReviewOptionClient {

    public static final String path="/hr/gcc-review-option";


    /**
     *  POST---新增
     * @param gccReviewOptionForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccReviewOptionForm gccReviewOptionForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccReviewOptionForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccReviewOptionForm gccReviewOptionForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccReviewOptionVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccReviewOptionQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccReviewOptionVo>> list(@Valid @RequestBody GccReviewOptionQueryForm gccReviewOptionQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccReviewOptionQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccReviewOptionVo>> search(@Valid @RequestBody GccReviewOptionQueryForm gccReviewOptionQueryForm);



    /**
     *  POST---批量新增
     * @param gccReOpBatch
     * @param   types 表示是暂存或评审完成
     * @return
     */
    @PostMapping(value = path+"/gatchAdd/{types}")
    Result gatchAdd(@PathVariable(value = "types") String types,@Valid @RequestBody GccReOpBatch gccReOpBatch);



}

package com.deloitte.platform.api.fssc.advance;

import com.deloitte.platform.api.fssc.advance.param.VerificationDetailQueryForm;
import com.deloitte.platform.api.fssc.advance.vo.VerificationDetailForm;
import com.deloitte.platform.api.fssc.advance.vo.VerificationDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmVerificationDetail控制器接口
 * @Modified :
 */
public interface VerificationDetailClient {

    public static final String path="/advance/bm-verification-detail";


    /**
     *  POST---新增
     * @param verificationDetailForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute VerificationDetailForm verificationDetailForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, verificationDetailForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody VerificationDetailForm verificationDetailForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<VerificationDetailVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bmVerificationDetailForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<VerificationDetailVo>> list(@Valid @RequestBody VerificationDetailQueryForm verificationDetailQueryForm);


    /**
     *  POST----复杂查询
     * @param  verificationDetailQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<VerificationDetailVo>> search(@Valid @RequestBody VerificationDetailQueryForm verificationDetailQueryForm);
}

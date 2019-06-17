package com.deloitte.platform.api.hr.gcc.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccEmployContractQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccBaseQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccEmployContractAllVo;
import com.deloitte.platform.api.hr.gcc.vo.GccEmployContractForm;
import com.deloitte.platform.api.hr.gcc.vo.GccEmployContractVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description :  GccEmployContract控制器接口
 * @Modified :
 */
public interface GccEmployContractClient {

    public static final String path="/hr/gcc-employ-contract";


    /**
     *  POST---新增
     * @param gccEmployContractForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccEmployContractForm gccEmployContractForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccEmployContractForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccEmployContractForm gccEmployContractForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccEmployContractVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccEmployContractQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccEmployContractVo>> list(@Valid @RequestBody GccEmployContractQueryForm gccEmployContractQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccEmployContractQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccEmployContractVo>> search(@Valid @RequestBody GccEmployContractQueryForm gccEmployContractQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccOnjobInformationQueryAllForm
     * @return
     */
    @PostMapping(value = path+"/pageAll/conditions")
    Result<IPage<GccEmployContractAllVo>> searchAll(@Valid @RequestBody GccBaseQueryForm gccOnjobInformationQueryAllForm);


    /**
     *  POST----列表查询
     * @param   gccOnjobInformationQueryAllForm
     * @return
     */
    @PostMapping(value = path+"/listAll/conditions")
    Result<List<GccEmployContractAllVo>> listAll(@Valid @RequestBody GccBaseQueryForm gccOnjobInformationQueryAllForm);


    /**
     *  POST----导出模版
     * @param  gccOnjobInformationQueryAllForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccBaseQueryForm gccOnjobInformationQueryAllForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


}

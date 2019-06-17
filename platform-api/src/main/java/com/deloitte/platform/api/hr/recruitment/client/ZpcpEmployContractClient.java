package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpEmployContractQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployContractForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpEmployContractVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-10
 * @Description :  ZpcpEmployContract控制器接口
 * @Modified :
 */
public interface ZpcpEmployContractClient {

    public static final String path="/hr/zpcp-employ-contract";


    /**
     *  POST---新增
     * @param zpcpEmployContractForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpEmployContractForm zpcpEmployContractForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpEmployContractForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpEmployContractForm zpcpEmployContractForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpEmployContractVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpEmployContractQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpEmployContractVo>> list(@Valid @RequestBody ZpcpEmployContractQueryForm zpcpEmployContractQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpEmployContractQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpEmployContractVo>> search(@Valid @RequestBody ZpcpEmployContractQueryForm zpcpEmployContractQueryForm);

    /**
     * GET----聘任审核时根据条件导出列表
     *
     * @param queryForm
     * @return
     */
    @GetMapping(value = path + "/exportEmployContractList")
    Result exportEmployContractList(@Valid @ModelAttribute ZpcpEmployContractQueryForm queryForm, HttpServletRequest request, HttpServletResponse response);

}

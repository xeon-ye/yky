package com.deloitte.platform.api.fssc.ppc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.param.ContractInformationQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.ContractInformationForm;
import com.deloitte.platform.api.fssc.ppc.vo.ContractInformationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ContractInformation控制器接口
 * @Modified :
 */
public interface ContractInformationClient {

    public static final String path="/ppc/contract-information";


    /**
     *  POST---新增
     * @param contractInformationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ContractInformationForm contractInformationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, contractInformationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ContractInformationForm contractInformationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ContractInformationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   contractInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ContractInformationVo>> list(@Valid @RequestBody ContractInformationQueryForm contractInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  contractInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ContractInformationVo>> search(@Valid @RequestBody ContractInformationQueryForm contractInformationQueryForm);
}

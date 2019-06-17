package com.deloitte.platform.api.hr.staffAllotment.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.staffAllotment.param.HrResignApplicationQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrResignApplicationForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrResignApplicationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrResignApplication控制器接口
 * @Modified :
 */
public interface HrResignApplicationClient {

    public static final String path="/hr/staffAllotment/hr-resign-application";


    /**
     *  POST---新增
     * @param hrResignApplicationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrResignApplicationForm hrResignApplicationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrResignApplicationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrResignApplicationForm hrResignApplicationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrResignApplicationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrResignApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrResignApplicationVo>> list(@Valid @RequestBody HrResignApplicationQueryForm hrResignApplicationQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrResignApplicationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrResignApplicationVo>> search(@Valid @RequestBody HrResignApplicationQueryForm hrResignApplicationQueryForm);

    /**
     *  POST---保存
     * @param hrResignApplicationForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute HrResignApplicationForm hrResignApplicationForm);

    /**
     *  POST---提交
     * @param hrResignApplicationForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute HrResignApplicationForm hrResignApplicationForm);

    /**
     * POST---根据编号查询信息
     * @param hrResignApplicationVo
     * @return
     */
    @PostMapping(value = path+"/selectByCode")
    Result<HrResignApplicationVo> selectByCode(@Valid @RequestBody  HrResignApplicationVo hrResignApplicationVo);

    /**
     * POST---根据编号查询信息（全中文）
     * @param hrResignApplicationVo
     * @return
     */
    @PostMapping(value = path+"/getByCode")
    Result<HrResignApplicationVo> getByCode(@Valid @RequestBody @ApiParam(name="HrResignApplication",value="新增HrResignApplication的form表单",required=true) HrResignApplicationVo hrResignApplicationVo);

    /**
     * POST---审批结束对数据进行更新
     * @param hrResignApplicationForm
     * @return
     */
    @PostMapping(value = path+"/updateDate")
    Result updateDate(@Valid @ModelAttribute HrResignApplicationForm hrResignApplicationForm);
}

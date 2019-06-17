package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpCheckNumberQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckNumberForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckNumberVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-19
 * @Description :  ZpcpCheckNumber控制器接口
 * @Modified :
 */
public interface ZpcpCheckNumberClient {

    public static final String path="/hr/zpcp-check-number";


    /**
     *  POST---新增
     * @param zpcpCheckNumberForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpCheckNumberForm zpcpCheckNumberForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpCheckNumberForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpCheckNumberForm zpcpCheckNumberForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpCheckNumberVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpCheckNumberQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpCheckNumberVo>> list(@Valid @RequestBody ZpcpCheckNumberQueryForm zpcpCheckNumberQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpCheckNumberQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpCheckNumberVo>> search(@Valid @RequestBody ZpcpCheckNumberQueryForm zpcpCheckNumberQueryForm);

    @ApiOperation(value = "新增或更新人数审核人数", notes = "新增一个ZpcpCheckNumber")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    Result addOrUpdate(@Valid @RequestBody @ApiParam(name = "zpcpCheckNumberForm", value = "新增ZpcpCheckNumber的form表单", required = true) ZpcpCheckNumberForm zpcpCheckNumberForm);

    @ApiOperation(value = "通过通知id和审核类型查询审核人数信息", notes = "通过通知id和审核类型查询审核人数信息")
    Result<ZpcpCheckNumberVo> getCheckNumberByNoticeIdAndCheckType(@Valid @RequestBody @ApiParam(name = "ZpcpCheckNumberQueryForm", value = "通过通知id和审核类型查询审核人数信息", required = true) ZpcpCheckNumberQueryForm queryForm);
}

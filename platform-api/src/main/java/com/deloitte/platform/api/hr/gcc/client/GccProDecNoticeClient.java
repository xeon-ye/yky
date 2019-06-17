package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccProDecNoticeQueryForm;
import com.deloitte.platform.api.hr.gcc.param.GccProDecNoticeSelfForm;
import com.deloitte.platform.api.hr.gcc.param.ListProjectForNotice;
import com.deloitte.platform.api.hr.gcc.vo.GccProDecNoticeForm;
import com.deloitte.platform.api.hr.gcc.vo.GccProDecNoticeSelfVo;
import com.deloitte.platform.api.hr.gcc.vo.GccProDecNoticeVo;
import com.deloitte.platform.api.hr.gcc.vo.NoticeForProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccProDecNotice控制器接口
 * @Modified :
 */
public interface GccProDecNoticeClient {

    public static final String path="/hr/gcc-pro-dec-notice";


    /**
     *  POST---新增
     * @param gccProDecNoticeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccProDecNoticeForm gccProDecNoticeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccProDecNoticeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccProDecNoticeForm gccProDecNoticeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccProDecNoticeVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccProDecNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccProDecNoticeVo>> list(@Valid @RequestBody GccProDecNoticeQueryForm gccProDecNoticeQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccProDecNoticeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccProDecNoticeVo>> search(@Valid @RequestBody GccProDecNoticeQueryForm gccProDecNoticeQueryForm);

    /**
     * post---导出
     * @param  gccProDecNoticeQueryForm
     * @return
     */
    @GetMapping(value = path+"/export")
    Result exportExcel(@Valid @ModelAttribute GccProDecNoticeQueryForm gccProDecNoticeQueryForm, HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     *  GET----设置启动按钮
     * @param  id 主键 type 类型（0所院、1院校）
     * @return
     */
    @GetMapping(value = path+"/start/{id}")
    Result setStart(@PathVariable(value = "id") long id);



    /**
     *  POST----复杂查询自助服务申报列表
     * @param  queryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditionsSelf")
    Result<IPage<GccProDecNoticeSelfVo>> searchSelf(@Valid @RequestBody GccProDecNoticeSelfForm queryForm);



    /**
     *  POST----获取项目列表
     * @param   listProjectForNotice
     * @return
     */
    @PostMapping(value = path+"/listProject")
    Result<List<NoticeForProjectVo>> listProject(@Valid @RequestBody ListProjectForNotice listProjectForNotice);


}

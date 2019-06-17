package com.deloitte.platform.api.portal.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.portal.param.NoticeQueryForm;
import com.deloitte.platform.api.portal.vo.NoticeForm;
import com.deloitte.platform.api.portal.vo.NoticeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-03
 * @Description :  Notice控制器接口
 * @Modified :
 */
public interface NoticeClient {

    public static final String path = "/portal/notice";


    /**
     * POST---新增
     *
     * @param noticeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute NoticeForm noticeForm);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * Patch----部分更新
     *
     * @param id, noticeForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody NoticeForm noticeForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<NoticeVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param noticeQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<NoticeVo>> list(@Valid @RequestBody NoticeQueryForm noticeQueryForm);


    /**
     * POST----复杂查询
     *
     * @param noticeQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<NoticeVo>> search(@Valid @RequestBody NoticeQueryForm noticeQueryForm);

    /**
     * GET----列表查询
     *
     * @param num 首页展示公告条数
     * @return
     */
    @GetMapping(value = path + "/notice/conditions/{num}")
    Result<List<NoticeVo>> getHomeList(@PathVariable(value = "num") int num);
}

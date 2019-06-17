package com.deloitte.platform.api.oaservice.news.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.news.param.NewsQueryForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsProcessForm;
import com.deloitte.platform.api.oaservice.news.vo.NewsVo;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  News控制器接口
 * @Modified :
 */
public interface NewsClient {

    public static final String path = "/oaservice/news";


    /**
     * POST---新增
     *
     * @param newsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute NewsForm newsForm);

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
     * @param id, newsForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody NewsForm newsForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<NewsVo> get(@PathVariable(value = "id") String id);

    /**
     * POST----列表查询
     *
     * @param newsQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<NewsVo>> list(@Valid @RequestBody NewsQueryForm newsQueryForm);


    /**
     * POST----门户复杂查询
     *
     * @param newsQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<GdcPage<NewsVo>> search(@Valid @RequestBody NewsQueryForm newsQueryForm, @RequestParam int currentPage, int pageSize);

    /**
     * POST----协同办公复杂查询
     *
     * @param newsQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/manage")
    Result<GdcPage<NewsVo>> manageSearch(@Valid @RequestBody NewsQueryForm newsQueryForm, @RequestParam int currentPage, int pageSize);

    /**
     * POST----复杂查询
     *
     * @return
     */
    @GetMapping(value = path + "/list/conditions")
    Result<List<NewsVo>> homeList(@PathVariable(value = "num") Integer num, @PathVariable(value = "newsTypeCode") String newsTypeCode);

    /**
     *  POST----复杂查询
     * @param  newsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/permission/conditions")
    Result<IPage<NewsVo>> searchWithPermission(@Valid @RequestBody NewsQueryForm newsQueryForm, String token);


}

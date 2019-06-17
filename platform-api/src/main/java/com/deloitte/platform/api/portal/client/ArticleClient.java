package com.deloitte.platform.api.portal.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.portal.param.ArticleQueryForm;
import com.deloitte.platform.api.portal.vo.ArticleForm;
import com.deloitte.platform.api.portal.vo.ArticleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-02
 * @Description :  Article控制器接口
 * @Modified :
 */
public interface ArticleClient {

    public static final String path = "/potal/article";


    /**
     * POST---新增
     *
     * @param articleForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ArticleForm articleForm);

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
     * @param id, articleForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ArticleForm articleForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<ArticleVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param articleQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<ArticleVo>> list(@Valid @RequestBody ArticleQueryForm articleQueryForm);


    /**
     * POST----复杂查询
     *
     * @param articleQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<ArticleVo>> search(@Valid @RequestBody ArticleQueryForm articleQueryForm);

    /**
     * GET----列表查询
     *
     * @param num 首页展示新闻条数
     * @return
     */
    @GetMapping(value = path + "/homeNews/conditions/{num}")
    Result<List<ArticleVo>> getHomeList(@PathVariable(value = "num") int num);

}

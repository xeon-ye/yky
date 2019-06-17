package com.deloitte.platform.api.oaservice.client;

import com.deloitte.platform.api.oaservice.param.OaMenuFavoritesQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaMenuFavoritesForm;
import com.deloitte.platform.api.oaservice.vo.OaMenuFavoritesVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description :  OaMenuFavorites控制器接口
 * @Modified :
 */
public interface OaMenuFavoritesClient {

    public static final String path="/oaservice/menu/favorites";


    /**
     *  POST---新增
     * @param oaMenuFavoritesForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMenuFavoritesForm oaMenuFavoritesForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMenuFavoritesForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMenuFavoritesForm oaMenuFavoritesForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMenuFavoritesVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMenuFavoritesQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMenuFavoritesVo>> list(@Valid @RequestBody OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMenuFavoritesQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMenuFavoritesVo>> search(@Valid @RequestBody OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm);
}

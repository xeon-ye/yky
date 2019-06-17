package com.deloitte.services.fssc.system.dic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.dic.param.DicQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicVo;
import com.deloitte.services.fssc.system.dic.entity.Dic;
import com.deloitte.services.fssc.system.dic.entity.DicValue;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : Dic服务类接口
 * @Modified :
 */
public interface IDicService extends IService<Dic> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Dic>
     */
    IPage<Dic> selectPage(DicQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Dic>
     */
    List<Dic> selectList(DicQueryForm queryForm);

    //public BaseResponse<Dic> findTypeByPage(PageRequestDto pageRequestDto,DicQueryForm  dicQueryForm);


    /**
     * 删除字典
     * @param idList
     * @return
     */
     public int removeByEnumType(List<String> idList );

     public int countBySysEnumType(Dic dic);

     public String saveType(Dic dic);


    /**
     * 生效/失效
     * @param idList
     * @param validFlag
     * @return
     */
    boolean  modifyValidFlag(List<Long> idList,String validFlag);


    public int removeByValue(List<Long> idList);

    public int countBySysEnumValue(DicValue dicValue);

}

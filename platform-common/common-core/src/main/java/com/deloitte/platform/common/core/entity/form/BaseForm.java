package com.deloitte.platform.common.core.entity.form;

import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.PlatFormException;
import com.deloitte.platform.common.core.util.BeanUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel
@Slf4j
@Data
public class BaseForm{

    /**
     * From转化为Po，进行后续业务处理
     */
    public Object formToPo( Class poClass) throws PlatFormException {
        try {
            Object po =  BeanUtils.beanTransaction( this, poClass);
            return po;
        }catch(Exception e){
            throw new PlatFormException(PlatformErrorType.FORM_TO_PO_ERROR,e.getMessage());
        }
    }


}

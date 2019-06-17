package com.deloitte.services.fssc.common.util;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FsscCommonUtil {


    private static FsscCommonServices commonServices;

    private static ProcessMapper processMapper;

    @Autowired
    public  void setProcessMapper(ProcessMapper processMapper) {
        this.processMapper = processMapper;
    }

    @Autowired
    public void setCommonServices(FsscCommonServices commonServices) {
        this.commonServices = commonServices;
    }


    public static Long getCurrentUserId() {

        UserVo currentUser = commonServices.getCurrentUser();
        if (currentUser != null) {
            String id = currentUser.getId();
            return StringUtil.castTolong(id);
        }
        return null;
    }


    public static void valiHasData(Long id,String documentType){
        if(id!=null){
            Long document = processMapper.findDocument(id, documentType);
            AssertUtils.asserts(document!=null, FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        }
    }

}

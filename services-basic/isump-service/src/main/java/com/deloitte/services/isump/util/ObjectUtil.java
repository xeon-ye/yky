package com.deloitte.services.isump.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author zhangdi
 * @Date 30/05/2019
 */
@Component
@Slf4j
public class ObjectUtil {

    /**
     * 生成流程单号
     * @return
     */
    public static String getOrderNum(String type, ConfigPropertiesUtil configPropertiesUtil) {
        String str ="0123456789";
        String s = "";
        for(int i=0; i<4; i++){
            char ch = str.charAt(new Random().nextInt(str.length()));
            s += ch;
        }
        System.out.println(s);
        String head = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(new Date());
        if(type.equals(configPropertiesUtil.orgRegisterProcessDefineKey)){
            head = "JGZC" + date + s;
        }
        if(type.equals(configPropertiesUtil.userRegisterProcessDefineKey)){
            head = "RYZC" + date + s;
        }
        return head;
    }

    /**
     * 根据code生成path路径
     * @param orgCode code
     * @return
     */
    public static String getOrgPath(String orgCode){
        String pathString = "";
        int codeLength = orgCode.length();
        if(codeLength > 0){
            if(codeLength == 4){
                pathString += orgCode.substring(0,4);
            }else if(codeLength == 7){
                pathString += orgCode.substring(0,4)+"/"+orgCode.substring(0,7);
            }else if(codeLength > 7){
                pathString += orgCode.substring(0,4)+"/"+orgCode.substring(0,7)+"/"+orgCode.substring(0,codeLength);
            }
        }
        log.info("path: "+pathString);
        return pathString;
    }
}

package com.deloitte.services.srpmp.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.io.*;
import java.util.Iterator;

/**
 * @Author : jackliu
 * @Date : Create in 16:56 28/01/2019
 * @Description :
 * @Modified :
 */
public class GeneratorSysDict {

    public static void main(String[] args) {

        String str = readFileByChars("C:\\Deloitte_Projects\\SRPMS_Project\\platform\\services-business\\srpmp-service\\src\\main\\java\\com\\deloitte\\services\\srpmp\\common\\util\\sysDict.json").toString();

        JSONObject json =  JSONObject.parseObject(str);
        System.out.println(json.toString());
        Iterator<String>  keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String dictType = keys.next();
            String dictName = json.getJSONObject(dictType).getString("name");
            String tempStr = json.getJSONObject(dictType).getString("lable");
            String codeArr = json.getJSONObject(dictType).getString("code");

            String[] strArr = tempStr.split("/");
            String[] strCodeArr = null;
            if (codeArr != null) {
                strCodeArr  = codeArr.split("/");
            }

            int countC1 = 10;
            long c1key = IdWorker.getId();


            System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c1key + "','" + dictType + "','" + dictName + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");

            for (int i = 0; i < strArr.length; i++) {
                String temp = strArr[i].trim();
                long c2key = IdWorker.getId();
                if (codeArr == null) {
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + ++countC1 + "','" + strArr[i] + "','" + c1key + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                } else {
                    System.out.println("insert into SYS_DICT (ID,DICT_CODE,DICT_VALUE,DICT_PARENT,ACTIVE_DATE,EXPIRED_DATE,CREATE_BY,UPDATE_BY) values ('" + c2key + "','" + strCodeArr[i] + "','" + strArr[i] + "','" + c1key + "'," + "to_date('2000-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + "," + "to_date('2099-11-26 00:04:47','yyyy-mm-dd hh24:mi:ss')" + ",'" + "system" + "','" + "system" + "');");
                }

            }
        }


        keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String dictType = keys.next();
            String dictName = json.getJSONObject(dictType).getString("name");
            System.out.println(dictType +  "(\"" + dictType + "\", \"" + dictName +"\"),");
        }
    }

    public static StringBuffer readFileByChars(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));

            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    sb.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            sb.append(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb;
    }
}

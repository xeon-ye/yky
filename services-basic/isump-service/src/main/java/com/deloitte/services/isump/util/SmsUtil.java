package com.deloitte.services.isump.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.exception.ServiceException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.*;

@Log4j
public class SmsUtil {


    //发送验证码的请求路径URL
    private static String SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static String APP_KEY="c5e84ed94693970a4882f66fd64e771b";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static String APP_SECRET="b2887185eb40";
    //短信模板ID
    private static String templateid="3057527";

    public static Map<String,JSONObject> map;

    public static void main(String[] args) {
        JSONObject jsonObject = sendSms("15696201696","login");
        System.out.println(jsonObject.toJSONString());
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     * @throws Exception
     */
    public static JSONObject sendSms(String phone,String type){
        Random random = new Random();
        //随机数
        String NONCE = String.valueOf(1000 + random.nextInt(8999));
        String authCode = String.valueOf(1000 + random.nextInt(8999));
        try{
            JSONObject object = SmsUtil.sendSms(phone,authCode,NONCE);
            if(map == null){
                map = new HashMap<>();
            }
            map.put(phone+type,object);
            if("200".equals(object.get("code"))){

                log.info("电话：" + phone + "的登录验证码为" + object.getString("authCode"));
            }else{
                log.info("电话：" + phone + "的发送短信失败，失败代码：" + object.getString("code"));
            }

            return object;
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(PlatformErrorType.SYSTEM_ERROR,"短信发送异常！");
        }

    }

    /**
     * 验证验证码是否正确
     * @param phone 电话
     * @param type  验证码类型
     * @param code  用户输入的验证码
     * @return
     */
    public static Boolean verifySms(String phone,String type,String code){
        //获取缓存
        JSONObject object = map.get(phone+type);
        if(object !=null && code.equals(object.get("authCode"))){
            long curTime = (new Date()).getTime() / 1000L;
            //超时
            if(curTime - Long.valueOf(object.getString("sendTime")) <=600L) {
                return true;
            }
        }
        return false;
    }
    /**
     * 给指定电话号码发送短信
     * @param phone 电话号码
     * @param authCode 验证码
     * @param nonce 随机数
     * @return
     * @throws Exception
     */
    public static JSONObject sendSms(String phone,String authCode,String nonce) throws Exception {


        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */

        String checkSum = SmsUtil.sha1Encrypt(APP_SECRET + nonce + curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //TODO 这里要指定发送模板
        //nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobile", phone));
        nvps.add(new BasicNameValuePair("authCode", authCode));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        JSONObject object = JSON.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
        object.put("code",200);
        object.put("sendTime",curTime);
        object.put("nonce",nonce);
        object.put("authCode",authCode);
        return object;
    }

    /**
     * 使用SHA1算法对字符串进行加密
     * @param str
     * @return
     */
    public static String sha1Encrypt(String str) {

        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}

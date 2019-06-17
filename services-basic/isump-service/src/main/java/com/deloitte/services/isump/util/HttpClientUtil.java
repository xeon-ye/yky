//package com.deloitte.services.isump.util;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import java.io.IOException;
//
//
//public class HttpClientUtil {
//
//    public static JSONObject doGetRequest(String uri) throws IOException{
//        return HttpClientUtil.doGetRequest(uri,new JSONObject());
//    }
//    public static JSONObject doGetRequest(String uri, JSONObject param) throws IOException {
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(uri);
//        JSONObject jsonObject = null;
//        try {
//            HttpResponse response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                String result = EntityUtils.toString(entity, "UTF-8");
//                jsonObject = JSONObject.parseObject(result);
//            }
//            httpGet.releaseConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }
//    public static JSONObject doPostRequest(String uri) throws IOException{
//        return HttpClientUtil.doPostRequest(uri,new JSONObject());
//    }
//    public static JSONObject doPostRequest(String uri,JSONObject params) throws IOException {
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(uri);
//        JSONObject jsonObject = null;
//        try {
//            httpPost.setEntity(new StringEntity(params.toString(), "UTF-8"));
//            HttpResponse response = httpClient.execute(httpPost);
//            String result = EntityUtils.toString(response.getEntity(),"UTF-8");
//            jsonObject = JSONObject.parseObject(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }
//
//
//}

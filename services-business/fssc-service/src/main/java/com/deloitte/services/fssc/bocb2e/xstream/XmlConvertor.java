package com.deloitte.services.fssc.bocb2e.xstream;

import com.deloitte.platform.common.core.util.ExceptionUtil;
import com.deloitte.services.fssc.bocb2e.bo.*;
import com.deloitte.services.fssc.bocb2e.bo.request.B2e0009Rq;
import com.deloitte.services.fssc.bocb2e.bo.request.TrnB2e0009Rq;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 单利类
 */
@Slf4j
public class XmlConvertor {



    private XmlConvertor() {
    }

    public static XmlConvertor getInstance() {
        if (!ISINIT) {
            init();
            ISINIT = true;
        }
        return Our.INSTANCE;
    }

    private static class Our {
        private static XmlConvertor INSTANCE = new XmlConvertor();
    }


    private static boolean ISINIT = false;

    private static XStream XSTREAM=new XStream(new DomDriver());

    private static void init() {
        String packageName = "com.deloitte.services.fssc.bocb2e.bo";
        String packageDir=packageName.replace(".","/");
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageDir);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    loadClassByFilePath(filePath, packageName);
                }
            }
        } catch (Exception e) {
            log.error("初始化xStream失败,{}", ExceptionUtil.getStackTraceAsString(e));
        }

    }


    private static void loadClassByFilePath(String filePath, String packageName) {
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()){
                loadClassByFilePath(f.getAbsolutePath(),packageName+"."+f.getName());
            }else {
                String className=f.getName().substring(0,f.getName().length()-6);
                try {
                    Class<?> aClass = Thread.currentThread().getContextClassLoader()
                            .loadClass(packageName + '.' + className);
                    XSTREAM.processAnnotations(aClass);
                }catch (Exception e){
                    log.error("初始化类失败,{}",ExceptionUtil.getStackTraceAsString(e));
                }

            }
        }

    }

    /**
     * 对象转XML
     * @param o
     * @return
     */
    public String toXml(Object o){
        String head="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        String xml = XSTREAM.toXML(o);
        return head+xml;
    }
    /**
     * xml转对象
     * @param xml
     * @param t
     * @param <T>
     * @return
     */
    public <T> T  toObject(String xml, Class<T> t){
        T o =(T)XSTREAM.fromXML(xml);
        return o;
    }



    public static void main(String[] args){
        XmlConvertor convertor=XmlConvertor.getInstance();

        Bocb2eBo bocb2eBo=new Bocb2eBo();
        BocbHeader bocbHeader=new BocbHeader();

        bocb2eBo.setHeader(bocbHeader);
        bocbHeader.setCusopr("23232323");
        bocbHeader.setCustid("343422");
        bocbHeader.setPushnum("3");
        bocbHeader.setToken("asdawdawd");

        BocbTrans trans =new BocbTrans();
        TrnB2e0009Rq trnB2e0009Rq=new TrnB2e0009Rq();


        List<B2e0009Rq> b2e0009Rqs=new ArrayList<>();
        B2e0009Rq b2e0009Rq=new B2e0009Rq();
        b2e0009Rq.setComacn("askdjalsd");
        b2e0009Rq.setInsid("222222211");
        b2e0009Rq.setObssid("879879879");
        b2e0009Rq.setPriolv("adsadawdawd");
        b2e0009Rqs.add(b2e0009Rq);

        Toactn toactn=new Toactn();
        toactn.setActacn("as3radsa");
        toactn.setToaddr("toaddr");
        toactn.setTobknm("ass");
        toactn.setToibkn("sadasdad");
        b2e0009Rq.setToactn(toactn);

        Fractn fractn=new Fractn();
        fractn.setActacn("111223322");
        fractn.setActnam("我我我i");
        fractn.setFribkn("988980909");
        b2e0009Rq.setFractn(fractn);

        trnB2e0009Rq.setB2e0009Rqs(b2e0009Rqs);
        trans.setTrnB2e0009Rq(trnB2e0009Rq);
        bocb2eBo.setTrans(trans);


        String s = convertor.toXml(bocb2eBo);

        Bocb2eBo dd = convertor.toObject(s, Bocb2eBo.class);
        System.out.println(s);

    }
}

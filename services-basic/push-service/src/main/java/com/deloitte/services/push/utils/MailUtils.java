package com.deloitte.services.push.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

public class MailUtils {
    public static void send(String mail,String title,String content) {
        MailUtils mailUtils = new MailUtils();
        mailUtils.sendSimpleMail(mail,title,content);
    }
    public void sendSimpleMail(String mail,String title,String content){
        MimeMessage message = null;
        try {
            JavaMailSender javaMailSender = javaMailSenderFactory();
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("macrp@cams.cn");
            helper.setTo(mail);
            helper.setSubject(title);

            StringBuffer sb = new StringBuffer();
            helper.setText(content, true);
            //FileSystemResource fileSystemResource=new FileSystemResource(new File("D:\76678.pdf"));
           // helper.addAttachment("电子发票",fileSystemResource);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private JavaMailSender javaMailSenderFactory(){
        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        ((JavaMailSenderImpl) javaMailSender).setHost("124.17.100.113");
        ((JavaMailSenderImpl) javaMailSender).setUsername("macrp@cams.cn");
        ((JavaMailSenderImpl) javaMailSender).setPassword("nc.smac@prcam");
        ((JavaMailSenderImpl) javaMailSender).setPort(25);
        ((JavaMailSenderImpl) javaMailSender).setProtocol("smtp");
        ((JavaMailSenderImpl) javaMailSender).setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.ssl.enable","false");
        ((JavaMailSenderImpl) javaMailSender).setJavaMailProperties(properties);
        return javaMailSender;
    }
}

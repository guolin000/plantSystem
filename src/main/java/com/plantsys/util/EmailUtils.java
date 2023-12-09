package com.plantsys.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailUtils {
    /**
     * 返回一个邮件发送对象
     * @return
     */
    public static JavaMailSenderImpl createJavaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        //实际项目中，平台搭建邮件服务器，如果真实项目需要自己搭建,此处用的是qq邮箱服务
        javaMailSender.setHost("smtp.qq.com");  //qq邮箱服务器
        // 发送方邮箱 平台官方邮箱
        javaMailSender.setUsername("347413502@qq.com"); //模拟官方邮箱账号
        // 发送秘钥 不是邮箱密码 也不是QQ密码！！！
        //如果用的是qq邮箱服务，则此处填的是qq邮箱中开启服务获得的 "授权码"
        javaMailSender.setPassword("phtdqjltkpfdbgcd"); //官方邮箱秘钥 / 授权码
        // 邮件协议
        javaMailSender.setProtocol("smtp");
        //属性
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.from", "347413502@qq.com"); //发送方
        properties.setProperty("mail.debug", "true");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    /**
     * @param emailTo 发送给谁
     * @param content 邮件内容
     * @param title   邮件标题
     *
     * 在controller中调用.sendEmail()方法来实现邮件的发送
     */
    public static void sendEmail(String emailTo, String content, String title) throws Exception {
        //构建“邮件发送”对象
        JavaMailSenderImpl sender = EmailUtils.createJavaMailSenderImpl();
        //构建“简单邮件”对象     SimpleMailMessage:简单邮箱对象
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(sender.getUsername());  //发送者是谁
        smm.setTo(emailTo);  //发送给谁
        smm.setSubject(title);  //邮件标题
        smm.setText(content);   //邮箱内容给
        sender.send(smm);
        System.out.println("邮件发送" + emailTo + "成功....");
    }
}

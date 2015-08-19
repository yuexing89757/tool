package com.zzy.tool.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;




/**
 * 
 * @author Qixuan.Chen
 */
public class SendMailUtil {

    public static final String HOST = "smtp.163.com";
    public static final String PROTOCOL = "smtp";   
    public static final int PORT = 25;
    public static final String FROM = "kaige8312@163.com";//发件人的email
    public static final String PWD = "gkg5224271";//发件人密码
   

    public static void send(String toEmail , String content) {
    	 Properties props = new Properties();
         props.put("mail.smtp.host", HOST);//设置服务器地址
         props.put("mail.store.protocol" , PROTOCOL);//设置协议
         props.put("mail.smtp.port", String.valueOf(PORT));//设置端口
         props.put("mail.smtp.auth" , "true");

         Authenticator authenticator = new Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(FROM, PWD);
             }

         };
         Session session = Session.getDefaultInstance(props , authenticator);
        try {
            System.out.println("--send--"+content);
            // 初始化一个 message
            Message msg = new MimeMessage(session);
            //设置message属性
            msg.setFrom(new InternetAddress(FROM));
            //收件人
            InternetAddress[] address = {new InternetAddress(toEmail)};
			//InternetAddress[] iaToList = new InternetAddress().parse(toList);//toList是以逗号隔开的字符串   多个收件人
            msg.setRecipients(Message.RecipientType.TO, address);  //设置收件人
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
             Transport.send(msg);
        }
        catch (MessagingException mex) {
             mex.printStackTrace();
        }
    }
   
    
    
    //测试发邮件
    @Test
    public  void  sendmail(){
    	SendMailUtil.send("243860327@qq.com", "123456789");
    }
    
}



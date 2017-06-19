package common;

/**
 * Created by L.H.S on 2017/3/12.
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName: Sendmail
 * @Description: Sendmail类继承Thread，因此Sendmail就是一个线程类，这个线程类用于给指定的用户发送Email
 * @author: howSure
 * @date: 2017-3-12
 */
public class SendEmail extends Thread {

    //用于给用户发送邮件的邮箱
    private String from = "howsurelee@163.com";
    //邮箱的用户名
    private String username = "howsurelee@163.com";
    //邮箱的密码
    private String password = "Sure1215";
    //发送邮件的服务器地址
    private String host = "smtp.163.com";

    private RespInfo respInfo;

    public SendEmail(RespInfo respInfo) {
        this.respInfo = respInfo;
    }

    /* 重写run方法的实现，在run方法中发送邮件给指定的用户
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            Session session = Session.getInstance(prop);
            session.setDebug(true);
            Transport ts = session.getTransport();
            ts.connect(host, username, password);

            // create email
            Message message = createEmail(session, respInfo);
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param session
     * @param respInfo; info-要发送的信息，object-邮箱地址
     * @return
     * @throws Exception
     * @Method: createEmail
     * @Description: 创建要发送的邮件
     * @Anthor: howSure
     */
    public Message createEmail(Session session, RespInfo respInfo) throws Exception {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(respInfo.getObject().toString()));  // 邮箱地址
        message.setSubject("HostelWorld");  // 主题

        String info = respInfo.getInfo();
        message.setContent(info, "text/html;charset=UTF-8");
        message.saveChanges();
        return message;
    }
}
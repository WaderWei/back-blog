package wade.wei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import wade.wei.service.EmailServer;
import wade.wei.utils.EmailUtils;
import wade.wei.utils.NumberUtils;

/**
 * @author Administrator
 */
@Service
public class EmailServerImpl implements EmailServer {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 通过rabbitmq发送邮件
     * @param receiver 接收者
     */
    @Override
    public void sentEmail(String receiver,String code) {
        // 发送邮件
        javaMailSender.send(EmailUtils.getMassage(receiver, code));
    }
}

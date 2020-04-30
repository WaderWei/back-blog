package wade.wei;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import wade.wei.stategy.AuthFactory;
import wade.wei.utils.JsonMapperUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    /* @Autowired
     private AmqpTemplate amqpTemplate;*/
    @Test
    public void test() throws MessagingException {
        /*// 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者
        message.setFrom("745696658@qq.com");
        // 设置邮件接收者，可以有多个接收者
        message.setTo("910960741@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("内容");
        // 发送邮件
        javaMailSender.send(message);*/
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("745696658@qq.com");
        helper.setTo("910960741@qq.com");
        String template = "<div style='width: 300px'>\n" +
                "  <h5>尊敬的用户：</h5>\n" +
                "  <div><p style='text-indent:2em'>您的验证码是:<a href='#'>" + 123456 + "</a>,请您在5分钟内完成验证，请勿向他人泄露您的验证码。</p></div>\n" +
                "  <div style='text-align: right;margin-right: 20px;font-weight: bolder'>myblog</div>\n" +
                "</div>";
        helper.setText(template,true);
        // 发送邮件
        javaMailSender.send(mimeMessage);
    }

    @Autowired
    private AuthFactory authFactory;
    @Test
    public  void  test1() throws InterruptedException {
        System.out.println(authFactory);
    }
}

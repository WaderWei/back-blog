package wade.wei.utils;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import wade.wei.properties.EmailProperties;

/**
 * @author Administrator
 */
public class EmailUtils {
    private static final String SUBJECT = "myblog";

    public static SimpleMailMessage getMassage(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(SUBJECT);
        message.setFrom(new EmailProperties().getUserName());
        message.setTo(to);
        String template = "<div style='width: 300px'>\n" +
                "  <h5>尊敬的用户：</h5>\n" +
                "  <div><p style='text-indent:2em'>您的验证码是:<a href='#'>" + code + "</a>,请您在5分钟内完成验证，请勿向他人泄露您的验证码。</p></div>\n" +
                "  <div style='text-align: right;margin-right: 20px;font-weight: bolder'>myblog</div>\n" +
                "</div>";
        message.setText(template);
        return message;
    }
}

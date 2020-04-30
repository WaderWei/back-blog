package wade.wei.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import wade.wei.entity.User;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.properties.JwtProperties;
import wade.wei.properties.PubProperties;
import wade.wei.service.PubServer;
import wade.wei.utils.JsonMapperUtil;
import wade.wei.utils.JwtUtils;
import wade.wei.utils.NumberUtils;
import wade.wei.vo.UserVO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Service
public class PubServerImpl implements PubServer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 通过rabbitmq发送邮件
     *
     * @param receiver 接收者
     */
    @Override
    public void sentEmail(String receiver, String code) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(PubProperties.SUBJECT);
            helper.setFrom(PubProperties.USERNAME);
            helper.setTo(receiver);
            String template = "<div style='width: 300px'>\n" +
                    "  <h5>尊敬的用户：</h5>\n" +
                    "  <div><p style='text-indent:2em'>您的验证码是:<a href='#'>" + code + "</a>,请您在5分钟内完成验证，请勿向他人泄露您的验证码。</p></div>\n" +
                    "  <div style='text-align: right;margin-right: 20px;font-weight: bolder'>myblog</div>\n" +
                    "</div>";
            helper.setText(template, true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在redis中存储验证码
     * 失效时间5分钟
     *
     * @param key
     * @param code
     */
    @Override
    public void saveCode(String key, String code) {
        // 30s之内不能重复发送
        Long expire = this.redisTemplate.opsForValue().getOperations().getExpire(key);
        if (expire != null && expire + 30 > 300 ) {
           throw new BusinessException(CommonReturnEnum.EXIST_CODE);
        }
        this.redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }


    /**
     * 发送code
     *
     * @param receiver 接收人
     */
    @Override
    public void sentCode(String receiver) {
        String code = NumberUtils.generateCode(5);
        UserVO userVO = new UserVO();
        userVO.setEmail(receiver);
        userVO.setVerificationCode(code);
        // 存储到redis中
        this.saveCode(PubProperties.CODE_KEY_PRE + receiver, code);
        // 发送到rabbitmq中
        this.amqpTemplate.convertAndSend(PubProperties.EXCHANGE_NAME, PubProperties.QUEUE_NAME, JsonMapperUtil.obj2String(userVO));

    }

    @Override
    public boolean validCode(String code, String receiver) {
        String redisCode = this.redisTemplate.opsForValue().get(PubProperties.CODE_KEY_PRE + receiver);
        return StringUtils.equals(code, redisCode);

    }

    @Override
    public String generateToken(User user) {
        return JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
    }
}

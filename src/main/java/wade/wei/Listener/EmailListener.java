package wade.wei.Listener;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import wade.wei.properties.PubProperties;
import wade.wei.service.PubServer;
import wade.wei.utils.JsonMapperUtil;
import wade.wei.vo.UserVO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author Administrator
 */
@Component
public class EmailListener {

    @Autowired
    private PubServer pubServer;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = PubProperties.QUEUE_NAME, durable = "true"),
            exchange = @Exchange(value = PubProperties.EXCHANGE_NAME, ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {PubProperties.CODE_ROUTING_KEY}
    ))
    public void sentEmail(String msg) throws MessagingException {
        UserVO userVO = JsonMapperUtil.string2Obj(msg, new TypeReference<UserVO>() {});
        pubServer.sentEmail(userVO.getEmail(),userVO.getVerificationCode());
    }
}

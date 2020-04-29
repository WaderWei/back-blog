package wade.wei.Listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
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
@Slf4j
public class EmailListener {

    @Autowired
    private PubServer pubServer;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = PubProperties.QUEUE_NAME, durable = "true"),
            exchange = @Exchange(value = PubProperties.EXCHANGE_NAME, ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {PubProperties.CODE_ROUTING_KEY}
    ))
    public void sentEmail(String msg, Channel channel, Message message) {
        try {
            UserVO userVO = JsonMapperUtil.string2Obj(msg, new TypeReference<UserVO>() {});
            System.out.println(userVO.getVerificationCode());
            pubServer.sentEmail(userVO.getEmail(),userVO.getVerificationCode());
        }catch (Exception e){ // 只要发生异常，就丢弃此消息
            log.error(e.getMessage());
        }
    }
}

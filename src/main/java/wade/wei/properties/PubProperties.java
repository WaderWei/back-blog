package wade.wei.properties;

import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * 注意，使用此bean时，必须autowired
 */
@Data
public class PubProperties {

    public static String USERNAME = "745696658@qq.com";

    public static final String CODE_KEY_PRE = "user:mail:code:";

    public static final String EXCHANGE_NAME = "email.exchange";

    public static final String CODE_ROUTING_KEY = "verity.code";

    public static final String QUEUE_NAME = "email.queue";

    public static final String SUBJECT = "myblog";

}

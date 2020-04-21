package wade.wei.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 */
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
    private String userName;
}

package wade.wei.service;

/**
 * @author Administrator
 */
public interface EmailServer {
    /**
     * 发送邮件
     * @param receiver
     * @param code
     */
    void sentEmail(String receiver,String code);
}

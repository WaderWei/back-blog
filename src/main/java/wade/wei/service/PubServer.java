package wade.wei.service;

/**
 * @author Administrator
 */
public interface PubServer {
    /**
     * 发送邮件
     * @param receiver
     * @param code
     */
    void sentEmail(String receiver,String code);

    /**
     * 保存验证码
     * @param receiver
     * @param code
     */
    void saveCode(String receiver,String code);

    /**
     * 发送code
     * @param receiver
     */
    void sentCode(String receiver);
}

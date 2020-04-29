package wade.wei.service;

import wade.wei.entity.User;

/**
 * @author Administrator
 */
public interface PubServer {
    /**
     * 发送邮件
     *
     * @param receiver
     * @param code
     */
    void sentEmail(String receiver, String code);

    /**
     * 保存验证码
     *
     * @param receiver
     * @param code
     */
    void saveCode(String receiver, String code);

    /**
     * 发送code
     *
     * @param receiver
     */
    void sentCode(String receiver);

    /**
     * 校验code
     *
     * @param code 输入的code
     * @param key  用户邮箱
     * @return
     */
    boolean validCode(String code, String key);

    /**
     * 生成token
     * @param user
     * @return
     */
    String generateToken(User user);
}

package wade.wei.service;

import wade.wei.entity.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {
    /**
     * 手机号
     *
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> selectList();

    /**
     * 判断邮箱是否存在
     * @param email
     * @return
     */
    boolean existEmail(String email);

}

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

    List<User> selectList();

}

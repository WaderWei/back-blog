package wade.wei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wade.wei.entity.User;
import wade.wei.mapper.UserMapper;
import wade.wei.service.UserService;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public List<User> selectList() {
        return userMapper.selectList(null);
    }
}

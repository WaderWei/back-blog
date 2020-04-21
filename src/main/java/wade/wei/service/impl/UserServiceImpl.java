package wade.wei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public boolean existEmail(String email) {
        User user = this.userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserEmail, email));
        return user == null;
    }
}

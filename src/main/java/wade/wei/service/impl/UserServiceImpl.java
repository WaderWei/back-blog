package wade.wei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wade.wei.entity.User;
import wade.wei.enums.CommonReturnEnum;
import wade.wei.exception.BusinessException;
import wade.wei.mapper.UserMapper;
import wade.wei.service.PubServer;
import wade.wei.service.UserService;
import wade.wei.utils.CodecUtils;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PubServer pubServer;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public User getUser(User user) {
        User uInDB = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserEmail, user.getUserEmail()));
        if (uInDB == null) {
            // 用户名不存在
            throw new BusinessException(CommonReturnEnum.EMAIL_OR_PWD_ERROR);
        }

        // 用户存在，校验密码
        if (!StringUtils.equals(uInDB.getUserPassword(), CodecUtils.md5Hex(user.getUserPassword(), uInDB.getUserSalt()))) {
            // 密码不存在
            throw new BusinessException(CommonReturnEnum.EMAIL_OR_PWD_ERROR);
        }

        return user;

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

    @Override
    public void saveUser(User user, String code) {
        boolean b = this.pubServer.validCode(code, user.getUserEmail());
        // 验证码错误
        if (!b) {
            throw new BusinessException(CommonReturnEnum.CODE_ERROR);
        }
        // 生成盐
        user.setUserSalt(CodecUtils.generateSalt());
        //生成密码
        user.setUserPassword(CodecUtils.md5Hex(user.getUserPassword(), user.getUserSalt()));
        int result = this.userMapper.insert(user);

        // 删除key
    }
}

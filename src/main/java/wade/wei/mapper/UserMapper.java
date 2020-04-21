package wade.wei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.PathVariable;
import wade.wei.entity.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据手机号查询用户
     *
     * @param phone
     * @return
     */
    public User getUserByPhone(@PathVariable String phone);

    /**
     * 判断邮箱是够存在
     * @param email
     * @return
     */
    /*public boolean existEmail(String email);*/

}

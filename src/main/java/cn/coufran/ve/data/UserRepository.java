package cn.coufran.ve.data;

import cn.coufran.ve.model.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据仓库组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Mapper
public interface UserRepository extends BaseMapper<User> {

    /**
     * 根据用户名和密码（加密后的）获取用户
     * @param username 用户名
     * @param password 密码（已加密）
     * @return 用户信息，找不到返回null
     */
    default User selectByUsernameAndPassword(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password);
        return this.selectOne(wrapper);
    }

}

package cn.coufran.ve.data;

import cn.coufran.ve.model.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {

    default User selectByUsernameAndPassword(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password);
        return this.selectOne(wrapper);
    }

}

package cn.coufran.ve.data;

import cn.coufran.ve.model.Token;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * Token 数据仓库组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Mapper
public interface TokenRepository extends BaseMapper<Token> {

    default Token selectByIdForUpdate(Serializable id) {
        Wrapper<Token> wrapper = new QueryWrapper<Token>().lambda()
                .eq(Token::getToken, id)
                .last("for update");
        return this.selectOne(wrapper);
    }
}

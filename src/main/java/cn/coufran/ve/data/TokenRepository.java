package cn.coufran.ve.data;

import cn.coufran.ve.model.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenRepository extends BaseMapper<Token> {
}

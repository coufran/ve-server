package cn.coufran.ve.data;

import cn.coufran.ve.model.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Token 数据仓库组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Mapper
public interface TokenRepository extends BaseMapper<Token> {
}

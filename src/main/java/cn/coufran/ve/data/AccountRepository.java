package cn.coufran.ve.data;

import cn.coufran.ve.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository extends BaseMapper<Account> {
}

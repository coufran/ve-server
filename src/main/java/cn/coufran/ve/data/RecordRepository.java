package cn.coufran.ve.data;

import cn.coufran.ve.model.Record;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 收支记录数据仓库组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Mapper
public interface RecordRepository extends BaseMapper<Record> {

    /**
     * 列举指定时间的收支记录
     * @param startTime 起始时间（含）
     * @param endTime 结束时间（含）
     * @return 符合条件的收支记录
     */
    default List<Record> listBetweenTime(Date startTime, Date endTime) {
        endTime.setTime(endTime.getTime() + 24*60*60*1000 - 1); // endTime设置到当天23:59:59.999
        Wrapper<Record> wrapper = new QueryWrapper<Record>().lambda()
                .between(Record::getTime, startTime, endTime)
                .orderByDesc(Record::getTime, Record::getId);
        return this.selectList(wrapper);
    }
}

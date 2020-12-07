package cn.coufran.ve.service;

import cn.coufran.ve.model.Record;

import java.util.Date;
import java.util.List;

/**
 * 收支记录服务组件
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public interface RecordService {
    /**
     * 列举指定时间的收支记录
     * @param startTime 起始时间（含）
     * @param endTime 结束时间（含）
     * @return 符合条件的收支记录
     */
    List<Record> list(Date startTime, Date endTime);
}

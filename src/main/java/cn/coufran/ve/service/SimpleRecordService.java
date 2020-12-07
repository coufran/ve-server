package cn.coufran.ve.service;

import cn.coufran.ve.data.RecordRepository;
import cn.coufran.ve.model.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 收支记录服务基础实现
 * @author coufran
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class SimpleRecordService implements RecordService {
    @Resource
    private RecordRepository recordRepository;

    @Override
    public List<Record> list(Date startTime, Date endTime) {
        // 设置参数默认值
        LocalDate today = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        ZonedDateTime todayStartTime = today.atStartOfDay(defaultZoneId);
        // 起始时间默认当天
        if(startTime == null) {
            startTime = Date.from(todayStartTime.toInstant());
        }
        // 结束时间默认当天
        if(endTime == null) {
            endTime = Date.from(todayStartTime.toInstant());
        }
        // 查询
        return recordRepository.listBetweenTime(startTime, endTime);
    }
}

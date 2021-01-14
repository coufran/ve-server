package cn.coufran.ve.api;

import cn.coufran.ve.api.vo.RecordVo;
import cn.coufran.ve.model.Record;
import cn.coufran.ve.service.RecordService;
import cn.coufran.ve.util.RecordVoConvertor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收支记录类接口
 * @author coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/web/record")
@CrossOrigin
public class RecordController {

    @Resource
    private RecordService recordService;
    @Resource
    private RecordVoConvertor recordVoConvertor;

    /**
     * 列举指定时间的收支记录
     * @param startTime 起始时间（含）
     * @param endTime 结束时间（含）
     * @return 符合条件的收支记录
     */
    @RequestMapping("/list")
    public List<RecordVo> list(
            @RequestParam(value = "startTime", required = false) Date startTime,
            @RequestParam(value = "endTime", required = false) Date endTime) {
        List<Record> records = recordService.list(startTime, endTime);
        return records.stream()
                .map(recordVoConvertor::from)
                .collect(Collectors.toList());
    }

    /**
     * 添加收支记录
     * @param record 收支记录
     */
    @PostMapping("/add")
    public Boolean add(@RequestBody Record record) {
        recordService.add(record);
        return true;
    }

    /**
     * 修改收支记录
     * @param record 收支记录
     */
    @PostMapping("/edit")
    public void edit(@RequestBody Record record) {
        //TODO
    }

    /**
     * 删除收支记录
     * @param id 收支记录ID
     */
    @PostMapping("/remove")
    public void remove(@RequestBody Integer id) {
        //TODO
    }


}

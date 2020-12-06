package cn.coufran.ve.api;

import cn.coufran.ve.model.Record;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 收支记录类接口
 * @author coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    /**
     * 列举指定时间的收支记录
     * @param startTime 起始时间（含）
     * @param endTime 结束时间（含）
     * @return 符合条件的收支记录
     */
    @RequestMapping("/list")
    public List<Record> list(
            @RequestParam(value = "startTime", required = false) Date startTime,
            @RequestParam(value = "endTime", required = false) Date endTime) {
        //TODO
        return null;
    }

    /**
     * 添加收支记录
     * @param record 收支记录
     */
    @PostMapping("/add")
    public void add(@RequestBody Record record) {
        //TODO
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

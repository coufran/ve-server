package cn.coufran.ve.api;

import cn.coufran.ve.model.Title;
import cn.coufran.ve.service.TitleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/web/title")
public class TitleController {

    @Resource
    private TitleService titleService;

    @RequestMapping("/list")
    public List<Title> list() {
        return titleService.list();
    }
}

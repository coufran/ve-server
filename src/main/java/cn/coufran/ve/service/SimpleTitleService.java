package cn.coufran.ve.service;

import cn.coufran.ve.data.TitleRepository;
import cn.coufran.ve.model.Title;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SimpleTitleService implements TitleService {

    @Resource
    private TitleRepository titleRepository;

    @Override
    public List<Title> list() {
        return titleRepository.selectList(new QueryWrapper<>());
    }

    @Override
    public Title getById(Integer id) {
        return titleRepository.selectById(id);
    }
}

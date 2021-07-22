package top.waxijiang.rush.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.ChapterDao;
import top.waxijiang.rush.entity.Chapter;
import top.waxijiang.rush.service.ChapterService;

/**
 * @author waxijiang
 */
@Service("chapterService")
public class ChapterServiceImpl extends ServiceImpl<ChapterDao, Chapter> implements ChapterService {
}

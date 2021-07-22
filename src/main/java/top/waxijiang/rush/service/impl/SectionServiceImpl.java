package top.waxijiang.rush.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.SectionDao;
import top.waxijiang.rush.entity.Section;
import top.waxijiang.rush.service.SectionService;

/**
 * @author waxijiang
 */
@Service("sectionService")
public class SectionServiceImpl extends ServiceImpl<SectionDao, Section> implements SectionService {
}

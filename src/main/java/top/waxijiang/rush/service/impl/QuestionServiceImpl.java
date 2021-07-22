package top.waxijiang.rush.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.QuestionDao;
import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.service.QuestionService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {
}

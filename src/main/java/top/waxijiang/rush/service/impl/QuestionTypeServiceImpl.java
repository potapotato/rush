package top.waxijiang.rush.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.QuestionTypeDao;
import top.waxijiang.rush.entity.QuestionType;
import top.waxijiang.rush.service.QuestionTypeService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service
public class QuestionTypeServiceImpl extends ServiceImpl<QuestionTypeDao, QuestionType> implements QuestionTypeService {
}

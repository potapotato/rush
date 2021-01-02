package top.waxijiang.rush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.CourseDao;
import top.waxijiang.rush.entity.Course;
import top.waxijiang.rush.service.CourseService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getAllCourse() {
        return courseDao.selectAll();
    }
}

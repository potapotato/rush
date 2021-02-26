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

    @Override
    public boolean addCourse(String name, String desc, String createUserId, String imgUrl) {
        Course course = new Course();
        course.setCreateUserId(Integer.valueOf(createUserId));
        course.setEnabled(true);
        course.setDescription(desc);
        course.setImgUrl(imgUrl);
        course.setName(name);
        return courseDao.insertSelective(course) > 0;
    }

    @Override
    public boolean deleteCourseById(String id) {
        return courseDao.deleteByPrimaryKey(Integer.valueOf(id)) > 0;
    }

    @Override
    public Course findCourseById(String id) {
        return courseDao.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public boolean updateCourse(String name, String desc, String imgUrl,String enabled, String id) {
        Course course = new Course();
        course.setId(Integer.valueOf(id));
        course.setEnabled(true);
        course.setDescription(desc);
        course.setImgUrl(imgUrl);
        course.setName(name);
        course.setEnabled("1".equals(enabled));
        return courseDao.updateByPrimaryKeySelective(course) > 0;
    }

    @Override
    public List<Course> findAllCourse() {
        return courseDao.selectAllEnabled();
    }
}

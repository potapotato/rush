package top.waxijiang.rush.service;


import top.waxijiang.rush.entity.Course;

import java.util.List;

/**
 * @author waxijiang
 */
public interface CourseService {

    /**
     * 获取全部课程
     * @return 全部课程
     */
    List<Course> getAllCourse();
}

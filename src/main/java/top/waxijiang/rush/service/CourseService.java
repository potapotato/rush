package top.waxijiang.rush.service;


import org.springframework.ui.Model;
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

    /**
     * 创建课程
     * @param name　名字
     * @param desc　描述
     * @param createdUserId　创建者
     * @param imgUrl　封面地址
     * @return 是否成功
     */
    boolean addCourse(String name, String desc, String createdUserId, String imgUrl);

    /**
     * 根据ｉｄ删除课程
     * @param id id
     * @return 是否成功
     */
    boolean deleteCourseById(String id);

    /**
     * 根据ｉｄ搜索课程
     * @param id　id
     * @return 课程
     */
    Course findCourseById(String id);

    /**
     * 更新课程数据
     * @param name　课程名
     * @param desc　课程描述
     * @param imgUrl　封面图片
     * @param enabled 是否可用
     * @param id　课程id
     * @return 是否成功
     */
    boolean updateCourse(String name, String desc, String imgUrl,String enabled, String id);

    /**
     *  获取所有可用科目
     * @return Course List
     */
    List<Course> findAllCourse();
}

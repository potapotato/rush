package top.waxijiang.rush.controller;

import cn.hutool.crypto.SecureUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("/img")
public class ImageController {

    @Value("${custom.imageParentPath}")
    private String parentPath;

    @Value("${custom.loginIconPath}")
    private String loginIconPath;

    @Value("${custom.userIconPath}")
    private String userIconPath;

    @Value("${custom.courseImagePath}")
    private String courseImagePath;

    @Value("${custom.questionImagePath}")
    private String questionImagePath;

    @Value("${custom.answerImagePath}")
    private String answerImagePath;

    /**
     * 根据相对路径获取图片
     *
     * @param response HttpServletResponse对象
     * @param imgPath  相对路径
     */
    @RequestMapping("getImage")
    public void getIcon(HttpServletResponse response, @RequestParam("imgPath") String imgPath) {
        String filePath = "default.jpg";
        if (imgPath != null) {
            filePath = imgPath;
        }

        try {
            InputStream inputStream = new FileInputStream(new File(parentPath, filePath));
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取icon文件异常");
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(new File(parentPath, "default.jpg"));
                OutputStream outputStream = response.getOutputStream();
                IOUtils.copy(inputStream, outputStream);
                IOUtils.closeQuietly(inputStream);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("默认图片文件不存在");
            }
        }
    }

    @GetMapping("getRandomIcon")
    public void getRandomIcon(HttpServletResponse response) {
        Random random = new Random();
        String path = loginIconPath;
        File dir = new File(path);
        String[] list = dir.list();
        if (list == null) {
            this.getIcon(response, null);
        } else {
            this.getIcon(response, "loginIcon/" + list[random.nextInt(list.length)]);
        }
    }

    /**
     * 上传用户头像图片
     *
     * @param icon    图片对象 MultipartFile
     * @param request HttpServletRequest对象
     * @return 图片的url地址
     */
    @PostMapping("uploadIcon")
    @ResponseBody
    public String uploadIcon(MultipartFile icon, HttpServletRequest request) {
        if (icon != null) {
            try {
                String parentPath = userIconPath;
                InputStream inputStream = icon.getInputStream();
                String filename = SecureUtil.md5(inputStream);
                String[] split = icon.getOriginalFilename().split("\\.");
                filename += "." + split[split.length - 1];
                File file = new File(parentPath, filename);
                if (!file.exists()) {
                    icon.transferTo(file);
                }
                return "userIcon/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("头像上传失败!");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 上传课程封面
     *
     * @param icon    图片对象 MultipartFile
     * @param request HttpServletRequest对象
     * @return 图片的url地址
     */
    @PostMapping("uploadCourseImage")
    @ResponseBody
    public String uploadCourseImage(MultipartFile icon, HttpServletRequest request) {
        if (icon != null) {
            try {
                String parentPath = courseImagePath;
                InputStream inputStream = icon.getInputStream();
                String filename = SecureUtil.md5(inputStream);
                String[] split = icon.getOriginalFilename().split("\\.");
                filename += "." + split[split.length - 1];
                File file = new File(parentPath, filename);
                if (!file.exists()) {
                    icon.transferTo(file);
                }
                // todo: 硬编码问题
                return "courseImages/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("课程图片上传失败!");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 上传题目
     *
     * @param icon    图片对象 MultipartFile
     * @param request HttpServletRequest对象
     * @return 图片的url地址
     */
    @PostMapping("uploadQuestionImage")
    @ResponseBody
    public String uploadQuestionImage(MultipartFile icon, HttpServletRequest request) {
        if (icon != null) {
            try {
                String parentPath = questionImagePath;
                InputStream inputStream = icon.getInputStream();
                String filename = SecureUtil.md5(inputStream);
                String[] split = icon.getOriginalFilename().split("\\.");
                filename += "." + split[split.length - 1];
                File file = new File(parentPath, filename);
                if (!file.exists()) {
                    icon.transferTo(file);
                }
                // todo: 硬编码问题
                return "questionImages/question/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("题目图片上传失败!");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 上传题目
     *
     * @param icon    图片对象 MultipartFile
     * @param request HttpServletRequest对象
     * @return 图片的url地址
     */
    @PostMapping("uploadAnswerImage")
    @ResponseBody
    public String uploadAnswerImage(MultipartFile icon, HttpServletRequest request) {
        if (icon != null) {
            try {
                String parentPath = answerImagePath;
                InputStream inputStream = icon.getInputStream();
                String filename = SecureUtil.md5(inputStream);
                String[] split = icon.getOriginalFilename().split("\\.");
                filename += "." + split[split.length - 1];
                File file = new File(parentPath, filename);
                if (!file.exists()) {
                    icon.transferTo(file);
                }
                // todo: 硬编码问题
                return "questionImages/answer/" + filename;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("答案图片上传失败!");
                return null;
            }
        } else {
            return null;
        }
    }
}

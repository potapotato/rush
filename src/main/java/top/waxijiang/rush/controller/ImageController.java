package top.waxijiang.rush.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("/img")
public class ImageController {

    @RequestMapping("getImage")
    public void getIcon(HttpServletResponse response, @RequestParam("imgPath") String imgPath) {
        String parentPath = null;
        try {
            parentPath = ResourceUtils.getURL("classpath:").getPath() + "static/images";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("图片文件根目录未找到");
        }
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

    @GetMapping("getIcon")
    public void getRandomIcon(HttpServletResponse response) {
        Random random = new Random();
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/images/loginIcon";
            File dir = new File(path);
            String[] list = dir.list();
            if (list == null) {
                this.getIcon(response, null);
            } else {
                this.getIcon(response, "loginIcon/" + list[random.nextInt(list.length)]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("登录icon目录不存在");
        }
    }
}

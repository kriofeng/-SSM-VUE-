package com.upc.fpbackstage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadService {
    private static final List<String> ALL_TYPE = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/gif");
    //这里的地址是我本地的地址，如上传到服务器后，可以下面的地址。这个地址是自己新建的文件夹，如果没有这个文件夹，图片是传不了的
    public final static String FILE_PATH = "D:\\upcprogect\\ProjectInit";
//    public final static String FILE_PATH = "/usr/upload/";

    public String uploadImg(MultipartFile file, HttpServletRequest request) {
        //准备目标路径
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!ALL_TYPE.contains(contentType)) {
                return "无效的文件类型";
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null ){
                return "无效的文件类型";
            }

            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String newFileName = UUID.randomUUID()+suffixName;//新名字
            //获取路径
//            String rootPath = request.getSession().getServletContext().getRealPath("/");
            File dest = new File(FILE_PATH + newFileName);
            //保存文件到本地
            file.transferTo(dest);
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }
}

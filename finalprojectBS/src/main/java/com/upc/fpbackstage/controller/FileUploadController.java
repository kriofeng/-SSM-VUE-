package com.upc.fpbackstage.controller;


import com.upc.fpbackstage.util.ImageHandle;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;


/**
 * 单图上传
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class FileUploadController {
    @RequestMapping("/img")
    public String add(MultipartFile file, HttpServletRequest request) throws IOException {
        /*前端 UI 的 form 表单里面有用到图片上传的时候，需要在这里对 request 作以下处理*/
         //MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
         //String title = mulRequest.getParameter("title");//获取标题
        // ... ...
        //判空

        System.out.println(request.getParameter("title"));
        if( file != null ){
            //将图片上传成功后的返回链接返回
            String litimg = ImageHandle.uploadImageToCos(file);
            System.out.println(litimg);
            return litimg;
        }else{
            System.out.println("file null!");
            return  null;
        }
    }
}

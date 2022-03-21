package com.upc.fpbackstage.controller;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileUploadControllerTest {
    @Resource
    FileUploadController cl;
    @Test
    void add() throws IOException {
        String filePath = "C:/Users/bzf66/Desktop/test3.png";
        java.io.File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream)
        // 其中originalFilename,String contentType 旧名字，类型  可为空
        // ContentType.APPLICATION_OCTET_STREAM.toString() 需要使用HttpClient的包
        MultipartFile multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        System.out.println(multipartFile.getName()); // 输出copytest.txt
        String ans = cl.add(multipartFile,null);
        System.out.println(ans);
    }
}
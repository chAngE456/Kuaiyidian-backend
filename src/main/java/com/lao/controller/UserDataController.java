package com.lao.controller;

import com.lao.pojo.User;
import com.lao.service.UserService;
import com.lao.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserDataController {

    @Autowired
    private UserService userService;

    @Value("${app.uploaded-folder}")
    private String imageData;

    @GetMapping("/data")
    public ResponseEntity<?> GetUserData(HttpServletRequest request){
        String token = request.getHeader("token");
        TokenUtil tokenUtil = new TokenUtil();
        String uid = tokenUtil.getUidByToken(token);
        User user = userService.getUserById(uid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("/{uid}")
    public ResponseEntity<?> getUserDataById(@PathVariable String uid){
        ResponseEntity<?> result;
        User user = userService.getUserById(uid);
        if(user == null){
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            result = new ResponseEntity<>(user, HttpStatus.OK);
        }
        return result;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        //System.out.println(file.getOriginalFilename());
        String token = request.getHeader("token");
        TokenUtil tokenUtil = new TokenUtil();
        String fileName = tokenUtil.getUidByToken(token);
        String suffix = (file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        File newFile = new File(imageData + File.separator + fileName + suffix);
        try {
            file.transferTo(newFile);
            return new ResponseEntity<>("图片上传成功", HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/uploadBase64")
    public String uploadImage(HttpServletRequest request,@RequestBody Map<String, String> formData) {
        String base64Image = formData.get("imageBase64"); // 获取Base64字符串
        byte[] imageBytes = Base64.getDecoder().decode(base64Image); // 解码Base64字符串为字节数组
        String token = request.getHeader("token");
        TokenUtil tokenUtil = new TokenUtil();
        String fileName = tokenUtil.getUidByToken(token);
        // 指定保存文件的路径和名称
        Path path = Paths.get(imageData + File.separator + fileName + ".png");
        try {
            Files.write(path, imageBytes); // 将字节数组写入文件
            return "Image uploaded successfully"; // 返回成功消息
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while uploading image"; // 返回错误消息
        }
    }
}

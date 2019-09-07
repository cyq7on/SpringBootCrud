package com.cyq7on.crud.controller;

import com.cyq7on.crud.common.utils.HttpUtil;
import com.cyq7on.crud.common.utils.JsonUtil;
import com.cyq7on.crud.common.vo.PageInfo;
import com.cyq7on.crud.common.vo.Result;
import com.cyq7on.crud.entity.UploadImageResponse;
import com.cyq7on.crud.entity.User;
import com.cyq7on.crud.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public Result<PageInfo<User>> getUsers(@RequestParam(value = "tel", required = false) String tel,
                                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        return Result.ok(service.getUsers(tel, pageNo, pageSize));
    }

    @PostMapping("/add")
    public Result<User> addUser(@RequestBody User user) {
        return Result.ok(service.addUser(user));
    }

    @PostMapping("/update")
    public Result<User> updateUser(@RequestBody User user) {
        return Result.ok(service.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable(value = "id") int id) {
        int i = service.deleteUser(id);
        if (i > 0) {
            return Result.ok("删除用户成功");
        } else {
            return Result.fail("用户不存在");
        }
    }

    @PostMapping("/upload")
    public Result<String> singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            return Result.fail("文件不能为空！");
        }
        try {
            /*saveImg(file, "D:\\logs");
            return Result.ok("上传成功！");*/

            byte[] bytes = file.getBytes();
            //https://sm.ms/doc/v1
            Response response = HttpUtil.postFile("https://sm.ms/api/upload",
                    "smfile", file.getOriginalFilename(), bytes);
            String resStr = response.body().string();
            System.out.println(resStr);
            UploadImageResponse uploadImageResponse = JsonUtil.string2Obj(resStr, UploadImageResponse.class);
            String code = uploadImageResponse.code;
            if ("success".equals(code)) {
                return Result.ok(uploadImageResponse.data.url,"上传成功！");
            }else {
                return Result.fail(uploadImageResponse.message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败！");
        }

    }

    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

}

package com.dikar.upaidemo.controller;

import com.upyun.FormUploader;
import com.upyun.Params;
import com.upyun.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class UploadController {

    @Value(value = "${youpai.bucket_name}")
    private String buckName;

    @Value(value = "${youpai.operator_name}")
    private String operatorName;

    @Value(value = "${youpai.operator_pwd}")
    private String operatorPwd;

    @PostMapping(value = "/upload")
    public void uploadFile(@RequestBody MultipartFile file) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        final Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Params.SAVE_KEY, "/meijia/" + file.getOriginalFilename());
        FormUploader uploader = new FormUploader(buckName, operatorName, operatorPwd);
        Result upload = uploader.upload(paramsMap, file.getBytes());
        System.out.println(upload);
    }

    @GetMapping(value = "/getReq")
    public void testGetReq() {
        System.out.println("-----------------");
    }
}

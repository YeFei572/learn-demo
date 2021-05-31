package com.dikar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api
 * @Author: yefei
 * @CreateTime: 2021-05-28 16:21
 * @Description: 主启动类
 */
@SpringBootApplication
@ServletComponentScan
public class NettyChatStarter {

    public static void main(String[] args) {
        SpringApplication.run(NettyChatStarter.class, args);
    }
}

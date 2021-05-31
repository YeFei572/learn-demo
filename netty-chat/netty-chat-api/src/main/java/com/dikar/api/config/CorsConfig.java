package com.dikar.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: learn-demo
 * @BelongsPackage: com.dikar.api.config
 * @Author: yefei
 * @CreateTime: 2021-05-31 17:50
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {

    // 允许的域
    private String allowedOrigins;
    // 允许的方法
    private String allowedMethods;
    // 允许的头信息
    private String allowedHeaders;
    // 是否允许Cookie
    private String allowedCredentials;

}

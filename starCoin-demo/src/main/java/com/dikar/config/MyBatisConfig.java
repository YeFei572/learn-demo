package com.dikar.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.dikar.mapper")
public class MyBatisConfig extends MybatisConfiguration {
}
